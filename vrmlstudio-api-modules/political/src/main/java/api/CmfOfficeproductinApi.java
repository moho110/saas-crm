package api;

import com.vrmlstudio.political.domain.CmfOfficeproductin;
import com.vrmlstudio.political.service.ICmfOfficeproductinService;
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
 * 办公用品入库记录表
 */
@Api(value = "办公用品入库记录表")
@RestController
public class CmfOfficeproductinApi {

    @Autowired
    private ICmfOfficeproductinService iCmfOfficeproductinService;

    @ApiOperation(value = "办公用品入库记录表", notes = "获取办公用品入库记录表")
    @ApiResponse(code = 400, message = "获取办公用品入库记录失败")
    @RequestMapping(value = "/political/cmfOfficeproductin/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfOfficeproductin cmfOfficeproductin) throws IOException {
        return new ResponseEntity<>(iCmfOfficeproductinService.selectCmfOfficeproductinList(cmfOfficeproductin), HttpStatus.OK);
    }

    @ApiOperation(value = "办公用品入库记录表", notes = "获取办公用品入库记录表详情")
    @ApiResponse(code = 400, message = "获取办公用品入库详情失败")
    @RequestMapping(value = "/political/cmfOfficeproductin/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfOfficeproductinService.selectCmfOfficeproductinById(id),HttpStatus.OK);
    }
}
