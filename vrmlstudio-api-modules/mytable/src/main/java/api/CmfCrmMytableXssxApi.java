package api;

import com.vrmlstudio.mytable.domain.CmfCrmMytableXssx;
import com.vrmlstudio.mytable.service.ICmfCrmMytableXssxService;
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
 * 显示属性记录表
 */
@Api(value = "显示属性记录表")
@RestController
public class CmfCrmMytableXssxApi {

    @Autowired
    private ICmfCrmMytableXssxService iCmfCrmMytableXssxService;

    @ApiOperation(value = "显示属性记录表", notes = "获取显示属性记录表")
    @ApiResponse(code = 400, message = "获取显示属性记录失败")
    @RequestMapping(value = "/mytable/cmfCrmMytableXssx/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmMytableXssx cmfCrmMytableXssx) throws IOException {
        return new ResponseEntity<>(iCmfCrmMytableXssxService.selectCmfCrmMytableXssxList(cmfCrmMytableXssx), HttpStatus.OK);
    }

    @ApiOperation(value = "显示属性记录表", notes = "获取显示属性记录表详情")
    @ApiResponse(code = 400, message = "获取显示属性详情失败")
    @RequestMapping(value = "/mytable/cmfCrmMytableXssx/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmMytableXssxService.selectCmfCrmMytableXssxById(id),HttpStatus.OK);
    }
}
