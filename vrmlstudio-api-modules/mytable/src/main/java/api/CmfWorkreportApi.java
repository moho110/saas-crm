package api;

import com.vrmlstudio.mytable.domain.CmfWorkreport;
import com.vrmlstudio.mytable.service.ICmfWorkreportService;
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
 * 工作报告记录表
 */
@Api(value = "工作报告记录表")
@RestController
public class CmfWorkreportApi {

    @Autowired
    private ICmfWorkreportService iCmfWorkreportService;

    @ApiOperation(value = "工作报告记录表", notes = "获取工作报告记录表")
    @ApiResponse(code = 400, message = "获取工作报告记录失败")
    @RequestMapping(value = "/mytable/cmfWorkreport/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfWorkreport cmfWorkreport) throws IOException {
        return new ResponseEntity<>(iCmfWorkreportService.selectCmfWorkreportList(cmfWorkreport), HttpStatus.OK);
    }

    @ApiOperation(value = "工作报告记录表", notes = "获取工作报告记录表详情")
    @ApiResponse(code = 400, message = "获取工作报告详情失败")
    @RequestMapping(value = "/mytable/cmfWorkreport/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfWorkreportService.selectCmfWorkreportById(id),HttpStatus.OK);
    }
}
