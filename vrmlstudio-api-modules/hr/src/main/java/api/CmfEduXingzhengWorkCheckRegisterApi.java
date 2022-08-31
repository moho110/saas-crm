package api;

import com.vrmlstudio.hr.domain.CmfEduXingzhengWorkCheckRegister;
import com.vrmlstudio.hr.service.ICmfEduXingzhengWorkCheckRegisterService;
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
 * 工作检查记录表
 */
@Api(value = "工作检查记录表")
@RestController
public class CmfEduXingzhengWorkCheckRegisterApi {

    @Autowired
    private ICmfEduXingzhengWorkCheckRegisterService iCmfEduXingzhengWorkCheckRegisterService;

    @ApiOperation(value = "工作检查记录表", notes = "获取工作检查记录表")
    @ApiResponse(code = 400, message = "获取工作检查记录失败")
    @RequestMapping(value = "/basic/cmfEduXingzhengWorkCheckRegister/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduXingzhengWorkCheckRegister cmfEduXingzhengWorkCheckRegister) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengWorkCheckRegisterService.selectCmfEduXingzhengWorkCheckRegisterList(cmfEduXingzhengWorkCheckRegister), HttpStatus.OK);
    }

    @ApiOperation(value = "工作检查记录表", notes = "获取工作检查记录表详情")
    @ApiResponse(code = 400, message = "获取工作检查详情失败")
    @RequestMapping(value = "/basic/cmfEduXingzhengWorkCheckRegister/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengWorkCheckRegisterService.selectCmfEduXingzhengWorkCheckRegisterById(id),HttpStatus.OK);
    }
}
