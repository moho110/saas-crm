package api;

import com.vrmlstudio.hr.domain.CmfHrmsBoolean;
import com.vrmlstudio.hr.service.ICmfHrmsBooleanService;
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
 * 人力是否记录表
 */
@Api(value = "人力是否记录表")
@RestController
public class CmfHrmsBooleanApi {

    @Autowired
    private ICmfHrmsBooleanService iCmfHrmsBooleanService;

    @ApiOperation(value = "人力是否记录表", notes = "获取人力是否记录表")
    @ApiResponse(code = 400, message = "获取人力是否记录失败")
    @RequestMapping(value = "/hr/cmfHrmsBoolean/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsBoolean cmfHrmsBoolean) throws IOException {
        return new ResponseEntity<>(iCmfHrmsBooleanService.selectCmfHrmsBooleanList(cmfHrmsBoolean), HttpStatus.OK);
    }

    @ApiOperation(value = "人力是否记录表", notes = "获取人力是否记录表详情")
    @ApiResponse(code = 400, message = "获取人力是否详情失败")
    @RequestMapping(value = "/hr/cmfHrmsBoolean/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsBooleanService.selectCmfHrmsBooleanById(id),HttpStatus.OK);
    }
}
