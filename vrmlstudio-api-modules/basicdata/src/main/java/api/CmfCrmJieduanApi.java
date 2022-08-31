package api;

import com.vrmlstudio.basicdata.domain.CmfCrmJieduan;
import com.vrmlstudio.basicdata.service.ICmfCrmJieduanService;
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
 * 阶段记录表
 */
@Api(value = "阶段记录表")
@RestController
public class CmfCrmJieduanApi {

    @Autowired
    private ICmfCrmJieduanService iCmfCrmJieduanService;

    @ApiOperation(value = "阶段记录表", notes = "获取阶段记录表")
    @ApiResponse(code = 400, message = "获取阶段记录失败")
    @RequestMapping(value = "/basic/cmfCrmJieduan/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmJieduan cmfCrmJieduan) throws IOException {
        return new ResponseEntity<>(iCmfCrmJieduanService.selectCmfCrmJieduanList(cmfCrmJieduan), HttpStatus.OK);
    }

    @ApiOperation(value = "阶段记录表", notes = "获取阶段记录表详情")
    @ApiResponse(code = 400, message = "获取阶段详情失败")
    @RequestMapping(value = "/basic/cmfCrmJieduan/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmJieduanService.selectCmfCrmJieduanById(id),HttpStatus.OK);
    }
}
