package api;

import com.vrmlstudio.finance.domain.CmfBankzhuru;
import com.vrmlstudio.finance.service.ICmfBankzhuruService;
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
 * 账户注入记录表
 */
@Api(value = "账户注入记录表")
@RestController
public class CmfBankzhuruzhuruApi {

    @Autowired
    private ICmfBankzhuruService iCmfBankzhuruService;

    @ApiOperation(value = "账户注入记录表", notes = "获取账户注入记录表")
    @ApiResponse(code = 400, message = "获取账户注入记录失败")
    @RequestMapping(value = "/finance/cmfBankzhuru/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfBankzhuru cmfBankzhuru) throws IOException {
        return new ResponseEntity<>(iCmfBankzhuruService.selectCmfBankzhuruList(cmfBankzhuru), HttpStatus.OK);
    }

    @ApiOperation(value = "账户注入记录表", notes = "获取账户注入记录表详情")
    @ApiResponse(code = 400, message = "获取账户注入详情失败")
    @RequestMapping(value = "/finance/cmfBankzhuru/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfBankzhuruService.selectCmfBankzhuruById(id),HttpStatus.OK);
    }
}
