package api;

import com.vrmlstudio.basicdata.domain.CmfCrmDictServicesource;
import com.vrmlstudio.basicdata.service.ICmfCrmDictServicesourceService;
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
 * 服务来源记录表
 */
@Api(value = "服务来源记录表")
@RestController
public class CmfCrmDictServicesourceApi {

    @Autowired
    private ICmfCrmDictServicesourceService iCmfCrmDictServicesourceService;

    @ApiOperation(value = "服务来源记录表", notes = "获取服务来源记录表")
    @ApiResponse(code = 400, message = "获取服务来源记录失败")
    @RequestMapping(value = "/basic/cmfCrmDictServicesource/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmDictServicesource cmfCrmDictServicesource) throws IOException {
        return new ResponseEntity<>(iCmfCrmDictServicesourceService.selectCmfCrmDictServicesourceList(cmfCrmDictServicesource), HttpStatus.OK);
    }

    @ApiOperation(value = "服务来源记录表", notes = "获取服务来源记录表详情")
    @ApiResponse(code = 400, message = "获取服务来源详情失败")
    @RequestMapping(value = "/basic/cmfCrmDictServicesource/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmDictServicesourceService.selectCmfCrmDictServicesourceById(id),HttpStatus.OK);
    }
}
