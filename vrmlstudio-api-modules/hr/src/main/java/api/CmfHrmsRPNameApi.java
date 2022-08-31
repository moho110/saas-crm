package api;

import com.vrmlstudio.hr.domain.CmfHrmsRPName;
import com.vrmlstudio.hr.service.ICmfHrmsRPNameService;
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
 * 奖惩名称记录表
 */
@Api(value = "奖惩名称记录表")
@RestController
public class CmfHrmsRPNameApi {

    @Autowired
    private ICmfHrmsRPNameService iCmfHrmsRPNameService;

    @ApiOperation(value = "奖惩名称记录表", notes = "获取奖惩名称记录表")
    @ApiResponse(code = 400, message = "获取奖惩名称记录失败")
    @RequestMapping(value = "/hr/cmfHrmsRPName/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsRPName cmfHrmsRPName) throws IOException {
        return new ResponseEntity<>(iCmfHrmsRPNameService.selectCmfHrmsRPNameList(cmfHrmsRPName), HttpStatus.OK);
    }

    @ApiOperation(value = "奖惩名称记录表", notes = "获取奖惩名称记录表详情")
    @ApiResponse(code = 400, message = "获取奖惩名称详情失败")
    @RequestMapping(value = "/hr/cmfHrmsRPName/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsRPNameService.selectCmfHrmsRPNameById(id),HttpStatus.OK);
    }
}
