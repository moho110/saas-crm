package api;

import com.vrmlstudio.finance.domain.CmfAccessbank;
import com.vrmlstudio.finance.service.ICmfAccessbankService;
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
 * 银行帐户记录表
 */
@Api(value = "银行帐户记录表")
@RestController
public class CmfAccessbankApi {

    @Autowired
    private ICmfAccessbankService iCmfAccessbankService;

    @ApiOperation(value = "银行帐户记录表", notes = "获取银行帐户记录表")
    @ApiResponse(code = 400, message = "获取银行帐户记录失败")
    @RequestMapping(value = "/finance/cmfAccessbank/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfAccessbank cmfAccessbank) throws IOException {
        return new ResponseEntity<>(iCmfAccessbankService.selectCmfAccessbankList(cmfAccessbank), HttpStatus.OK);
    }

    @ApiOperation(value = "银行帐户记录表", notes = "获取银行帐户记录表详情")
    @ApiResponse(code = 400, message = "获取银行帐户详情失败")
    @RequestMapping(value = "/finance/cmfAccessbank/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfAccessbankService.selectCmfAccessbankById(id),HttpStatus.OK);
    }
}
