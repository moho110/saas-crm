package api;

import com.vrmlstudio.finance.domain.CmfPaystate;
import com.vrmlstudio.finance.service.ICmfPaystateService;
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
 * 支付状态记录表
 */
@Api(value = "支付状态记录表")
@RestController
public class CmfPaystateApi {

    @Autowired
    private ICmfPaystateService iCmfPaystateService;

    @ApiOperation(value = "支付状态记录表", notes = "获取支付状态记录表")
    @ApiResponse(code = 400, message = "获取支付状态记录失败")
    @RequestMapping(value = "/finance/cmfPaystate/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfPaystate cmfPaystate) throws IOException {
        return new ResponseEntity<>(iCmfPaystateService.selectCmfPaystateList(cmfPaystate), HttpStatus.OK);
    }

    @ApiOperation(value = "支付状态记录表", notes = "获取支付状态记录表详情")
    @ApiResponse(code = 400, message = "获取支付状态详情失败")
    @RequestMapping(value = "/finance/cmfPaystate/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfPaystateService.selectCmfPaystateById(id),HttpStatus.OK);
    }
}
