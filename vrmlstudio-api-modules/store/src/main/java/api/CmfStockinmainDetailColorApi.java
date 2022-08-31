package api;

import com.vrmlstudio.store.domain.CmfStockinmainDetailColor;
import com.vrmlstudio.store.service.ICmfStockinmainDetailColorService;
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
 * 库存入库明细颜色记录表
 */
@Api(value = "库存入库明细颜色记录表")
@RestController
public class CmfStockinmainDetailColorApi {

    @Autowired
    private ICmfStockinmainDetailColorService iCmfStockinmainDetailColorService;

    @ApiOperation(value = "库存入库明细颜色记录表", notes = "获取库存入库明细颜色记录表")
    @ApiResponse(code = 400, message = "获取库存入库明细颜色记录失败")
    @RequestMapping(value = "/store/cmfStockinmainDetailColor/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfStockinmainDetailColor cmfStockinmainDetailColor) throws IOException {
        return new ResponseEntity<>(iCmfStockinmainDetailColorService.selectCmfStockinmainDetailColorList(cmfStockinmainDetailColor), HttpStatus.OK);
    }

    @ApiOperation(value = "库存入库明细颜色记录表", notes = "获取库存入库明细颜色记录表详情")
    @ApiResponse(code = 400, message = "获取库存入库明细颜色详情失败")
    @RequestMapping(value = "/store/cmfStockinmainDetailColor/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfStockinmainDetailColorService.selectCmfStockinmainDetailColorById(id),HttpStatus.OK);
    }
}
