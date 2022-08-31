package api;

import com.vrmlstudio.buy.domain.CmfBuyplanmainDetailColor;
import com.vrmlstudio.buy.service.ICmfBuyplanmainDetailColorService;
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
 * 采购计划明细单颜色记录表
 */
@Api(value = "采购计划明细单颜色记录表")
@RestController
public class CmfBuyplanmainDetailColorApi {

    @Autowired
    private ICmfBuyplanmainDetailColorService iCmfBuyplanmainDetailColorService;

    @ApiOperation(value = "采购计划明细单颜色记录表", notes = "获取采购计划明细单颜色记录表")
    @ApiResponse(code = 400, message = "获取采购计划明细单颜色记录失败")
    @RequestMapping(value = "/buy/cmfBuyplanmainDetailColor/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfBuyplanmainDetailColor cmfBuyplanmainDetailColor) throws IOException {
        return new ResponseEntity<>(iCmfBuyplanmainDetailColorService.selectCmfBuyplanmainDetailColorList(cmfBuyplanmainDetailColor), HttpStatus.OK);
    }

    @ApiOperation(value = "采购计划明细单颜色记录表", notes = "获取采购计划明细单颜色记录表详情")
    @ApiResponse(code = 400, message = "获取采购计划明细单颜色详情失败")
    @RequestMapping(value = "/buy/cmfBuyplanmainDetailColor/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfBuyplanmainDetailColorService.selectCmfBuyplanmainDetailColorById(id),HttpStatus.OK);
    }
}
