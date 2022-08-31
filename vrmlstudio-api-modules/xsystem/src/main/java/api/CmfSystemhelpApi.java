package api;

import com.vrmlstudio.system.domain.CmfSystemhelp;
import com.vrmlstudio.system.service.ICmfSystemhelpService;
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
 * 系统帮助记录表
 */
@Api(value = "系统帮助记录表")
@RestController
public class CmfSystemhelpApi {

    @Autowired
    private ICmfSystemhelpService iCmfSystemhelpService;

    @ApiOperation(value = "系统帮助记录表", notes = "获取系统帮助记录表")
    @ApiResponse(code = 400, message = "获取系统帮助记录失败")
    @RequestMapping(value = "/xsystem/cmfSystemhelp/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSystemhelp cmfSystemhelp) throws IOException {
        return new ResponseEntity<>(iCmfSystemhelpService.selectCmfSystemhelpList(cmfSystemhelp), HttpStatus.OK);
    }

    @ApiOperation(value = "系统帮助记录表", notes = "获取系统帮助记录表详情")
    @ApiResponse(code = 400, message = "获取系统帮助详情失败")
    @RequestMapping(value = "/xsystem/cmfSystemhelp/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSystemhelpService.selectCmfSystemhelpById(id),HttpStatus.OK);
    }
}
