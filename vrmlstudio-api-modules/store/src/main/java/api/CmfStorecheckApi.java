package api;

import com.vrmlstudio.store.domain.CmfStorecheck;
import com.vrmlstudio.store.service.ICmfStorecheckService;
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
 * 仓储盘点记录表
 */
@Api(value = "仓储盘点记录表")
@RestController
public class CmfStorecheckApi {

    @Autowired
    private ICmfStorecheckService iCmfStorecheckService;

    @ApiOperation(value = "仓储盘点记录表", notes = "获取仓储盘点记录表")
    @ApiResponse(code = 400, message = "获取仓储盘点记录失败")
    @RequestMapping(value = "/store/cmfStorecheck/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfStorecheck cmfStorecheck) throws IOException {
        return new ResponseEntity<>(iCmfStorecheckService.selectCmfStorecheckList(cmfStorecheck), HttpStatus.OK);
    }

    @ApiOperation(value = "仓储盘点记录表", notes = "获取仓储盘点记录表详情")
    @ApiResponse(code = 400, message = "获取仓储盘点详情失败")
    @RequestMapping(value = "/store/cmfStorecheck/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfStorecheckService.selectCmfStorecheckById(id),HttpStatus.OK);
    }
}
