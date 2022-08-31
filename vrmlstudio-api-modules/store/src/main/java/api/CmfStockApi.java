package api;

import com.vrmlstudio.store.domain.CmfStock;
import com.vrmlstudio.store.service.ICmfStockService;
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
 * 库存记录表
 */
@Api(value = "库存记录表")
@RestController
public class CmfStockApi {

    @Autowired
    private ICmfStockService iCmfStockService;

    @ApiOperation(value = "库存记录表", notes = "获取库存记录表")
    @ApiResponse(code = 400, message = "获取库存记录失败")
    @RequestMapping(value = "/store/cmfStock/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfStock cmfStock) throws IOException {
        return new ResponseEntity<>(iCmfStockService.selectCmfStockList(cmfStock), HttpStatus.OK);
    }

    @ApiOperation(value = "库存记录表", notes = "获取库存记录表详情")
    @ApiResponse(code = 400, message = "获取库存详情失败")
    @RequestMapping(value = "/store/cmfStock/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfStockService.selectCmfStockById(id),HttpStatus.OK);
    }
}
