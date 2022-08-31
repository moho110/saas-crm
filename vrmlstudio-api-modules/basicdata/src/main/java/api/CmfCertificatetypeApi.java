package api;

import com.vrmlstudio.basicdata.domain.CmfCertificatetype;
import com.vrmlstudio.basicdata.service.ICmfCertificatetypeService;
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
 * 认证类型记录表
 */
@Api(value = "认证类型记录表")
@RestController
public class CmfCertificatetypeApi {

    @Autowired
    private ICmfCertificatetypeService iCmfCertificatetypeService;

    @ApiOperation(value = "认证类型记录表", notes = "获取认证类型记录表")
    @ApiResponse(code = 400, message = "获取认证类型记录失败")
    @RequestMapping(value = "/basic/cmfCertificatetype/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCertificatetype cmfCertificatetype) throws IOException {
        return new ResponseEntity<>(iCmfCertificatetypeService.selectCmfCertificatetypeList(cmfCertificatetype), HttpStatus.OK);
    }

    @ApiOperation(value = "认证类型记录表", notes = "获取认证类型记录表详情")
    @ApiResponse(code = 400, message = "获取认证类型详情失败")
    @RequestMapping(value = "/basic/cmfCertificatetype/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCertificatetypeService.selectCmfCertificatetypeById(id),HttpStatus.OK);
    }
}
