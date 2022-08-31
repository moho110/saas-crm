package api;

import com.vrmlstudio.basicdata.domain.CmfRecycleBin;
import com.vrmlstudio.basicdata.service.ICmfRecycleBinService;
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
 * 回收记录表
 */
@Api(value = "回收记录表")
@RestController
public class CmfRecycleBinApi {

    @Autowired
    private ICmfRecycleBinService iCmfRecycleBinService;

    @ApiOperation(value = "回收记录表", notes = "获取回收记录表")
    @ApiResponse(code = 400, message = "获取回收记录失败")
    @RequestMapping(value = "/basic/cmfRecycleBin/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfRecycleBin cmfRecycleBin) throws IOException {
        return new ResponseEntity<>(iCmfRecycleBinService.selectCmfRecycleBinList(cmfRecycleBin), HttpStatus.OK);
    }

    @ApiOperation(value = "回收记录表", notes = "获取回收记录表详情")
    @ApiResponse(code = 400, message = "获取回收详情失败")
    @RequestMapping(value = "/basic/cmfRecycleBin/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfRecycleBinService.selectCmfRecycleBinById(id),HttpStatus.OK);
    }
}
