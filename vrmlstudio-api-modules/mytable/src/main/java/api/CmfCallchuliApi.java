package api;

import com.vrmlstudio.mytable.domain.CmfCallchuli;
import com.vrmlstudio.mytable.service.ICmfCallchuliService;
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
 * 呼叫处理记录表
 */
@Api(value = "呼叫处理记录表")
@RestController
public class CmfCallchuliApi {

    @Autowired
    private ICmfCallchuliService iCmfCallchuliService;

    @ApiOperation(value = "呼叫处理记录表", notes = "获取呼叫处理记录表")
    @ApiResponse(code = 400, message = "获取呼叫处理记录失败")
    @RequestMapping(value = "/mytable/cmfCallchuli/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCallchuli cmfCallchuli) throws IOException {
        return new ResponseEntity<>(iCmfCallchuliService.selectCmfCallchuliList(cmfCallchuli), HttpStatus.OK);
    }

    @ApiOperation(value = "呼叫处理记录表", notes = "获取呼叫处理记录表详情")
    @ApiResponse(code = 400, message = "获取呼叫处理详情失败")
    @RequestMapping(value = "/mytable/cmfCallchuli/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCallchuliService.selectCmfCallchuliById(id),HttpStatus.OK);
    }
}
