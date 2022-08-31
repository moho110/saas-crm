package api;

import com.vrmlstudio.hr.domain.CmfEduTeacherkaoqin;
import com.vrmlstudio.hr.service.ICmfEduTeacherkaoqinService;
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
 * 教师考勤记录表
 */
@Api(value = "教师考勤记录表")
@RestController
public class CmfEduTeacherkaoqinApi {

    @Autowired
    private ICmfEduTeacherkaoqinService iCmfEduTeacherkaoqinService;

    @ApiOperation(value = "教师考勤记录表", notes = "获取教师考勤记录表")
    @ApiResponse(code = 400, message = "获取教师考勤记录失败")
    @RequestMapping(value = "/hr/cmfEduTeacherkaoqin/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduTeacherkaoqin cmfEduTeacherkaoqin) throws IOException {
        return new ResponseEntity<>(iCmfEduTeacherkaoqinService.selectCmfEduTeacherkaoqinList(cmfEduTeacherkaoqin), HttpStatus.OK);
    }

    @ApiOperation(value = "教师考勤记录表", notes = "获取教师考勤记录表详情")
    @ApiResponse(code = 400, message = "获取教师考勤详情失败")
    @RequestMapping(value = "/hr/cmfEduTeacherkaoqin/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduTeacherkaoqinService.selectCmfEduTeacherkaoqinById(id),HttpStatus.OK);
    }
}
