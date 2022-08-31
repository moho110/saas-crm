package api;

import com.vrmlstudio.sales.domain.CmfSellplanmainDetailColor;
import com.vrmlstudio.sales.service.ICmfSellplanmainDetailColorService;
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
 * 销售计划明细表颜色记录表
 */
@Api(value = "销售计划明细表颜色记录表")
@RestController
public class CmfSellplanmainDetailColorApi {

    @Autowired
    private ICmfSellplanmainDetailColorService iCmfSellplanmainDetailColorService;

    @ApiOperation(value = "销售计划明细表颜色记录表", notes = "获取销售计划明细表颜色记录表")
    @ApiResponse(code = 400, message = "获取销售计划明细表颜色记录失败")
    @RequestMapping(value = "/sales/cmfSellplanmainDetailColor/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSellplanmainDetailColor cmfSellplanmainDetailColor) throws IOException {
        return new ResponseEntity<>(iCmfSellplanmainDetailColorService.selectCmfSellplanmainDetailColorList(cmfSellplanmainDetailColor), HttpStatus.OK);
    }

    @ApiOperation(value = "销售计划明细表颜色记录表", notes = "获取销售计划明细表颜色记录表详情")
    @ApiResponse(code = 400, message = "获取销售计划明细表颜色详情失败")
    @RequestMapping(value = "/sales/cmfSellplanmainDetailColor/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSellplanmainDetailColorService.selectCmfSellplanmainDetailColorById(id),HttpStatus.OK);
    }
}
