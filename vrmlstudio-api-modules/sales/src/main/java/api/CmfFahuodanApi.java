package api;

import com.vrmlstudio.sales.domain.CmfFahuodan;
import com.vrmlstudio.sales.service.ICmfFahuodanService;
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
 * 发货单记录表
 */
@Api(value = "发货单记录表")
@RestController
public class CmfFahuodanApi {

    @Autowired
    private ICmfFahuodanService iCmfFahuodanService;

    @ApiOperation(value = "发货单记录表", notes = "获取发货单记录表")
    @ApiResponse(code = 400, message = "获取发货单记录失败")
    @RequestMapping(value = "/sales/cmfFahuodan/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfFahuodan cmfFahuodan) throws IOException {
        return new ResponseEntity<>(iCmfFahuodanService.selectCmfFahuodanList(cmfFahuodan), HttpStatus.OK);
    }

    @ApiOperation(value = "发货单记录表", notes = "获取发货单记录表详情")
    @ApiResponse(code = 400, message = "获取发货单详情失败")
    @RequestMapping(value = "/sales/cmfFahuodan/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfFahuodanService.selectCmfFahuodanById(id),HttpStatus.OK);
    }
}
