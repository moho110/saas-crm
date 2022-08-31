package api;

import com.vrmlstudio.mytable.domain.CmfEmailstate;
import com.vrmlstudio.mytable.service.ICmfEmailstateService;
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
 * 邮件状态记录表
 */
@Api(value = "邮件状态记录表")
@RestController
public class CmfEmailstateApi {

    @Autowired
    private ICmfEmailstateService iCmfEmailstateService;

    @ApiOperation(value = "邮件状态记录表", notes = "获取邮件状态记录表")
    @ApiResponse(code = 400, message = "获取邮件状态记录失败")
    @RequestMapping(value = "/mytable/cmfEmailstate/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEmailstate cmfEmailstate) throws IOException {
        return new ResponseEntity<>(iCmfEmailstateService.selectCmfEmailstateList(cmfEmailstate), HttpStatus.OK);
    }

    @ApiOperation(value = "邮件状态记录表", notes = "获取邮件状态记录表详情")
    @ApiResponse(code = 400, message = "获取邮件状态详情失败")
    @RequestMapping(value = "/mytable/cmfEmailstate/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEmailstateService.selectCmfEmailstateById(id),HttpStatus.OK);
    }
}
