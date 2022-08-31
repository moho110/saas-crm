package api;

import com.vrmlstudio.hr.domain.CmfHrmsFile;
import com.vrmlstudio.hr.service.ICmfHrmsFileService;
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
 * 人力资源记录表
 */
@Api(value = "人力资源记录表")
@RestController
public class CmfHrmsFileApi {

    @Autowired
    private ICmfHrmsFileService iCmfHrmsFileService;

    @ApiOperation(value = "人力资源记录表", notes = "获取人力资源记录表")
    @ApiResponse(code = 400, message = "获取人力资源记录失败")
    @RequestMapping(value = "/hr/cmfHrmsFile/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsFile cmfHrmsFile) throws IOException {
        return new ResponseEntity<>(iCmfHrmsFileService.selectCmfHrmsFileList(cmfHrmsFile), HttpStatus.OK);
    }

    @ApiOperation(value = "人力资源记录表", notes = "获取人力资源记录表详情")
    @ApiResponse(code = 400, message = "获取人力资源详情失败")
    @RequestMapping(value = "/hr/cmfHrmsFile/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsFileService.selectCmfHrmsFileById(id),HttpStatus.OK);
    }
}
