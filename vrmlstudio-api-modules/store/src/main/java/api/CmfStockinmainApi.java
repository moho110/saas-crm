package api;

import com.vrmlstudio.store.domain.CmfStockinmain;
import com.vrmlstudio.store.service.ICmfStockinmainService;
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
 * 库存入库记录表
 */
@Api(value = "库存入库记录表")
@RestController
public class CmfStockinmainApi {

    @Autowired
    private ICmfStockinmainService iCmfStockinmainService;

    @ApiOperation(value = "库存入库记录表", notes = "获取库存入库记录表")
    @ApiResponse(code = 400, message = "获取库存入库记录失败")
    @RequestMapping(value = "/store/cmfStockinmain/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfStockinmain cmfStockinmain) throws IOException {
        return new ResponseEntity<>(iCmfStockinmainService.selectCmfStockinmainList(cmfStockinmain), HttpStatus.OK);
    }

    @ApiOperation(value = "库存入库记录表", notes = "获取库存入库记录表详情")
    @ApiResponse(code = 400, message = "获取库存入库详情失败")
    @RequestMapping(value = "/store/cmfStockinmain/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfStockinmainService.selectCmfStockinmainById(id),HttpStatus.OK);
    }
}
