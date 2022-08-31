package api;

import com.vrmlstudio.customer.domain.CmfCustomer;
import com.vrmlstudio.customer.service.ICmfCustomerService;
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
 * 客户记录表
 */
@Api(value = "客户记录表")
@RestController
public class CmfCustomerApi {

    @Autowired
    private ICmfCustomerService iCmfCustomerService;

    @ApiOperation(value = "客户记录表", notes = "获取客户记录表")
    @ApiResponse(code = 400, message = "获取客户记录失败")
    @RequestMapping(value = "/customer/cmfCustomer/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCustomer cmfCustomer) throws IOException {
        return new ResponseEntity<>(iCmfCustomerService.selectCmfCustomerList(cmfCustomer), HttpStatus.OK);
    }

    @ApiOperation(value = "客户记录表", notes = "获取客户记录表详情")
    @ApiResponse(code = 400, message = "获取客户详情失败")
    @RequestMapping(value = "/customer/cmfCustomer/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCustomerService.selectCmfCustomerById(id),HttpStatus.OK);
    }
}
