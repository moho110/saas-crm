package api;

import com.vrmlstudio.sales.domain.CmfFukuanplan;
import com.vrmlstudio.sales.service.ICmfFukuanplanService;
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
 * 付款计划记录表
 */
@Api(value = "付款计划记录表")
@RestController
public class CmfFukuanplanApi {

    @Autowired
    private ICmfFukuanplanService iCmfFukuanplanService;

    @ApiOperation(value = "付款计划记录表", notes = "获取付款计划记录表")
    @ApiResponse(code = 400, message = "获取付款计划记录失败")
    @RequestMapping(value = "/sales/cmfFukuanplan/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfFukuanplan cmfFukuanplan) throws IOException {
        return new ResponseEntity<>(iCmfFukuanplanService.selectCmfFukuanplanList(cmfFukuanplan), HttpStatus.OK);
    }

    @ApiOperation(value = "付款计划记录表", notes = "获取付款计划记录表详情")
    @ApiResponse(code = 400, message = "获取付款计划详情失败")
    @RequestMapping(value = "/sales/cmfFukuanplan/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfFukuanplanService.selectCmfFukuanplanById(id),HttpStatus.OK);
    }
}
