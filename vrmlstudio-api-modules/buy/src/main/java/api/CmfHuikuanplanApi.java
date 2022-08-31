package api;

import com.vrmlstudio.buy.domain.CmfHuikuanplan;
import com.vrmlstudio.buy.service.ICmfHuikuanplanService;
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
 * 汇款计划记录表
 */
@Api(value = "汇款计划记录表")
@RestController
public class CmfHuikuanplanApi {

    @Autowired
    private ICmfHuikuanplanService iCmfHuikuanplanService;

    @ApiOperation(value = "汇款计划记录表", notes = "获取汇款计划记录表")
    @ApiResponse(code = 400, message = "获取汇款计划记录失败")
    @RequestMapping(value = "/buy/cmfHuikuanplan/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHuikuanplan cmfHuikuanplan) throws IOException {
        return new ResponseEntity<>(iCmfHuikuanplanService.selectCmfHuikuanplanList(cmfHuikuanplan), HttpStatus.OK);
    }

    @ApiOperation(value = "汇款计划记录表", notes = "获取汇款计划记录表详情")
    @ApiResponse(code = 400, message = "获取汇款计划详情失败")
    @RequestMapping(value = "/buy/cmfHuikuanplan/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHuikuanplanService.selectCmfHuikuanplanById(id),HttpStatus.OK);
    }
}
