package api;

import com.vrmlstudio.basicdata.domain.CmfNotify;
import com.vrmlstudio.basicdata.service.ICmfNotifyService;
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
 * 消息提醒记录表
 */
@Api(value = "消息提醒记录表")
@RestController
public class CmfNotifyApi {

    @Autowired
    private ICmfNotifyService iCmfNotifyService;

    @ApiOperation(value = "消息提醒记录表", notes = "获取消息提醒记录表")
    @ApiResponse(code = 400, message = "获取消息提醒记录失败")
    @RequestMapping(value = "/basic/cmfNotify/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfNotify cmfNotify) throws IOException {
        return new ResponseEntity<>(iCmfNotifyService.selectCmfNotifyList(cmfNotify), HttpStatus.OK);
    }

    @ApiOperation(value = "消息提醒记录表", notes = "获取消息提醒记录表详情")
    @ApiResponse(code = 400, message = "获取消息提醒详情失败")
    @RequestMapping(value = "/basic/cmfNotify/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfNotifyService.selectCmfNotifyById(id),HttpStatus.OK);
    }
}
