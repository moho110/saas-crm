package api;

import com.vrmlstudio.mytable.domain.CmfCalltype;
import com.vrmlstudio.mytable.service.ICmfCalltypeService;
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
 * 呼叫类型记录表
 */
@Api(value = "呼叫类型记录表")
@RestController
public class CmfCalltypeApi {

    @Autowired
    private ICmfCalltypeService iCmfCalltypeService;

    @ApiOperation(value = "呼叫类型记录表", notes = "获取呼叫类型记录表")
    @ApiResponse(code = 400, message = "获取呼叫类型记录失败")
    @RequestMapping(value = "/mytable/cmfCalltype/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCalltype cmfCalltype) throws IOException {
        return new ResponseEntity<>(iCmfCalltypeService.selectCmfCalltypeList(cmfCalltype), HttpStatus.OK);
    }

    @ApiOperation(value = "呼叫类型记录表", notes = "获取呼叫类型记录表详情")
    @ApiResponse(code = 400, message = "获取呼叫类型详情失败")
    @RequestMapping(value = "/mytable/cmfCalltype/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCalltypeService.selectCmfCalltypeById(id),HttpStatus.OK);
    }
}
