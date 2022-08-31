package api;

import com.vrmlstudio.mytable.domain.CmfEmail;
import com.vrmlstudio.mytable.service.ICmfEmailService;
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
 * 邮件记录表
 */
@Api(value = "邮件记录表")
@RestController
public class CmfEmailApi {

    @Autowired
    private ICmfEmailService iCmfEmailService;

    @ApiOperation(value = "邮件记录表", notes = "获取邮件记录表")
    @ApiResponse(code = 400, message = "获取邮件记录失败")
    @RequestMapping(value = "/mytable/cmfEmail/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEmail cmfEmail) throws IOException {
        return new ResponseEntity<>(iCmfEmailService.selectCmfEmailList(cmfEmail), HttpStatus.OK);
    }

    @ApiOperation(value = "邮件记录表", notes = "获取邮件记录表详情")
    @ApiResponse(code = 400, message = "获取邮件详情失败")
    @RequestMapping(value = "/mytable/cmfEmail/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEmailService.selectCmfEmailById(id),HttpStatus.OK);
    }
}
