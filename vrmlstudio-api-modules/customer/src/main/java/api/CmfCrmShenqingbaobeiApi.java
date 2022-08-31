package api;

import com.vrmlstudio.customer.domain.CmfCrmShenqingbaobei;
import com.vrmlstudio.customer.service.ICmfCrmShenqingbaobeiService;
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
 * 申请报备记录表
 */
@Api(value = "申请报备记录表")
@RestController
public class CmfCrmShenqingbaobeiApi {

    @Autowired
    private ICmfCrmShenqingbaobeiService iCmfCrmShenqingbaobeiService;

    @ApiOperation(value = "申请报备记录表", notes = "获取申请报备记录表")
    @ApiResponse(code = 400, message = "获取申请报备记录失败")
    @RequestMapping(value = "/customer/cmfCrmShenqingbaobei/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmShenqingbaobei cmfCrmShenqingbaobei) throws IOException {
        return new ResponseEntity<>(iCmfCrmShenqingbaobeiService.selectCmfCrmShenqingbaobeiList(cmfCrmShenqingbaobei), HttpStatus.OK);
    }

    @ApiOperation(value = "申请报备记录表", notes = "获取申请报备记录表详情")
    @ApiResponse(code = 400, message = "获取申请报备详情失败")
    @RequestMapping(value = "/customer/cmfCrmShenqingbaobei/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmShenqingbaobeiService.selectCmfCrmShenqingbaobeiById(id),HttpStatus.OK);
    }
}
