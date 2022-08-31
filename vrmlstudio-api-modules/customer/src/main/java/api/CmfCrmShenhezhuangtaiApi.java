package api;

import com.vrmlstudio.customer.domain.CmfCrmShenhezhuangtai;
import com.vrmlstudio.customer.service.ICmfCrmShenhezhuangtaiService;
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
 * 审核状态记录表
 */
@Api(value = "审核状态记录表")
@RestController
public class CmfCrmShenhezhuangtaiApi {

    @Autowired
    private ICmfCrmShenhezhuangtaiService iCmfCrmShenhezhuangtaiService;

    @ApiOperation(value = "审核状态记录表", notes = "获取审核状态记录表")
    @ApiResponse(code = 400, message = "获取审核状态记录失败")
    @RequestMapping(value = "/customer/cmfCrmShenhezhuangtai/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmShenhezhuangtai cmfCrmShenhezhuangtai) throws IOException {
        return new ResponseEntity<>(iCmfCrmShenhezhuangtaiService.selectCmfCrmShenhezhuangtaiList(cmfCrmShenhezhuangtai), HttpStatus.OK);
    }

    @ApiOperation(value = "审核状态记录表", notes = "获取审核状态记录表详情")
    @ApiResponse(code = 400, message = "获取审核状态详情失败")
    @RequestMapping(value = "/customer/cmfCrmShenhezhuangtai/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmShenhezhuangtaiService.selectCmfCrmShenhezhuangtaiById(id),HttpStatus.OK);
    }
}
