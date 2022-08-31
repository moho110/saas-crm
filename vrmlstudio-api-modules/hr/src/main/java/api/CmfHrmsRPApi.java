package api;

import com.vrmlstudio.hr.domain.CmfHrmsRP;
import com.vrmlstudio.hr.service.ICmfHrmsRPService;
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
 * 是否奖惩记录表
 */
@Api(value = "是否奖惩记录表")
@RestController
public class CmfHrmsRPApi {

    @Autowired
    private ICmfHrmsRPService iCmfHrmsRPService;

    @ApiOperation(value = "是否奖惩记录表", notes = "获取是否奖惩记录表")
    @ApiResponse(code = 400, message = "获取是否奖惩记录失败")
    @RequestMapping(value = "/hr/cmfHrmsRP/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsRP cmfHrmsRP) throws IOException {
        return new ResponseEntity<>(iCmfHrmsRPService.selectCmfHrmsRPList(cmfHrmsRP), HttpStatus.OK);
    }

    @ApiOperation(value = "是否奖惩记录表", notes = "获取是否奖惩记录表详情")
    @ApiResponse(code = 400, message = "获取是否奖惩详情失败")
    @RequestMapping(value = "/hr/cmfHrmsRP/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsRPService.selectCmfHrmsRPById(id),HttpStatus.OK);
    }
}
