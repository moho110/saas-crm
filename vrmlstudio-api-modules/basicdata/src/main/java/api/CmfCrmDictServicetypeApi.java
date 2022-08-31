package api;

import com.vrmlstudio.basicdata.domain.CmfCrmDictServicetype;
import com.vrmlstudio.basicdata.service.ICmfCrmDictServicetypeService;
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
 * 服务类型记录表
 */
@Api(value = "服务类型记录表")
@RestController
public class CmfCrmDictServicetypeApi {

    @Autowired
    private ICmfCrmDictServicetypeService iCmfCrmDictServicetypeService;

    @ApiOperation(value = "服务类型记录表", notes = "获取服务类型记录表")
    @ApiResponse(code = 400, message = "获取服务类型记录失败")
    @RequestMapping(value = "/basic/cmfCrmDictServicetype/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmDictServicetype cmfCrmDictServicetype) throws IOException {
        return new ResponseEntity<>(iCmfCrmDictServicetypeService.selectCmfCrmDictServicetypeList(cmfCrmDictServicetype), HttpStatus.OK);
    }

    @ApiOperation(value = "服务类型记录表", notes = "获取服务类型记录表详情")
    @ApiResponse(code = 400, message = "获取服务类型详情失败")
    @RequestMapping(value = "/basic/cmfCrmDictServicetype/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmDictServicetypeService.selectCmfCrmDictServicetypeById(id),HttpStatus.OK);
    }
}
