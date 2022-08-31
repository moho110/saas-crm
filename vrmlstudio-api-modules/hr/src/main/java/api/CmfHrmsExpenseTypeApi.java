package api;

import com.vrmlstudio.hr.domain.CmfHrmsExpenseType;
import com.vrmlstudio.hr.service.ICmfHrmsExpenseTypeService;
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
 * 人力成本类型记录表
 */
@Api(value = "人力成本类型记录表")
@RestController
public class CmfHrmsExpenseTypeApi {

    @Autowired
    private ICmfHrmsExpenseTypeService iCmfHrmsExpenseTypeService;

    @ApiOperation(value = "人力成本类型记录表", notes = "获取人力成本类型记录表")
    @ApiResponse(code = 400, message = "获取人力成本类型记录失败")
    @RequestMapping(value = "/hr/cmfHrmsExpenseType/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsExpenseType cmfHrmsExpenseType) throws IOException {
        return new ResponseEntity<>(iCmfHrmsExpenseTypeService.selectCmfHrmsExpenseTypeList(cmfHrmsExpenseType), HttpStatus.OK);
    }

    @ApiOperation(value = "人力成本类型记录表", notes = "获取人力成本类型记录表详情")
    @ApiResponse(code = 400, message = "获取人力成本类型详情失败")
    @RequestMapping(value = "/hr/cmfHrmsExpenseType/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsExpenseTypeService.selectCmfHrmsExpenseTypeById(id),HttpStatus.OK);
    }
}
