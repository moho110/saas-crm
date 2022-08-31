package api;

import com.vrmlstudio.basicdata.domain.CmfProduct;
import com.vrmlstudio.basicdata.service.ICmfProductService;
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
 * 产品记录表
 */
@Api(value = "产品记录表")
@RestController
public class CmfProductApi {

    @Autowired
    private ICmfProductService iCmfProductService;

    @ApiOperation(value = "产品记录表", notes = "获取产品记录表")
    @ApiResponse(code = 400, message = "获取产品记录失败")
    @RequestMapping(value = "/basic/cmfProduct/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfProduct cmfProduct) throws IOException {
        return new ResponseEntity<>(iCmfProductService.selectCmfProductList(cmfProduct), HttpStatus.OK);
    }

    @ApiOperation(value = "产品记录表", notes = "获取产品记录表详情")
    @ApiResponse(code = 400, message = "获取产品详情失败")
    @RequestMapping(value = "/basic/cmfProduct/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfProductService.selectCmfProductById(id),HttpStatus.OK);
    }
}
