package api;

import com.vrmlstudio.hr.domain.CmfHrmsWorkerZhengzhao;
import com.vrmlstudio.hr.service.ICmfHrmsWorkerZhengzhaoService;
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
 * 证照记录表
 */
@Api(value = "证照记录表")
@RestController
public class CmfHrmsWorkerZhengzhaoApi {

    @Autowired
    private ICmfHrmsWorkerZhengzhaoService iCmfHrmsWorkerZhengzhaoService;

    @ApiOperation(value = "证照记录表", notes = "获取证照记录表")
    @ApiResponse(code = 400, message = "获取证照记录失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkerZhengzhao/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsWorkerZhengzhao cmfHrmsWorkerZhengzhao) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkerZhengzhaoService.selectCmfHrmsWorkerZhengzhaoList(cmfHrmsWorkerZhengzhao), HttpStatus.OK);
    }

    @ApiOperation(value = "证照记录表", notes = "获取证照记录表详情")
    @ApiResponse(code = 400, message = "获取证照详情失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkerZhengzhao/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkerZhengzhaoService.selectCmfHrmsWorkerZhengzhaoById(id),HttpStatus.OK);
    }
}
