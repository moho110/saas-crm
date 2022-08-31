package api;

import com.vrmlstudio.basicdata.domain.CmfUnitprop;
import com.vrmlstudio.basicdata.service.ICmfUnitpropService;
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
 * 单元属性记录表
 */
@Api(value = "单元属性记录表")
@RestController
public class CmfUnitpropApi {

    @Autowired
    private ICmfUnitpropService iCmfUnitpropService;

    @ApiOperation(value = "单元属性记录表", notes = "获取单元属性记录表")
    @ApiResponse(code = 400, message = "获取单元属性记录失败")
    @RequestMapping(value = "/basic/cmfUnitprop/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfUnitprop cmfUnitprop) throws IOException {
        return new ResponseEntity<>(iCmfUnitpropService.selectCmfUnitpropList(cmfUnitprop), HttpStatus.OK);
    }

    @ApiOperation(value = "单元属性记录表", notes = "获取单元属性记录表详情")
    @ApiResponse(code = 400, message = "获取单元属性详情失败")
    @RequestMapping(value = "/basic/cmfUnitprop/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfUnitpropService.selectCmfUnitpropById(id),HttpStatus.OK);
    }
}
