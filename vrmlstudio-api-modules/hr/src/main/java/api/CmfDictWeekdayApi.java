package api;

import com.vrmlstudio.hr.domain.CmfDictWeekday;
import com.vrmlstudio.hr.service.ICmfDictWeekdayService;
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
 * 周日记录表
 */
@Api(value = "周日记录表")
@RestController
public class CmfDictWeekdayApi {

    @Autowired
    private ICmfDictWeekdayService iCmfDictWeekdayService;

    @ApiOperation(value = "周日记录表", notes = "获取周日记录表")
    @ApiResponse(code = 400, message = "获取周日记录失败")
    @RequestMapping(value = "/hr/cmfDictWeekday/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfDictWeekday cmfDictWeekday) throws IOException {
        return new ResponseEntity<>(iCmfDictWeekdayService.selectCmfDictWeekdayList(cmfDictWeekday), HttpStatus.OK);
    }

    @ApiOperation(value = "周日记录表", notes = "获取周日记录表详情")
    @ApiResponse(code = 400, message = "获取周日详情失败")
    @RequestMapping(value = "/hr/cmfDictWeekday/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfDictWeekdayService.selectCmfDictWeekdayById(id),HttpStatus.OK);
    }
}
