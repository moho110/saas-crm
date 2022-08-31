package api;

import com.vrmlstudio.mytable.domain.CmfDictNotify;
import com.vrmlstudio.mytable.service.ICmfDictNotifyService;
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
 * 提醒记录表
 */
@Api(value = "提醒记录表")
@RestController
public class CmfDictNotifyApi {

    @Autowired
    private ICmfDictNotifyService iCmfDictNotifyService;

    @ApiOperation(value = "提醒记录表", notes = "获取提醒记录表")
    @ApiResponse(code = 400, message = "获取提醒记录失败")
    @RequestMapping(value = "/mytable/cmfDictNotify/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfDictNotify cmfDictNotify) throws IOException {
        return new ResponseEntity<>(iCmfDictNotifyService.selectCmfDictNotifyList(cmfDictNotify), HttpStatus.OK);
    }

    @ApiOperation(value = "提醒记录表", notes = "获取提醒记录表详情")
    @ApiResponse(code = 400, message = "获取提醒详情失败")
    @RequestMapping(value = "/mytable/cmfDictNotify/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfDictNotifyService.selectCmfDictNotifyById(id),HttpStatus.OK);
    }
}
