package api;

import com.vrmlstudio.mytable.domain.CmfWorkplanstate;
import com.vrmlstudio.mytable.service.ICmfWorkplanstateService;
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
 * 工作计划状态记录表
 */
@Api(value = "工作计划状态记录表")
@RestController
public class CmfWorkplanstateApi {

    @Autowired
    private ICmfWorkplanstateService iCmfWorkplanstateService;

    @ApiOperation(value = "工作计划状态记录表", notes = "获取工作计划状态记录表")
    @ApiResponse(code = 400, message = "获取工作计划状态记录失败")
    @RequestMapping(value = "/mytable/cmfWorkplanstate/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfWorkplanstate cmfWorkplanstate) throws IOException {
        return new ResponseEntity<>(iCmfWorkplanstateService.selectCmfWorkplanstateList(cmfWorkplanstate), HttpStatus.OK);
    }

    @ApiOperation(value = "工作计划状态记录表", notes = "获取工作计划状态记录表详情")
    @ApiResponse(code = 400, message = "获取工作计划状态详情失败")
    @RequestMapping(value = "/mytable/cmfWorkplanstate/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfWorkplanstateService.selectCmfWorkplanstateById(id),HttpStatus.OK);
    }
}
