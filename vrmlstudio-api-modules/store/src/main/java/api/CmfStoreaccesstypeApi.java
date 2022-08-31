package api;

import com.vrmlstudio.store.domain.CmfStoreaccesstype;
import com.vrmlstudio.store.service.ICmfStoreaccesstypeService;
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
 * 仓储类型记录表
 */
@Api(value = "仓储类型记录表")
@RestController
public class CmfStoreaccesstypeApi {

    @Autowired
    private ICmfStoreaccesstypeService iCmfStoreaccesstypeService;

    @ApiOperation(value = "仓储类型记录表", notes = "获取仓储类型记录表")
    @ApiResponse(code = 400, message = "获取仓储类型记录失败")
    @RequestMapping(value = "/store/cmfStoreaccesstype/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfStoreaccesstype cmfStoreaccesstype) throws IOException {
        return new ResponseEntity<>(iCmfStoreaccesstypeService.selectCmfStoreaccesstypeList(cmfStoreaccesstype), HttpStatus.OK);
    }

    @ApiOperation(value = "仓储类型记录表", notes = "获取仓储类型记录表详情")
    @ApiResponse(code = 400, message = "获取仓储类型详情失败")
    @RequestMapping(value = "/store/cmfStoreaccesstype/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfStoreaccesstypeService.selectCmfStoreaccesstypeById(id),HttpStatus.OK);
    }
}
