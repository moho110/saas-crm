package api;

import com.vrmlstudio.basicdata.domain.CmfDepartment;
import com.vrmlstudio.basicdata.service.ICmfDepartmentService;
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
 * 部门管理记录表
 */
@Api(value = "部门管理记录表")
@RestController
public class CmfDepartmentApi {

    @Autowired
    private ICmfDepartmentService iCmfDepartmentService;

    @ApiOperation(value = "部门管理记录表", notes = "获取部门管理记录表")
    @ApiResponse(code = 400, message = "获取部门管理记录失败")
    @RequestMapping(value = "/basic/cmfDepartment/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfDepartment cmfDepartment) throws IOException {
        return new ResponseEntity<>(iCmfDepartmentService.selectCmfDepartmentList(cmfDepartment), HttpStatus.OK);
    }

    @ApiOperation(value = "部门管理记录表", notes = "获取部门管理记录表详情")
    @ApiResponse(code = 400, message = "获取部门管理详情失败")
    @RequestMapping(value = "/basic/cmfDepartment/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfDepartmentService.selectCmfDepartmentById(id),HttpStatus.OK);
    }
}
