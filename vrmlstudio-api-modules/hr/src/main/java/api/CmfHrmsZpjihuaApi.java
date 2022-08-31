package api;

import com.vrmlstudio.hr.domain.CmfHrmsZpjihua;
import com.vrmlstudio.hr.service.ICmfHrmsZpjihuaService;
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
 * 招聘计划记录表
 */
@Api(value = "招聘计划记录表")
@RestController
public class CmfHrmsZpjihuaApi {

    @Autowired
    private ICmfHrmsZpjihuaService iCmfHrmsZpjihuaService;

    @ApiOperation(value = "招聘计划记录表", notes = "获取招聘计划记录表")
    @ApiResponse(code = 400, message = "获取招聘计划记录失败")
    @RequestMapping(value = "/hr/cmfHrmsZpjihua/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsZpjihua cmfHrmsZpjihua) throws IOException {
        return new ResponseEntity<>(iCmfHrmsZpjihuaService.selectCmfHrmsZpjihuaList(cmfHrmsZpjihua), HttpStatus.OK);
    }

    @ApiOperation(value = "招聘计划记录表", notes = "获取招聘计划记录表详情")
    @ApiResponse(code = 400, message = "获取招聘计划详情失败")
    @RequestMapping(value = "/hr/cmfHrmsZpjihua/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsZpjihuaService.selectCmfHrmsZpjihuaById(id),HttpStatus.OK);
    }
}
