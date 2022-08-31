package api;

import com.vrmlstudio.basicdata.domain.CmfMessage;
import com.vrmlstudio.basicdata.service.ICmfMessageService;
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
 * 消息记录表
 */
@Api(value = "消息记录表")
@RestController
public class CmfMessageApi {

    @Autowired
    private ICmfMessageService iCmfMessageService;

    @ApiOperation(value = "消息记录表", notes = "获取消息记录表")
    @ApiResponse(code = 400, message = "获取消息记录失败")
    @RequestMapping(value = "/basic/cmfMessage/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfMessage cmfMessage) throws IOException {
        return new ResponseEntity<>(iCmfMessageService.selectCmfMessageList(cmfMessage), HttpStatus.OK);
    }

    @ApiOperation(value = "消息记录表", notes = "获取消息记录表详情")
    @ApiResponse(code = 400, message = "获取消息详情失败")
    @RequestMapping(value = "/basic/cmfMessage/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfMessageService.selectCmfMessageById(id),HttpStatus.OK);
    }
}
