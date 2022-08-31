package api;

import com.vrmlstudio.buy.domain.CmfBuyplanmainTmpColor;
import com.vrmlstudio.buy.service.ICmfBuyplanmainTmpColorService;
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
 * 采购计划临时表颜色记录表
 */
@Api(value = "采购计划临时表颜色记录表")
@RestController
public class CmfBuyplanmainTmpColorApi {

    @Autowired
    private ICmfBuyplanmainTmpColorService iCmfBuyplanmainTmpColorService;

    @ApiOperation(value = "采购计划临时表颜色记录表", notes = "获取采购计划临时表颜色记录表")
    @ApiResponse(code = 400, message = "获取采购计划临时表颜色记录失败")
    @RequestMapping(value = "/buy/cmfBuyplanmainTmpColor/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfBuyplanmainTmpColor cmfBuyplanmainTmpColor) throws IOException {
        return new ResponseEntity<>(iCmfBuyplanmainTmpColorService.selectCmfBuyplanmainTmpColorList(cmfBuyplanmainTmpColor), HttpStatus.OK);
    }

    @ApiOperation(value = "采购计划临时表颜色记录表", notes = "获取采购计划临时表颜色记录表详情")
    @ApiResponse(code = 400, message = "获取采购计划临时表颜色详情失败")
    @RequestMapping(value = "/buy/cmfBuyplanmainTmpColor/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfBuyplanmainTmpColorService.selectCmfBuyplanmainTmpColorById(id),HttpStatus.OK);
    }
}
