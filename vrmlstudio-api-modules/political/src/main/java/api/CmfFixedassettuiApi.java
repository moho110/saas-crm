package api;

import com.vrmlstudio.political.domain.CmfFixedassettui;
import com.vrmlstudio.political.service.ICmfFixedassettuiService;
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
 * 固定资产退还记录表
 */
@Api(value = "固定资产退还记录表")
@RestController
public class CmfFixedassettuiApi {

    @Autowired
    private ICmfFixedassettuiService iCmfFixedassettuiService;

    @ApiOperation(value = "固定资产退还记录表", notes = "获取固定资产退还记录表")
    @ApiResponse(code = 400, message = "获取固定资产退还记录失败")
    @RequestMapping(value = "/political/cmfFixedassettui/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfFixedassettui cmfFixedassettui) throws IOException {
        return new ResponseEntity<>(iCmfFixedassettuiService.selectCmfFixedassettuiList(cmfFixedassettui), HttpStatus.OK);
    }

    @ApiOperation(value = "固定资产退还记录表", notes = "获取固定资产退还记录表详情")
    @ApiResponse(code = 400, message = "获取固定资产退还详情失败")
    @RequestMapping(value = "/political/cmfFixedassettui/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfFixedassettuiService.selectCmfFixedassettuiById(id),HttpStatus.OK);
    }
}
