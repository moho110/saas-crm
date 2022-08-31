package api;

import com.vrmlstudio.customer.domain.CmfCustomerlever;
import com.vrmlstudio.customer.service.ICmfCustomerleverService;
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
 * 客户等级记录表
 */
@Api(value = "客户等级记录表")
@RestController
public class CmfCustomerleverApi {

    @Autowired
    private ICmfCustomerleverService iCmfCustomerleverService;

    @ApiOperation(value = "客户等级记录表", notes = "获取客户等级记录表")
    @ApiResponse(code = 400, message = "获取客户等级记录失败")
    @RequestMapping(value = "/customer/cmfCustomerlever/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCustomerlever cmfCustomerlever) throws IOException {
        return new ResponseEntity<>(iCmfCustomerleverService.selectCmfCustomerleverList(cmfCustomerlever), HttpStatus.OK);
    }

    @ApiOperation(value = "客户等级记录表", notes = "获取客户等级记录表详情")
    @ApiResponse(code = 400, message = "获取客户等级详情失败")
    @RequestMapping(value = "/customer/cmfCustomerlever/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCustomerleverService.selectCmfCustomerleverById(id),HttpStatus.OK);
    }
}
