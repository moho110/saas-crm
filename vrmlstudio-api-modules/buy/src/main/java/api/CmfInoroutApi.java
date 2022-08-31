package api;

import com.vrmlstudio.buy.domain.CmfInorout;
import com.vrmlstudio.buy.service.ICmfInoroutService;
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
 * 输入输出记录表
 */
@Api(value = "输入输出记录表")
@RestController
public class CmfInoroutApi {

    @Autowired
    private ICmfInoroutService iCmfInoroutService;

    @ApiOperation(value = "输入输出记录表", notes = "获取输入输出记录表")
    @ApiResponse(code = 400, message = "获取输入输出记录失败")
    @RequestMapping(value = "/buy/cmfInorout/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfInorout cmfInorout) throws IOException {
        return new ResponseEntity<>(iCmfInoroutService.selectCmfInoroutList(cmfInorout), HttpStatus.OK);
    }

    @ApiOperation(value = "输入输出记录表", notes = "获取输入输出记录表详情")
    @ApiResponse(code = 400, message = "获取输入输出详情失败")
    @RequestMapping(value = "/buy/cmfInorout/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfInoroutService.selectCmfInoroutById(id),HttpStatus.OK);
    }
}
