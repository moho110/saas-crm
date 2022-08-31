package api;

import com.vrmlstudio.buy.domain.CmfIfneed;
import com.vrmlstudio.buy.service.ICmfIfneedService;
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
 * 是否需要记录表
 */
@Api(value = "是否需要记录表")
@RestController
public class CmfIfneedApi {

    @Autowired
    private ICmfIfneedService iCmfIfneedService;

    @ApiOperation(value = "是否需要记录表", notes = "获取是否需要记录表")
    @ApiResponse(code = 400, message = "获取是否需要记录失败")
    @RequestMapping(value = "/buy/cmfIfneed/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfIfneed cmfIfneed) throws IOException {
        return new ResponseEntity<>(iCmfIfneedService.selectCmfIfneedList(cmfIfneed), HttpStatus.OK);
    }

    @ApiOperation(value = "是否需要记录表", notes = "获取是否需要记录表详情")
    @ApiResponse(code = 400, message = "获取是否需要详情失败")
    @RequestMapping(value = "/buy/cmfIfneed/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfIfneedService.selectCmfIfneedById(id),HttpStatus.OK);
    }
}
