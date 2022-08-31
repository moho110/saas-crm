package api;

import com.vrmlstudio.political.domain.CmfDictHuxing;
import com.vrmlstudio.political.service.ICmfDictHuxingService;
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
 * 户型记录表
 */
@Api(value = "户型记录表")
@RestController
public class CmfDictHuxingApi {

    @Autowired
    private ICmfDictHuxingService iCmfDictHuxingService;

    @ApiOperation(value = "户型记录表", notes = "获取户型记录表")
    @ApiResponse(code = 400, message = "获取户型记录失败")
    @RequestMapping(value = "/political/cmfDictHuxing/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfDictHuxing cmfDictHuxing) throws IOException {
        return new ResponseEntity<>(iCmfDictHuxingService.selectCmfDictHuxingList(cmfDictHuxing), HttpStatus.OK);
    }

    @ApiOperation(value = "户型记录表", notes = "获取户型记录表详情")
    @ApiResponse(code = 400, message = "获取户型详情失败")
    @RequestMapping(value = "/political/cmfDictHuxing/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfDictHuxingService.selectCmfDictHuxingById(id),HttpStatus.OK);
    }
}
