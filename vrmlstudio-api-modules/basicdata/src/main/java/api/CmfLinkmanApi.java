package api;

import com.vrmlstudio.basicdata.domain.CmfLinkman;
import com.vrmlstudio.basicdata.service.ICmfLinkmanService;
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
 * 联系人记录表
 */
@Api(value = "联系人记录表")
@RestController
public class CmfLinkmanApi {

    @Autowired
    private ICmfLinkmanService iCmfLinkmanService;

    @ApiOperation(value = "联系人记录表", notes = "获取联系人记录表")
    @ApiResponse(code = 400, message = "获取联系人记录失败")
    @RequestMapping(value = "/basic/cmfLinkman/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfLinkman cmfLinkman) throws IOException {
        return new ResponseEntity<>(iCmfLinkmanService.selectCmfLinkmanList(cmfLinkman), HttpStatus.OK);
    }

    @ApiOperation(value = "联系人记录表", notes = "获取联系人记录表详情")
    @ApiResponse(code = 400, message = "获取联系人详情失败")
    @RequestMapping(value = "/basic/cmfLinkman/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfLinkmanService.selectCmfLinkmanById(id),HttpStatus.OK);
    }
}
