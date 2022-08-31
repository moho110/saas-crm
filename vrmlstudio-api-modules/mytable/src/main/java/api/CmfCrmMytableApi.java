package api;

import com.vrmlstudio.mytable.domain.CmfCrmMytable;
import com.vrmlstudio.mytable.service.ICmfCrmMytableService;
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
 * 我的桌面记录表
 */
@Api(value = "我的桌面记录表")
@RestController
public class CmfCrmMytableApi {

    @Autowired
    private ICmfCrmMytableService iCmfCrmMytableService;

    @ApiOperation(value = "我的桌面记录表", notes = "获取我的桌面记录表")
    @ApiResponse(code = 400, message = "获取我的桌面记录失败")
    @RequestMapping(value = "/mytable/cmfCrmMytable/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmMytable cmfCrmMytable) throws IOException {
        return new ResponseEntity<>(iCmfCrmMytableService.selectCmfCrmMytableList(cmfCrmMytable), HttpStatus.OK);
    }

    @ApiOperation(value = "我的桌面记录表", notes = "获取我的桌面记录表详情")
    @ApiResponse(code = 400, message = "获取我的桌面详情失败")
    @RequestMapping(value = "/mytable/cmfCrmMytable/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmMytableService.selectCmfCrmMytableById(id),HttpStatus.OK);
    }
}
