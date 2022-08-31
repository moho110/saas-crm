package api;

import com.vrmlstudio.customer.domain.CmfCrmContact;
import com.vrmlstudio.customer.service.ICmfCrmContactService;
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
 * 联系方式记录表
 */
@Api(value = "联系方式记录表")
@RestController
public class CmfCrmContactApi {

    @Autowired
    private ICmfCrmContactService iCmfCrmContactService;

    @ApiOperation(value = "联系方式记录表", notes = "获取联系方式记录表")
    @ApiResponse(code = 400, message = "获取联系方式记录失败")
    @RequestMapping(value = "/customer/cmfCrmContact/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmContact cmfCrmContact) throws IOException {
        return new ResponseEntity<>(iCmfCrmContactService.selectCmfCrmContactList(cmfCrmContact), HttpStatus.OK);
    }

    @ApiOperation(value = "联系方式记录表", notes = "获取联系方式记录表详情")
    @ApiResponse(code = 400, message = "获取联系方式详情失败")
    @RequestMapping(value = "/customer/cmfCrmContact/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmContactService.selectCmfCrmContactById(id),HttpStatus.OK);
    }
}
