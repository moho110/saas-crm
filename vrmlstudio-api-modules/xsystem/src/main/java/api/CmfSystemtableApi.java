package api;

import com.vrmlstudio.system.domain.CmfSystemtable;
import com.vrmlstudio.system.service.ICmfSystemtableService;
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
 * 系统表记录表
 */
@Api(value = "系统表记录表")
@RestController
public class CmfSystemtableApi {

    @Autowired
    private ICmfSystemtableService iCmfSystemtableService;

    @ApiOperation(value = "系统表记录表", notes = "获取系统表记录表")
    @ApiResponse(code = 400, message = "获取系统表记录失败")
    @RequestMapping(value = "/xsystem/cmfSystemtable/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSystemtable cmfSystemtable) throws IOException {
        return new ResponseEntity<>(iCmfSystemtableService.selectCmfSystemtableList(cmfSystemtable), HttpStatus.OK);
    }

    @ApiOperation(value = "系统表记录表", notes = "获取系统表记录表详情")
    @ApiResponse(code = 400, message = "获取系统表详情失败")
    @RequestMapping(value = "/xsystem/cmfSystemtable/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSystemtableService.selectCmfSystemtableById(id),HttpStatus.OK);
    }
}
