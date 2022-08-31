package api;

import com.vrmlstudio.mytable.domain.CmfCallertype;
import com.vrmlstudio.mytable.service.ICmfCallertypeService;
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
 * 呼叫者类型记录表
 */
@Api(value = "呼叫者类型记录表")
@RestController
public class CmfCallertypeApi {

    @Autowired
    private ICmfCallertypeService iCmfCallertypeService;

    @ApiOperation(value = "呼叫者类型记录表", notes = "获取呼叫者类型记录表")
    @ApiResponse(code = 400, message = "获取呼叫者类型记录失败")
    @RequestMapping(value = "/mytable/cmfCallertype/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCallertype cmfCallertype) throws IOException {
        return new ResponseEntity<>(iCmfCallertypeService.selectCmfCallertypeList(cmfCallertype), HttpStatus.OK);
    }

    @ApiOperation(value = "呼叫者类型记录表", notes = "获取呼叫者类型记录表详情")
    @ApiResponse(code = 400, message = "获取呼叫者类型详情失败")
    @RequestMapping(value = "/mytable/cmfCallertype/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCallertypeService.selectCmfCallertypeById(id),HttpStatus.OK);
    }
}
