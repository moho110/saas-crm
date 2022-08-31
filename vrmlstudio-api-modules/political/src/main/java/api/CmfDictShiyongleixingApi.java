package api;

import com.vrmlstudio.political.domain.CmfDictShiyongleixing;
import com.vrmlstudio.political.service.ICmfDictShiyongleixingService;
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
 * 使用类型记录表
 */
@Api(value = "使用类型记录表")
@RestController
public class CmfDictShiyongleixingApi {

    @Autowired
    private ICmfDictShiyongleixingService iCmfDictShiyongleixingService;

    @ApiOperation(value = "使用类型记录表", notes = "获取使用类型记录表")
    @ApiResponse(code = 400, message = "获取使用类型记录失败")
    @RequestMapping(value = "/political/cmfDictShiyongleixing/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfDictShiyongleixing cmfDictShiyongleixing) throws IOException {
        return new ResponseEntity<>(iCmfDictShiyongleixingService.selectCmfDictShiyongleixingList(cmfDictShiyongleixing), HttpStatus.OK);
    }

    @ApiOperation(value = "使用类型记录表", notes = "获取使用类型记录表详情")
    @ApiResponse(code = 400, message = "获取使用类型详情失败")
    @RequestMapping(value = "/political/cmfDictShiyongleixing/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfDictShiyongleixingService.selectCmfDictShiyongleixingById(id),HttpStatus.OK);
    }
}
