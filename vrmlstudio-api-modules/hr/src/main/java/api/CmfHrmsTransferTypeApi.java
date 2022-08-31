package api;

import com.vrmlstudio.hr.domain.CmfHrmsTransferType;
import com.vrmlstudio.hr.service.ICmfHrmsTransferTypeService;
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
 * 人力转移类型记录表
 */
@Api(value = "人力转移类型记录表")
@RestController
public class CmfHrmsTransferTypeApi {

    @Autowired
    private ICmfHrmsTransferTypeService iCmfHrmsTransferTypeService;

    @ApiOperation(value = "人力转移类型记录表", notes = "获取人力转移类型记录表")
    @ApiResponse(code = 400, message = "获取人力转移类型记录失败")
    @RequestMapping(value = "/hr/cmfHrmsTransferType/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsTransferType cmfHrmsTransferType) throws IOException {
        return new ResponseEntity<>(iCmfHrmsTransferTypeService.selectCmfHrmsTransferTypeList(cmfHrmsTransferType), HttpStatus.OK);
    }

    @ApiOperation(value = "人力转移类型记录表", notes = "获取人力转移类型记录表详情")
    @ApiResponse(code = 400, message = "获取人力转移类型详情失败")
    @RequestMapping(value = "/hr/cmfHrmsTransferType/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsTransferTypeService.selectCmfHrmsTransferTypeById(id),HttpStatus.OK);
    }
}
