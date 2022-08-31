package api;

import com.vrmlstudio.hr.domain.CmfHrmsWorkexperience;
import com.vrmlstudio.hr.service.ICmfHrmsWorkexperienceService;
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
 * 工作经验记录表
 */
@Api(value = "工作经验记录表")
@RestController
public class CmfHrmsWorkexperienceApi {

    @Autowired
    private ICmfHrmsWorkexperienceService iCmfHrmsWorkexperienceService;

    @ApiOperation(value = "工作经验记录表", notes = "获取工作经验记录表")
    @ApiResponse(code = 400, message = "获取工作经验记录失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkexperience/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsWorkexperience cmfHrmsWorkexperience) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkexperienceService.selectCmfHrmsWorkexperienceList(cmfHrmsWorkexperience), HttpStatus.OK);
    }

    @ApiOperation(value = "工作经验记录表", notes = "获取工作经验记录表详情")
    @ApiResponse(code = 400, message = "获取工作经验详情失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkexperience/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkexperienceService.selectCmfHrmsWorkexperienceById(id),HttpStatus.OK);
    }
}
