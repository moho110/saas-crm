package api;

import com.vrmlstudio.sales.domain.CmfCrmChanceRate;
import com.vrmlstudio.sales.service.ICmfCrmChanceRateService;
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
 * 销售机会成功率记录表
 */
@Api(value = "销售机会成功率记录表")
@RestController
public class CmfCrmChanceRateApi {

    @Autowired
    private ICmfCrmChanceRateService iCmfCrmChanceRateService;

    @ApiOperation(value = "销售机会成功率记录表", notes = "获取销售机会成功率记录表")
    @ApiResponse(code = 400, message = "获取销售机会成功率记录失败")
    @RequestMapping(value = "/sales/cmfCrmChanceRate/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmChanceRate cmfCrmChanceRate) throws IOException {
        return new ResponseEntity<>(iCmfCrmChanceRateService.selectCmfCrmChanceRateList(cmfCrmChanceRate), HttpStatus.OK);
    }

    @ApiOperation(value = "销售机会成功率记录表", notes = "获取销售机会成功率记录表详情")
    @ApiResponse(code = 400, message = "获取销售机会成功率详情失败")
    @RequestMapping(value = "/sales/cmfCrmChanceRate/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmChanceRateService.selectCmfCrmChanceRateById(id),HttpStatus.OK);
    }
}
