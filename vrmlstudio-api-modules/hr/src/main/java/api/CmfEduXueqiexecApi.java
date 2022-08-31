package api;

import com.vrmlstudio.hr.domain.CmfEduXueqiexec;
import com.vrmlstudio.hr.service.ICmfEduXueqiexecService;
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
 * 学期执行记录表
 */
@Api(value = "学期执行记录表")
@RestController
public class CmfEduXueqiexecApi {

    @Autowired
    private ICmfEduXueqiexecService iCmfEduXueqiexecService;

    @ApiOperation(value = "学期执行记录表", notes = "获取学期执行记录表")
    @ApiResponse(code = 400, message = "获取学期执行记录失败")
    @RequestMapping(value = "/hr/cmfEduXueqiexec/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduXueqiexec cmfEduXueqiexec) throws IOException {
        return new ResponseEntity<>(iCmfEduXueqiexecService.selectCmfEduXueqiexecList(cmfEduXueqiexec), HttpStatus.OK);
    }

    @ApiOperation(value = "学期执行记录表", notes = "获取学期执行记录表详情")
    @ApiResponse(code = 400, message = "获取学期执行详情失败")
    @RequestMapping(value = "/hr/cmfEduXueqiexec/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduXueqiexecService.selectCmfEduXueqiexecById(id),HttpStatus.OK);
    }
}
