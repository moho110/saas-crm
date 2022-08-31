package api;

import com.vrmlstudio.system.domain.CmfSystemlang;
import com.vrmlstudio.system.service.ICmfSystemlangService;
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
 * 系统语言记录表
 */
@Api(value = "系统语言记录表")
@RestController
public class CmfSystemlangApi {

    @Autowired
    private ICmfSystemlangService iCmfSystemlangService;

    @ApiOperation(value = "系统语言记录表", notes = "获取系统语言记录表")
    @ApiResponse(code = 400, message = "获取系统语言记录失败")
    @RequestMapping(value = "/xsystem/cmfSystemlang/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSystemlang cmfSystemlang) throws IOException {
        return new ResponseEntity<>(iCmfSystemlangService.selectCmfSystemlangList(cmfSystemlang), HttpStatus.OK);
    }

    @ApiOperation(value = "系统语言记录表", notes = "获取系统语言记录表详情")
    @ApiResponse(code = 400, message = "获取系统语言详情失败")
    @RequestMapping(value = "/xsystem/cmfSystemlang/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSystemlangService.selectCmfSystemlangById(id),HttpStatus.OK);
    }
}
