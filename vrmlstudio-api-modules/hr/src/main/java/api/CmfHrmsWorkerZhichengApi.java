package api;

import com.vrmlstudio.hr.domain.CmfHrmsWorkerZhicheng;
import com.vrmlstudio.hr.service.ICmfHrmsWorkerZhichengService;
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
 * 职称记录表
 */
@Api(value = "职称记录表")
@RestController
public class CmfHrmsWorkerZhichengApi {

    @Autowired
    private ICmfHrmsWorkerZhichengService iCmfHrmsWorkerZhichengService;

    @ApiOperation(value = "职称记录表", notes = "获取职称记录表")
    @ApiResponse(code = 400, message = "获取职称记录失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkerZhicheng/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsWorkerZhicheng cmfHrmsWorkerZhicheng) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkerZhichengService.selectCmfHrmsWorkerZhichengList(cmfHrmsWorkerZhicheng), HttpStatus.OK);
    }

    @ApiOperation(value = "职称记录表", notes = "获取职称记录表详情")
    @ApiResponse(code = 400, message = "获取职称详情失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkerZhicheng/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkerZhichengService.selectCmfHrmsWorkerZhichengById(id),HttpStatus.OK);
    }
}
