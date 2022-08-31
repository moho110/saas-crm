package api;

import com.vrmlstudio.system.domain.CmfSystemconfig;
import com.vrmlstudio.system.service.ICmfSystemconfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 系统配置记录表
 */
@Api(value = "系统配置记录表")
@RestController
public class CmfSystemconfigApi {

    @Autowired
    private ICmfSystemconfigService iCmfSystemconfigService;

    @ApiOperation(value = "系统配置记录表", notes = "获取系统配置记录表")
    @ApiResponse(code = 400, message = "获取系统配置记录失败")
    @RequestMapping(value = "/xsystem/cmfSystemconfig/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSystemconfig cmfSystemconfig) throws IOException {
        return new ResponseEntity<>(iCmfSystemconfigService.selectCmfSystemconfigList(cmfSystemconfig), HttpStatus.OK);
    }

    @ApiOperation(value = "系统配置记录表", notes = "获取系统配置记录表详情")
    @ApiResponse(code = 400, message = "获取系统配置详情失败")
    @RequestMapping(value = "/xsystem/cmfSystemconfig/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSystemconfigService.selectCmfSystemconfigById(id),HttpStatus.OK);
    }
}
