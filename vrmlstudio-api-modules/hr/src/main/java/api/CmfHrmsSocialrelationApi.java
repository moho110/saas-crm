package api;

import com.vrmlstudio.hr.domain.CmfHrmsSocialrelation;
import com.vrmlstudio.hr.service.ICmfHrmsSocialrelationService;
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
 * 社会关系记录表
 */
@Api(value = "社会关系记录表")
@RestController
public class CmfHrmsSocialrelationApi {

    @Autowired
    private ICmfHrmsSocialrelationService iCmfHrmsSocialrelationService;

    @ApiOperation(value = "社会关系记录表", notes = "获取社会关系记录表")
    @ApiResponse(code = 400, message = "获取社会关系记录失败")
    @RequestMapping(value = "/hr/cmfHrmsSocialrelation/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsSocialrelation cmfHrmsSocialrelation) throws IOException {
        return new ResponseEntity<>(iCmfHrmsSocialrelationService.selectCmfHrmsSocialrelationList(cmfHrmsSocialrelation), HttpStatus.OK);
    }

    @ApiOperation(value = "社会关系记录表", notes = "获取社会关系记录表详情")
    @ApiResponse(code = 400, message = "获取社会关系详情失败")
    @RequestMapping(value = "/hr/cmfHrmsSocialrelation/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsSocialrelationService.selectCmfHrmsSocialrelationById(id),HttpStatus.OK);
    }
}
