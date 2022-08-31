package api;

import com.vrmlstudio.sales.domain.CmfShoupiaorecord;
import com.vrmlstudio.sales.service.ICmfShoupiaorecordService;
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
 * 收票记录表
 */
@Api(value = "收票记录表")
@RestController
public class CmfShoupiaorecordApi {

    @Autowired
    private ICmfShoupiaorecordService iCmfShoupiaorecordService;

    @ApiOperation(value = "收票记录表", notes = "获取收票记录表")
    @ApiResponse(code = 400, message = "获取收票记录失败")
    @RequestMapping(value = "/sales/cmfShoupiaorecord/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfShoupiaorecord cmfShoupiaorecord) throws IOException {
        return new ResponseEntity<>(iCmfShoupiaorecordService.selectCmfShoupiaorecordList(cmfShoupiaorecord), HttpStatus.OK);
    }

    @ApiOperation(value = "收票记录表", notes = "获取收票记录表详情")
    @ApiResponse(code = 400, message = "获取收票详情失败")
    @RequestMapping(value = "/sales/cmfShoupiaorecord/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfShoupiaorecordService.selectCmfShoupiaorecordById(id),HttpStatus.OK);
    }
}
