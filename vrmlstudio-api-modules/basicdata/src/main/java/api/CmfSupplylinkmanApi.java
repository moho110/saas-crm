package api;

import com.vrmlstudio.basicdata.domain.CmfSupplylinkman;
import com.vrmlstudio.basicdata.service.ICmfSupplylinkmanService;
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
 * 供应商联系人记录表
 */
@Api(value = "供应商联系人记录表")
@RestController
public class CmfSupplylinkmanApi {

    @Autowired
    private ICmfSupplylinkmanService iCmfSupplylinkmanService;

    @ApiOperation(value = "供应商联系人记录表", notes = "获取供应商联系人记录表")
    @ApiResponse(code = 400, message = "获取供应商联系人记录失败")
    @RequestMapping(value = "/basic/cmfSupplylinkman/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSupplylinkman cmfSupplylinkman) throws IOException {
        return new ResponseEntity<>(iCmfSupplylinkmanService.selectCmfSupplylinkmanList(cmfSupplylinkman), HttpStatus.OK);
    }

    @ApiOperation(value = "供应商联系人记录表", notes = "获取供应商联系人记录表详情")
    @ApiResponse(code = 400, message = "获取供应商联系人详情失败")
    @RequestMapping(value = "/basic/cmfSupplylinkman/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSupplylinkmanService.selectCmfSupplylinkmanById(id),HttpStatus.OK);
    }
}
