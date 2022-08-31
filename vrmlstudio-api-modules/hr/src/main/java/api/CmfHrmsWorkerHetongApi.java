package api;

import com.vrmlstudio.hr.domain.CmfHrmsWorkerHetong;
import com.vrmlstudio.hr.service.ICmfHrmsWorkerHetongService;
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
 * 工作合同记录表
 */
@Api(value = "工作合同记录表")
@RestController
public class CmfHrmsWorkerHetongApi {

    @Autowired
    private ICmfHrmsWorkerHetongService iCmfHrmsWorkerHetongService;

    @ApiOperation(value = "工作合同记录表", notes = "获取工作合同记录表")
    @ApiResponse(code = 400, message = "获取工作合同记录失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkerHetong/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsWorkerHetong cmfHrmsWorkerHetong) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkerHetongService.selectCmfHrmsWorkerHetongList(cmfHrmsWorkerHetong), HttpStatus.OK);
    }

    @ApiOperation(value = "工作合同记录表", notes = "获取工作合同记录表详情")
    @ApiResponse(code = 400, message = "获取工作合同详情失败")
    @RequestMapping(value = "/hr/cmfHrmsWorkerHetong/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsWorkerHetongService.selectCmfHrmsWorkerHetongById(id),HttpStatus.OK);
    }
}
