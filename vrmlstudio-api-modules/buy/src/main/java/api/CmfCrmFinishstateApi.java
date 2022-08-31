package api;

import com.vrmlstudio.buy.domain.CmfCrmFinishstate;
import com.vrmlstudio.buy.service.ICmfCrmFinishstateService;
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
 * 完成状态记录表
 */
@Api(value = "完成状态记录表")
@RestController
public class CmfCrmFinishstateApi {

    @Autowired
    private ICmfCrmFinishstateService iCmfCrmFinishstateService;

    @ApiOperation(value = "完成状态记录表", notes = "获取完成状态记录表")
    @ApiResponse(code = 400, message = "获取完成状态记录失败")
    @RequestMapping(value = "/buy/cmfCrmFinishstate/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmFinishstate cmfCrmFinishstate) throws IOException {
        return new ResponseEntity<>(iCmfCrmFinishstateService.selectCmfCrmFinishstateList(cmfCrmFinishstate), HttpStatus.OK);
    }

    @ApiOperation(value = "完成状态记录表", notes = "获取完成状态记录表详情")
    @ApiResponse(code = 400, message = "获取完成状态详情失败")
    @RequestMapping(value = "/buy/cmfCrmFinishstate/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmFinishstateService.selectCmfCrmFinishstateById(id),HttpStatus.OK);
    }
}
