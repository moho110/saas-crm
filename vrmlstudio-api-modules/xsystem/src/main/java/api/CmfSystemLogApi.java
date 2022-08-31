package api;

import com.vrmlstudio.system.domain.CmfSystemLog;
import com.vrmlstudio.system.service.ICmfSystemLogService;
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
public class CmfSystemLogApi {

    @Autowired
    private ICmfSystemLogService iCmfSystemLogService;

    @ApiOperation(value = "系统日志记录表", notes = "获取系统日志记录表")
    @ApiResponse(code = 400, message = "获取系统日志记录失败")
    @RequestMapping(value = "/xsystem/cmfSystemLog/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSystemLog cmfSystemLog) throws IOException {
        return new ResponseEntity<>(iCmfSystemLogService.selectCmfSystemLogList(cmfSystemLog), HttpStatus.OK);
    }

    @ApiOperation(value = "系统日志记录表", notes = "获取系统日志记录表详情")
    @ApiResponse(code = 400, message = "获取系统日志详情失败")
    @RequestMapping(value = "/xsystem/cmfSystemLog/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSystemLogService.selectCmfSystemLogById(id),HttpStatus.OK);
    }
}
