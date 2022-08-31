package api;

import com.vrmlstudio.basicdata.domain.CmfCrmDictServicestatus;
import com.vrmlstudio.basicdata.service.ICmfCrmDictServicestatusService;
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
 * 服务状态记录表
 */
@Api(value = "服务状态记录表")
@RestController
public class CmfCrmDictServicestatusApi {

    @Autowired
    private ICmfCrmDictServicestatusService iCmfCrmDictServicestatusService;

    @ApiOperation(value = "服务状态记录表", notes = "获取服务状态记录表")
    @ApiResponse(code = 400, message = "获取服务状态记录失败")
    @RequestMapping(value = "/basic/cmfCrmDictServicestatus/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmDictServicestatus cmfCrmDictServicestatus) throws IOException {
        return new ResponseEntity<>(iCmfCrmDictServicestatusService.selectCmfCrmDictServicestatusList(cmfCrmDictServicestatus), HttpStatus.OK);
    }

    @ApiOperation(value = "服务状态记录表", notes = "获取服务状态记录表详情")
    @ApiResponse(code = 400, message = "获取服务状态详情失败")
    @RequestMapping(value = "/basic/cmfCrmDictServicestatus/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmDictServicestatusService.selectCmfCrmDictServicestatusById(id),HttpStatus.OK);
    }
}
