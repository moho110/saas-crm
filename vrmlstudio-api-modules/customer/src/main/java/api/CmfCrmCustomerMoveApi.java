package api;

import com.vrmlstudio.customer.domain.CmfCrmCustomerMove;
import com.vrmlstudio.customer.service.ICmfCrmCustomerMoveService;
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
 * 客户移除记录表
 */
@Api(value = "客户移除记录表")
@RestController
public class CmfCrmCustomerMoveApi {

    @Autowired
    private ICmfCrmCustomerMoveService iCmfCrmCustomerMoveService;

    @ApiOperation(value = "客户移除记录表", notes = "获取客户移除记录表")
    @ApiResponse(code = 400, message = "获取客户移除记录失败")
    @RequestMapping(value = "/customer/cmfCrmCustomerMove/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmCustomerMove cmfCrmCustomerMove) throws IOException {
        return new ResponseEntity<>(iCmfCrmCustomerMoveService.selectCmfCrmCustomerMoveList(cmfCrmCustomerMove), HttpStatus.OK);
    }

    @ApiOperation(value = "客户移除记录表", notes = "获取客户移除记录表详情")
    @ApiResponse(code = 400, message = "获取客户移除详情失败")
    @RequestMapping(value = "/customer/cmfCrmCustomerMove/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmCustomerMoveService.selectCmfCrmCustomerMoveById(id),HttpStatus.OK);
    }
}
