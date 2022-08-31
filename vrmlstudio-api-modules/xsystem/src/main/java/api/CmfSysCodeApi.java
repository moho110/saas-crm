package api;

import com.vrmlstudio.system.domain.CmfSysCode;
import com.vrmlstudio.system.service.ICmfSysCodeService;
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
 * 系统代码记录表
 */
@Api(value = "系统代码记录表")
@RestController
public class CmfSysCodeApi {

    @Autowired
    private ICmfSysCodeService iCmfSysCodeService;

    @ApiOperation(value = "系统代码记录表", notes = "获取系统代码记录表")
    @ApiResponse(code = 400, message = "获取系统代码记录失败")
    @RequestMapping(value = "/xsystem/cmfSysCode/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSysCode cmfSysCode) throws IOException {
        return new ResponseEntity<>(iCmfSysCodeService.selectCmfSysCodeList(cmfSysCode), HttpStatus.OK);
    }

    @ApiOperation(value = "系统代码记录表", notes = "获取系统代码记录表详情")
    @ApiResponse(code = 400, message = "获取系统代码详情失败")
    @RequestMapping(value = "/xsystem/cmfSysCode/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSysCodeService.selectCmfSysCodeById(id),HttpStatus.OK);
    }
}
