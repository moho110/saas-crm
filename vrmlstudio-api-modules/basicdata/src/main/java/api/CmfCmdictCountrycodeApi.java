package api;

import com.vrmlstudio.basicdata.domain.CmfCmdictCountrycode;
import com.vrmlstudio.basicdata.service.ICmfCmdictCountrycodeService;
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
@Api(value = "国家代码记录表")
@RestController
public class CmfCmdictCountrycodeApi {

    @Autowired
    private ICmfCmdictCountrycodeService iCmfCmdictCountrycodeService;

    @ApiOperation(value = "国家代码记录表", notes = "获取国家代码记录表")
    @ApiResponse(code = 400, message = "获取国家代码记录失败")
    @RequestMapping(value = "/basic/cmfCmdictCountrycode/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCmdictCountrycode cmfCmdictCountrycode) throws IOException {
        return new ResponseEntity<>(iCmfCmdictCountrycodeService.selectCmfCmdictCountrycodeList(cmfCmdictCountrycode), HttpStatus.OK);
    }

    @ApiOperation(value = "国家代码记录表", notes = "获取国家代码记录表详情")
    @ApiResponse(code = 400, message = "获取国家代码详情失败")
    @RequestMapping(value = "/basic/cmfCmdictCountrycode/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCmdictCountrycodeService.selectCmfCmdictCountrycodeById(id),HttpStatus.OK);
    }
}
