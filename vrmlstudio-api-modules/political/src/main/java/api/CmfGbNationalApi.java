package api;

import com.vrmlstudio.political.domain.CmfGbNational;
import com.vrmlstudio.political.service.ICmfGbNationalService;
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
 * 国籍记录表
 */
@Api(value = "国籍记录表")
@RestController
public class CmfGbNationalApi {

    @Autowired
    private ICmfGbNationalService iCmfGbNationalService;

    @ApiOperation(value = "国籍记录表", notes = "获取国籍记录表")
    @ApiResponse(code = 400, message = "获取国籍记录失败")
    @RequestMapping(value = "/political/cmfGbNational/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfGbNational cmfGbNational) throws IOException {
        return new ResponseEntity<>(iCmfGbNationalService.selectCmfGbNationalList(cmfGbNational), HttpStatus.OK);
    }

    @ApiOperation(value = "国籍记录表", notes = "获取国籍记录表详情")
    @ApiResponse(code = 400, message = "获取国籍详情失败")
    @RequestMapping(value = "/political/cmfGbNational/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfGbNationalService.selectCmfGbNationalById(id),HttpStatus.OK);
    }
}
