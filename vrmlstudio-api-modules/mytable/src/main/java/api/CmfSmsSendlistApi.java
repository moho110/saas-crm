package api;

import com.vrmlstudio.mytable.domain.CmfSmsSendlist;
import com.vrmlstudio.mytable.service.ICmfSmsSendlistService;
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
 * 短信发送记录表
 */
@Api(value = "短信发送记录表")
@RestController
public class CmfSmsSendlistApi {

    @Autowired
    private ICmfSmsSendlistService iCmfSmsSendlistService;

    @ApiOperation(value = "短信发送记录表", notes = "获取短信发送记录表")
    @ApiResponse(code = 400, message = "获取短信发送记录失败")
    @RequestMapping(value = "/mytable/cmfSmsSendlist/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSmsSendlist cmfSmsSendlist) throws IOException {
        return new ResponseEntity<>(iCmfSmsSendlistService.selectCmfSmsSendlistList(cmfSmsSendlist), HttpStatus.OK);
    }

    @ApiOperation(value = "短信发送记录表", notes = "获取短信发送记录表详情")
    @ApiResponse(code = 400, message = "获取短信发送详情失败")
    @RequestMapping(value = "/mytable/cmfSmsSendlist/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSmsSendlistService.selectCmfSmsSendlistById(id),HttpStatus.OK);
    }
}
