package api;

import com.vrmlstudio.basicdata.domain.CmfFeiyongrecord;
import com.vrmlstudio.basicdata.service.ICmfFeiyongrecordService;
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
 * 费用记录表
 */
@Api(value = "费用记录表")
@RestController
public class CmfFeiyongrecordApi {

    @Autowired
    private ICmfFeiyongrecordService iCmfFeiyongrecordService;

    @ApiOperation(value = "费用记录表", notes = "获取费用记录表")
    @ApiResponse(code = 400, message = "获取费用记录失败")
    @RequestMapping(value = "/basic/cmfFeiyongrecord/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfFeiyongrecord cmfFeiyongrecord) throws IOException {
        return new ResponseEntity<>(iCmfFeiyongrecordService.selectCmfFeiyongrecordList(cmfFeiyongrecord), HttpStatus.OK);
    }

    @ApiOperation(value = "费用记录表", notes = "获取费用记录表详情")
    @ApiResponse(code = 400, message = "获取费用详情失败")
    @RequestMapping(value = "/basic/cmfFeiyongrecord/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfFeiyongrecordService.selectCmfFeiyongrecordById(id),HttpStatus.OK);
    }
}
