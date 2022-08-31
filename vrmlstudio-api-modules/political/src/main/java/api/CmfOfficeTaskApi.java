package api;

import com.vrmlstudio.political.domain.CmfOfficeTask;
import com.vrmlstudio.political.service.ICmfOfficeTaskService;
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
 * 办公任务记录表
 */
@Api(value = "办公任务记录表")
@RestController
public class CmfOfficeTaskApi {

    @Autowired
    private ICmfOfficeTaskService iCmfOfficeTaskService;

    @ApiOperation(value = "办公任务记录表", notes = "获取办公任务记录表")
    @ApiResponse(code = 400, message = "获取办公任务记录失败")
    @RequestMapping(value = "/political/cmfOfficeTask/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfOfficeTask cmfOfficeTask) throws IOException {
        return new ResponseEntity<>(iCmfOfficeTaskService.selectCmfOfficeTaskList(cmfOfficeTask), HttpStatus.OK);
    }

    @ApiOperation(value = "办公任务记录表", notes = "获取办公任务记录表详情")
    @ApiResponse(code = 400, message = "获取办公任务详情失败")
    @RequestMapping(value = "/political/cmfOfficeTask/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfOfficeTaskService.selectCmfOfficeTaskById(id),HttpStatus.OK);
    }
}
