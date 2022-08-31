package api;

import com.vrmlstudio.basicdata.domain.CmfSupplylever;
import com.vrmlstudio.basicdata.service.ICmfSupplyleverService;
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
 * 供应商等级记录表
 */
@Api(value = "供应商等级记录表")
@RestController
public class CmfSupplyleverApi {

    @Autowired
    private ICmfSupplyleverService iCmfSupplyleverService;

    @ApiOperation(value = "供应商等级记录表", notes = "获取供应商等级记录表")
    @ApiResponse(code = 400, message = "获取供应商等级记录失败")
    @RequestMapping(value = "/basic/cmfSupplylever/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSupplylever cmfSupplylever) throws IOException {
        return new ResponseEntity<>(iCmfSupplyleverService.selectCmfSupplyleverList(cmfSupplylever), HttpStatus.OK);
    }

    @ApiOperation(value = "供应商等级记录表", notes = "获取供应商等级记录表详情")
    @ApiResponse(code = 400, message = "获取供应商等级详情失败")
    @RequestMapping(value = "/basic/cmfSupplylever/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSupplyleverService.selectCmfSupplyleverById(id),HttpStatus.OK);
    }
}
