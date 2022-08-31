package api;

import com.vrmlstudio.store.domain.CmfStockchangemain;
import com.vrmlstudio.store.service.ICmfStockchangemainService;
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
 * 库存调拔记录表
 */
@Api(value = "库存调拔记录表")
@RestController
public class CmfStockchangemainApi {

    @Autowired
    private ICmfStockchangemainService iCmfStockchangemainService;

    @ApiOperation(value = "库存调拔记录表", notes = "获取库存调拔记录表")
    @ApiResponse(code = 400, message = "获取库存调拔记录失败")
    @RequestMapping(value = "/store/cmfStockchangemain/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfStockchangemain cmfStockchangemain) throws IOException {
        return new ResponseEntity<>(iCmfStockchangemainService.selectCmfStockchangemainList(cmfStockchangemain), HttpStatus.OK);
    }

    @ApiOperation(value = "库存调拔记录表", notes = "获取库存调拔记录表详情")
    @ApiResponse(code = 400, message = "获取库存调拔详情失败")
    @RequestMapping(value = "/store/cmfStockchangemain/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfStockchangemainService.selectCmfStockchangemainById(id),HttpStatus.OK);
    }
}
