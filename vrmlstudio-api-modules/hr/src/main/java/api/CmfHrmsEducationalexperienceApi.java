package api;

import com.vrmlstudio.hr.domain.CmfHrmsEducationalexperience;
import com.vrmlstudio.hr.service.ICmfHrmsEducationalexperienceService;
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
 * 教育经验记录表
 */
@Api(value = "教育经验记录表")
@RestController
public class CmfHrmsEducationalexperienceApi {

    @Autowired
    private ICmfHrmsEducationalexperienceService iCmfHrmsEducationalexperienceService;

    @ApiOperation(value = "教育经验记录表", notes = "获取教育经验记录表")
    @ApiResponse(code = 400, message = "获取教育经验记录失败")
    @RequestMapping(value = "/hr/cmfHrmsEducationalexperience/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsEducationalexperience cmfHrmsEducationalexperience) throws IOException {
        return new ResponseEntity<>(iCmfHrmsEducationalexperienceService.selectCmfHrmsEducationalexperienceList(cmfHrmsEducationalexperience), HttpStatus.OK);
    }

    @ApiOperation(value = "教育经验记录表", notes = "获取教育经验记录表详情")
    @ApiResponse(code = 400, message = "获取教育经验详情失败")
    @RequestMapping(value = "/hr/cmfHrmsEducationalexperience/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsEducationalexperienceService.selectCmfHrmsEducationalexperienceById(id),HttpStatus.OK);
    }
}
