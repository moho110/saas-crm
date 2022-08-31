package api;

import com.vrmlstudio.system.domain.CmfSystemLogall;
import com.vrmlstudio.system.service.ICmfSystemLogallService;
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
 * 系统日志记录表
 */
@Api(value = "系统日志记录表")
@RestController
public class CmfSystemLogallApi {

    @Autowired
    private ICmfSystemLogallService iCmfSystemLogallService;

    @ApiOperation(value = "系统日志记录表", notes = "获取系统日志记录表")
    @ApiResponse(code = 400, message = "获取系统日志记录失败")
    @RequestMapping(value = "/xsystem/cmfSystemLogall/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSystemLogall cmfSystemLogall) throws IOException {
        return new ResponseEntity<>(iCmfSystemLogallService.selectCmfSystemLogallList(cmfSystemLogall), HttpStatus.OK);
    }

    @ApiOperation(value = "系统日志记录表", notes = "获取系统日志记录表详情")
    @ApiResponse(code = 400, message = "获取系统日志详情失败")
    @RequestMapping(value = "/xsystem/cmfSystemLogall/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSystemLogallService.selectCmfSystemLogallById(id),HttpStatus.OK);
    }
}
