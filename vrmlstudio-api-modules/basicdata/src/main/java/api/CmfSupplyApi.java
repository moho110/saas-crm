package api;

import com.vrmlstudio.basicdata.domain.CmfSupply;
import com.vrmlstudio.basicdata.service.ICmfSupplyService;
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
 * 供应商记录表
 */
@Api(value = "供应商记录表")
@RestController
public class CmfSupplyApi {

    @Autowired
    private ICmfSupplyService iCmfSupplyService;

    @ApiOperation(value = "供应商记录表", notes = "获取供应商记录表")
    @ApiResponse(code = 400, message = "获取供应商记录失败")
    @RequestMapping(value = "/basic/cmfSupply/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSupply cmfSupply) throws IOException {
        return new ResponseEntity<>(iCmfSupplyService.selectCmfSupplyList(cmfSupply), HttpStatus.OK);
    }

    @ApiOperation(value = "供应商记录表", notes = "获取供应商记录表详情")
    @ApiResponse(code = 400, message = "获取供应商详情失败")
    @RequestMapping(value = "/basic/cmfSupply/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSupplyService.selectCmfSupplyById(id),HttpStatus.OK);
    }
}
