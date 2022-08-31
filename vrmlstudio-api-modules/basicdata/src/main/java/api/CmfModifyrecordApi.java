package api;

import com.vrmlstudio.basicdata.domain.CmfModifyrecord;
import com.vrmlstudio.basicdata.service.ICmfModifyrecordService;
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
 * 修改记录表
 */
@Api(value = "修改记录表")
@RestController
public class CmfModifyrecordApi {

    @Autowired
    private ICmfModifyrecordService iCmfModifyrecordService;

    @ApiOperation(value = "修改记录表", notes = "获取修改记录表")
    @ApiResponse(code = 400, message = "获取修改记录失败")
    @RequestMapping(value = "/basic/cmfModifyrecord/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfModifyrecord cmfModifyrecord) throws IOException {
        return new ResponseEntity<>(iCmfModifyrecordService.selectCmfModifyrecordList(cmfModifyrecord), HttpStatus.OK);
    }

    @ApiOperation(value = "修改记录表", notes = "获取修改记录表详情")
    @ApiResponse(code = 400, message = "获取修改详情失败")
    @RequestMapping(value = "/basic/cmfModifyrecord/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfModifyrecordService.selectCmfModifyrecordById(id),HttpStatus.OK);
    }
}
