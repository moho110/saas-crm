package api;

import com.vrmlstudio.political.domain.CmfWuBoolean;
import com.vrmlstudio.political.service.ICmfWuBooleanService;
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
 * 是否工作记录表
 */
@Api(value = "是否工作记录表")
@RestController
public class CmfWuBooleanApi {

    @Autowired
    private ICmfWuBooleanService iCmfWuBooleanService;

    @ApiOperation(value = "是否工作记录表", notes = "获取是否工作记录表")
    @ApiResponse(code = 400, message = "获取是否工作记录失败")
    @RequestMapping(value = "/political/cmfWuBoolean/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfWuBoolean cmfWuBoolean) throws IOException {
        return new ResponseEntity<>(iCmfWuBooleanService.selectCmfWuBooleanList(cmfWuBoolean), HttpStatus.OK);
    }

    @ApiOperation(value = "是否工作记录表", notes = "获取是否工作记录表详情")
    @ApiResponse(code = 400, message = "获取是否工作详情失败")
    @RequestMapping(value = "/political/cmfWuBoolean/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfWuBooleanService.selectCmfWuBooleanById(id),HttpStatus.OK);
    }
}
