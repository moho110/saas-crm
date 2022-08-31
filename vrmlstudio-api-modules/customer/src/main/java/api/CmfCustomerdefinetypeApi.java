package api;

import com.vrmlstudio.customer.domain.CmfCustomerdefinetype;
import com.vrmlstudio.customer.service.ICmfCustomerdefinetypeService;
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
 * 客户自定义记录表
 */
@Api(value = "客户自定义记录表")
@RestController
public class CmfCustomerdefinetypeApi {

    @Autowired
    private ICmfCustomerdefinetypeService iCmfCustomerdefinetypeService;

    @ApiOperation(value = "客户自定义记录表", notes = "获取客户自定义记录表")
    @ApiResponse(code = 400, message = "获取客户自定义记录失败")
    @RequestMapping(value = "/customer/cmfCustomerdefinetype/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCustomerdefinetype cmfCustomerdefinetype) throws IOException {
        return new ResponseEntity<>(iCmfCustomerdefinetypeService.selectCmfCustomerdefinetypeList(cmfCustomerdefinetype), HttpStatus.OK);
    }

    @ApiOperation(value = "客户自定义记录表", notes = "获取客户自定义记录表详情")
    @ApiResponse(code = 400, message = "获取客户自定义详情失败")
    @RequestMapping(value = "/customer/cmfCustomerdefinetype/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCustomerdefinetypeService.selectCmfCustomerdefinetypeById(id),HttpStatus.OK);
    }
}
