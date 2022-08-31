package api;

import com.vrmlstudio.hr.domain.CmfHrmsFileLizhi;
import com.vrmlstudio.hr.service.ICmfHrmsFileLizhiService;
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
 * 离职记录表
 */
@Api(value = "离职记录表")
@RestController
public class CmfHrmsFileLizhiApi {

    @Autowired
    private ICmfHrmsFileLizhiService iCmfHrmsFileLizhiService;

    @ApiOperation(value = "离职记录表", notes = "获取离职记录表")
    @ApiResponse(code = 400, message = "获取离职记录失败")
    @RequestMapping(value = "/hr/cmfHrmsFileLizhi/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsFileLizhi cmfHrmsFileLizhi) throws IOException {
        return new ResponseEntity<>(iCmfHrmsFileLizhiService.selectCmfHrmsFileLizhiList(cmfHrmsFileLizhi), HttpStatus.OK);
    }

    @ApiOperation(value = "离职记录表", notes = "获取离职记录表详情")
    @ApiResponse(code = 400, message = "获取离职详情失败")
    @RequestMapping(value = "/hr/cmfHrmsFileLizhi/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsFileLizhiService.selectCmfHrmsFileLizhiById(id),HttpStatus.OK);
    }
}
