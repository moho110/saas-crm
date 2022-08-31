package api;

import com.vrmlstudio.hr.domain.CmfDictXingbie;
import com.vrmlstudio.hr.service.ICmfDictXingbieService;
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
 * 性别记录表
 */
@Api(value = "性别记录表")
@RestController
public class CmfDictXingbieApi {

    @Autowired
    private ICmfDictXingbieService iCmfDictXingbieService;

    @ApiOperation(value = "性别记录表", notes = "获取性别记录表")
    @ApiResponse(code = 400, message = "获取性别记录失败")
    @RequestMapping(value = "/hr/cmfDictXingbie/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfDictXingbie cmfDictXingbie) throws IOException {
        return new ResponseEntity<>(iCmfDictXingbieService.selectCmfDictXingbieList(cmfDictXingbie), HttpStatus.OK);
    }

    @ApiOperation(value = "性别记录表", notes = "获取性别记录表详情")
    @ApiResponse(code = 400, message = "获取性别详情失败")
    @RequestMapping(value = "/hr/cmfDictXingbie/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfDictXingbieService.selectCmfDictXingbieById(id),HttpStatus.OK);
    }
}
