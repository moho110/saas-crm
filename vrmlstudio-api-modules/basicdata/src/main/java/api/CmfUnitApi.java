package api;

import com.vrmlstudio.basicdata.domain.CmfUnit;
import com.vrmlstudio.basicdata.service.ICmfUnitService;
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
 * 单元记录表
 */
@Api(value = "单元记录表")
@RestController
public class CmfUnitApi {

    @Autowired
    private ICmfUnitService iCmfUnitService;

    @ApiOperation(value = "单元记录表", notes = "获取单元记录表")
    @ApiResponse(code = 400, message = "获取单元记录失败")
    @RequestMapping(value = "/basic/cmfUnit/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfUnit cmfUnit) throws IOException {
        return new ResponseEntity<>(iCmfUnitService.selectCmfUnitList(cmfUnit), HttpStatus.OK);
    }

    @ApiOperation(value = "单元记录表", notes = "获取单元记录表详情")
    @ApiResponse(code = 400, message = "获取单元详情失败")
    @RequestMapping(value = "/basic/cmfUnit/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfUnitService.selectCmfUnitById(id),HttpStatus.OK);
    }
}
