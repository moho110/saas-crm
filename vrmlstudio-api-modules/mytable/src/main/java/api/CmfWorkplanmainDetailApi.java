package api;

import com.vrmlstudio.mytable.domain.CmfWorkplanmainDetail;
import com.vrmlstudio.mytable.service.ICmfWorkplanmainDetailService;
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
 * 工作计划明细记录表
 */
@Api(value = "工作计划明细记录表")
@RestController
public class CmfWorkplanmainDetailApi {

    @Autowired
    private ICmfWorkplanmainDetailService iCmfWorkplanmainDetailService;

    @ApiOperation(value = "工作计划明细记录表", notes = "获取工作计划明细记录表")
    @ApiResponse(code = 400, message = "获取工作计划明细记录失败")
    @RequestMapping(value = "/mytable/cmfWorkplanmainDetail/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfWorkplanmainDetail cmfWorkplanmainDetail) throws IOException {
        return new ResponseEntity<>(iCmfWorkplanmainDetailService.selectCmfWorkplanmainDetailList(cmfWorkplanmainDetail), HttpStatus.OK);
    }

    @ApiOperation(value = "工作计划明细记录表", notes = "获取工作计划明细记录表详情")
    @ApiResponse(code = 400, message = "获取工作计划明细详情失败")
    @RequestMapping(value = "/mytable/cmfWorkplanmainDetail/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfWorkplanmainDetailService.selectCmfWorkplanmainDetailById(id),HttpStatus.OK);
    }
}
