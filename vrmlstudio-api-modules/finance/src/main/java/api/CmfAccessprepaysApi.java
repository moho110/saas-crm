package api;

import com.vrmlstudio.finance.domain.CmfAccessprepays;
import com.vrmlstudio.finance.service.ICmfAccessprepaysService;
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
 * 预支付记录表
 */
@Api(value = "预支付记录表")
@RestController
public class CmfAccessprepaysApi {

    @Autowired
    private ICmfAccessprepaysService iCmfAccessprepaysService;

    @ApiOperation(value = "预支付记录表", notes = "获取预支付记录表")
    @ApiResponse(code = 400, message = "获取预支付记录失败")
    @RequestMapping(value = "/finance/cmfAccessprepays/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfAccessprepays cmfAccessprepays) throws IOException {
        return new ResponseEntity<>(iCmfAccessprepaysService.selectCmfAccessprepaysList(cmfAccessprepays), HttpStatus.OK);
    }

    @ApiOperation(value = "预支付记录表", notes = "获取预支付记录表详情")
    @ApiResponse(code = 400, message = "获取预支付详情失败")
    @RequestMapping(value = "/finance/cmfAccessprepays/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfAccessprepaysService.selectCmfAccessprepaysById(id),HttpStatus.OK);
    }
}
