package com.vrmlstudio.framework.web.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.vrmlstudio.common.constant.Constants;
import com.vrmlstudio.common.core.domain.entity.SysUser;
import com.vrmlstudio.common.core.domain.model.LoginUser;
import com.vrmlstudio.common.core.redis.RedisCache;
import com.vrmlstudio.common.exception.ServiceException;
import com.vrmlstudio.common.exception.user.CaptchaException;
import com.vrmlstudio.common.exception.user.CaptchaExpireException;
import com.vrmlstudio.common.exception.user.UserPasswordNotMatchException;
import com.vrmlstudio.common.utils.DateUtils;
import com.vrmlstudio.common.utils.MessageUtils;
import com.vrmlstudio.common.utils.ServletUtils;
import com.vrmlstudio.common.utils.ip.IpUtils;
import com.vrmlstudio.framework.manager.AsyncManager;
import com.vrmlstudio.framework.manager.factory.AsyncFactory;
import com.vrmlstudio.system.service.ISysConfigService;
import com.vrmlstudio.system.service.ISysUserService;

/**
 * 登录校验方法
 * 
 * @author vrmlstudio
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 登录验证
     * 
     *
     * @param tenant 租户ID
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String tenant, String username, String password, String code, String uuid)
    {

        validateCaptcha(username, code, uuid);

        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(tenant, username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(tenant, username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(tenant, username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        loginUser.setTenant(tenant);
        recordLoginInfo(loginUser.getUserId())                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ;
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor( username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor( username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }
}
