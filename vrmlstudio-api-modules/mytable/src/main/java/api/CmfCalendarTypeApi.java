package api;

import com.vrmlstudio.mytable.domain.CmfCalendarType;
import com.vrmlstudio.mytable.service.ICmfCalendarTypeService;
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
 * 日历类型记录表
 */
@Api(value = "日历类型记录表")
@RestController
public class CmfCalendarTypeApi {

    @Autowired
    private ICmfCalendarTypeService iCmfCalendarTypeService;

    @ApiOperation(value = "日历类型记录表", notes = "获取日历类型记录表")
    @ApiResponse(code = 400, message = "获取日历类型记录失败")
    @RequestMapping(value = "/mytable/cmfCalendarType/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCalendarType cmfCalendarType) throws IOException {
        return new ResponseEntity<>(iCmfCalendarTypeService.selectCmfCalendarTypeList(cmfCalendarType), HttpStatus.OK);
    }

    @ApiOperation(value = "日历类型记录表", notes = "获取日历类型记录表详情")
    @ApiResponse(code = 400, message = "获取日历类型详情失败")
    @RequestMapping(value = "/mytable/cmfCalendarType/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCalendarTypeService.selectCmfCalendarTypeById(id),HttpStatus.OK);
    }
}
