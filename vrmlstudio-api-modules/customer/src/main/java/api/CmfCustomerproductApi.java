package api;

import com.vrmlstudio.customer.domain.CmfCustomerproduct;
import com.vrmlstudio.customer.service.ICmfCustomerproductService;
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
 * 客户产品记录表
 */
@Api(value = "银行帐户记录表")
@RestController
public class CmfCustomerproductApi {

    @Autowired
    private ICmfCustomerproductService iCmfCustomerproductService;

    @ApiOperation(value = "客户产品记录表", notes = "获取客户产品记录表")
    @ApiResponse(code = 400, message = "获取客户产品记录失败")
    @RequestMapping(value = "/customer/cmfCustomerproduct/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCustomerproduct cmfCustomerproduct) throws IOException {
        return new ResponseEntity<>(iCmfCustomerproductService.selectCmfCustomerproductList(cmfCustomerproduct), HttpStatus.OK);
    }

    @ApiOperation(value = "客户产品记录表", notes = "获取客户产品记录表详情")
    @ApiResponse(code = 400, message = "获取客户产品详情失败")
    @RequestMapping(value = "/customer/cmfCustomerproduct/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCustomerproductService.selectCmfCustomerproductById(id),HttpStatus.OK);
    }
}
