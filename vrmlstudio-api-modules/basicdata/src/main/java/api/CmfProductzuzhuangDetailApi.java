package api;

import com.vrmlstudio.basicdata.domain.CmfProductzuzhuangDetail;
import com.vrmlstudio.basicdata.service.ICmfProductzuzhuangDetailService;
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
 * 产品组装明细记录表
 */
@Api(value = "产品组装明细记录表")
@RestController
public class CmfProductzuzhuangDetailApi {

    @Autowired
    private ICmfProductzuzhuangDetailService iCmfProductzuzhuangDetailService;

    @ApiOperation(value = "产品组装明细记录表", notes = "获取产品组装明细记录表")
    @ApiResponse(code = 400, message = "获取产品组装明细记录失败")
    @RequestMapping(value = "/basic/cmfProductzuzhuangDetail/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfProductzuzhuangDetail cmfProductzuzhuangDetail) throws IOException {
        return new ResponseEntity<>(iCmfProductzuzhuangDetailService.selectCmfProductzuzhuangDetailList(cmfProductzuzhuangDetail), HttpStatus.OK);
    }

    @ApiOperation(value = "产品组装明细记录表", notes = "获取产品组装明细记录表详情")
    @ApiResponse(code = 400, message = "获取产品组装明细详情失败")
    @RequestMapping(value = "/basic/cmfProductzuzhuangDetail/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfProductzuzhuangDetailService.selectCmfProductzuzhuangDetailById(id),HttpStatus.OK);
    }
}
