package api;

import com.vrmlstudio.hr.domain.CmfHrmsWorkStatus;
import com.vrmlstudio.hr.service.ICmfHrmsWorkStatusService;
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
 * 工作状态记录表
 */
@Api(value = "工作状态记录表")
@RestController
public class CmfHrmsWorkStatusApi {

    @Autowired
    private ICmfHrmsWorkStatusService iCmfHrmsWorkStatusService;

    @ApiOperation(value = "工作状态记录表", notes = "获取工作状态记录表")
    @ApiResponse(code = 400, message = "获取工作状态记录失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkStatus/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsWorkStatus cmfHrmsWorkStatus) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkStatusService.selectCmfHrmsWorkStatusList(cmfHrmsWorkStatus), HttpStatus.OK);
    }

    @ApiOperation(value = "工作状态记录表", notes = "获取工作状态记录表详情")
    @ApiResponse(code = 400, message = "获取工作状态详情失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkStatus/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkStatusService.selectCmfHrmsWorkStatusById(id),HttpStatus.OK);
    }
}
