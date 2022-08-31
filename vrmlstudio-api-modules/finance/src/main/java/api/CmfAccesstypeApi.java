package api;

import com.vrmlstudio.finance.domain.CmfAccesstype;
import com.vrmlstudio.finance.service.ICmfAccesstypeService;
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
 * 存取类型记录表
 */
@Api(value = "存取类型记录表")
@RestController
public class CmfAccesstypeApi {

    @Autowired
    private ICmfAccesstypeService iCmfAccesstypeService;

    @ApiOperation(value = "存取类型记录表", notes = "获取存取类型记录表")
    @ApiResponse(code = 400, message = "获取存取类型记录失败")
    @RequestMapping(value = "/finance/cmfAccesstype/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfAccesstype cmfAccesstype) throws IOException {
        return new ResponseEntity<>(iCmfAccesstypeService.selectCmfAccesstypeList(cmfAccesstype), HttpStatus.OK);
    }

    @ApiOperation(value = "存取类型记录表", notes = "获取存取类型记录表详情")
    @ApiResponse(code = 400, message = "获取存取类型详情失败")
    @RequestMapping(value = "/finance/cmfAccesstype/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfAccesstypeService.selectCmfAccesstypeById(id),HttpStatus.OK);
    }
}
