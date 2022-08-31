package api;

import com.vrmlstudio.store.domain.CmfStockoutmainDetailColor;
import com.vrmlstudio.store.service.ICmfStockoutmainDetailColorService;
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
 * 库存出库单明细单颜色记录表
 */
@Api(value = "库存出库单明细单颜色记录表")
@RestController
public class CmfStockoutmainDetailColorApi {

    @Autowired
    private ICmfStockoutmainDetailColorService iCmfStockoutmainDetailColorService;

    @ApiOperation(value = "库存出库单明细单颜色记录表", notes = "获取库存出库单明细单颜色记录表")
    @ApiResponse(code = 400, message = "获取库存出库单明细单颜色记录失败")
    @RequestMapping(value = "/store/cmfStockoutmainDetailColor/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfStockoutmainDetailColor cmfStockoutmainDetailColor) throws IOException {
        return new ResponseEntity<>(iCmfStockoutmainDetailColorService.selectCmfStockoutmainDetailColorList(cmfStockoutmainDetailColor), HttpStatus.OK);
    }

    @ApiOperation(value = "库存出库单明细单颜色记录表", notes = "获取库存出库单明细单颜色记录表详情")
    @ApiResponse(code = 400, message = "获取库存出库单明细单颜色详情失败")
    @RequestMapping(value = "/store/cmfStockoutmainDetailColor/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfStockoutmainDetailColorService.selectCmfStockoutmainDetailColorById(id),HttpStatus.OK);
    }
}
