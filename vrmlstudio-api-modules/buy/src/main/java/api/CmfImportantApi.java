package api;

import com.vrmlstudio.buy.domain.CmfImportant;
import com.vrmlstudio.buy.service.ICmfImportantService;
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
 * 重要性记录表
 */
@Api(value = "重要性记录表")
@RestController
public class CmfImportantApi {

    @Autowired
    private ICmfImportantService iCmfImportantService;

    @ApiOperation(value = "重要性记录表", notes = "获取重要性记录表")
    @ApiResponse(code = 400, message = "获取银行帐户记录失败")
    @RequestMapping(value = "/buy/cmfImportant/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfImportant cmfImportant) throws IOException {
        return new ResponseEntity<>(iCmfImportantService.selectCmfImportantList(cmfImportant), HttpStatus.OK);
    }

    @ApiOperation(value = "重要性记录表", notes = "获取重要性记录表详情")
    @ApiResponse(code = 400, message = "获取银行帐户详情失败")
    @RequestMapping(value = "/buy/cmfImportant/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfImportantService.selectCmfImportantById(id),HttpStatus.OK);
    }
}
