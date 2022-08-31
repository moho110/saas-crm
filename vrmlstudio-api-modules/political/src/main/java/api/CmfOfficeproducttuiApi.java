package api;

import com.vrmlstudio.political.domain.CmfOfficeproducttui;
import com.vrmlstudio.political.service.ICmfOfficeproducttuiService;
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
 * 办公用品退还记录表
 */
@Api(value = "办公用品退还记录表")
@RestController
public class CmfOfficeproducttuiApi {

    @Autowired
    private ICmfOfficeproducttuiService iCmfOfficeproducttuiService;

    @ApiOperation(value = "办公用品退还记录表", notes = "获取办公用品退还记录表")
    @ApiResponse(code = 400, message = "获取办公用品退还记录失败")
    @RequestMapping(value = "/political/cmfOfficeproducttui/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfOfficeproducttui cmfOfficeproducttui) throws IOException {
        return new ResponseEntity<>(iCmfOfficeproducttuiService.selectCmfOfficeproducttuiList(cmfOfficeproducttui), HttpStatus.OK);
    }

    @ApiOperation(value = "办公用品退还记录表", notes = "获取办公用品退还记录表详情")
    @ApiResponse(code = 400, message = "获取办公用品退还详情失败")
    @RequestMapping(value = "/political/cmfOfficeproducttui/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfOfficeproducttuiService.selectCmfOfficeproducttuiById(id),HttpStatus.OK);
    }
}
