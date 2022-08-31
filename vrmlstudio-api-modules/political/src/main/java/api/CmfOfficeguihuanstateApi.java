package api;

import com.vrmlstudio.political.domain.CmfOfficeguihuanstate;
import com.vrmlstudio.political.service.ICmfOfficeguihuanstateService;
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
 * 办公用品归还状态记录表
 */
@Api(value = "办公用品归还状态记录表")
@RestController
public class CmfOfficeguihuanstateApi {

    @Autowired
    private ICmfOfficeguihuanstateService iCmfOfficeguihuanstateService;

    @ApiOperation(value = "办公用品归还状态记录表", notes = "获取办公用品归还状态记录表")
    @ApiResponse(code = 400, message = "获取办公用品归还状态记录失败")
    @RequestMapping(value = "/political/cmfOfficeguihuanstate/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfOfficeguihuanstate cmfOfficeguihuanstate) throws IOException {
        return new ResponseEntity<>(iCmfOfficeguihuanstateService.selectCmfOfficeguihuanstateList(cmfOfficeguihuanstate), HttpStatus.OK);
    }

    @ApiOperation(value = "办公用品归还状态记录表", notes = "获取办公用品归还状态记录表详情")
    @ApiResponse(code = 400, message = "获取办公用品归还状态详情失败")
    @RequestMapping(value = "/political/cmfOfficeguihuanstate/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfOfficeguihuanstateService.selectCmfOfficeguihuanstateById(id),HttpStatus.OK);
    }
}
