package api;

import com.vrmlstudio.hr.domain.CmfEduXingzhengKaoqinbudeng;
import com.vrmlstudio.hr.service.ICmfEduXingzhengKaoqinbudengService;
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
 * 考勤补登记录表
 */
@Api(value = "考勤补登记录表")
@RestController
public class CmfEduXingzhengKaoqinbudengApi {

    @Autowired
    private ICmfEduXingzhengKaoqinbudengService iCmfEduXingzhengKaoqinbudengService;

    @ApiOperation(value = "考勤补登记录表", notes = "获取考勤补登记录表")
    @ApiResponse(code = 400, message = "获取考勤补登记录失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengKaoqinbudeng/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduXingzhengKaoqinbudeng cmfEduXingzhengKaoqinbudeng) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengKaoqinbudengService.selectCmfEduXingzhengKaoqinbudengList(cmfEduXingzhengKaoqinbudeng), HttpStatus.OK);
    }

    @ApiOperation(value = "考勤补登记录表", notes = "获取考勤补登记录表详情")
    @ApiResponse(code = 400, message = "获取考勤补登详情失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengKaoqinbudeng/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengKaoqinbudengService.selectCmfEduXingzhengKaoqinbudengById(id),HttpStatus.OK);
    }
}
