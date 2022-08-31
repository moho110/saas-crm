package api;

import com.vrmlstudio.customer.domain.CmfCrmZhuangtai;
import com.vrmlstudio.customer.service.ICmfCrmZhuangtaiService;
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
 * 状态记录表
 */
@Api(value = "状态记录表")
@RestController
public class CmfCrmZhuangtaiApi {

    @Autowired
    private ICmfCrmZhuangtaiService iCmfCrmZhuangtaiService;

    @ApiOperation(value = "状态记录表", notes = "获取状态记录表")
    @ApiResponse(code = 400, message = "获取状态记录失败")
    @RequestMapping(value = "/customer/cmfCrmZhuangtai/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmZhuangtai cmfCrmZhuangtai) throws IOException {
        return new ResponseEntity<>(iCmfCrmZhuangtaiService.selectCmfCrmZhuangtaiList(cmfCrmZhuangtai), HttpStatus.OK);
    }

    @ApiOperation(value = "状态记录表", notes = "获取状态记录表详情")
    @ApiResponse(code = 400, message = "获取状态详情失败")
    @RequestMapping(value = "/customer/cmfCrmZhuangtai/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmZhuangtaiService.selectCmfCrmZhuangtaiById(id),HttpStatus.OK);
    }
}
