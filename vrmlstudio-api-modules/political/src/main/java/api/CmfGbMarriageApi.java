package api;

import com.vrmlstudio.political.domain.CmfGbMarriage;
import com.vrmlstudio.political.service.ICmfGbMarriageService;
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
 * 婚否记录表
 */
@Api(value = "婚否记录表")
@RestController
public class CmfGbMarriageApi {

    @Autowired
    private ICmfGbMarriageService iCmfGbMarriageService;

    @ApiOperation(value = "婚否记录表", notes = "获取婚否记录表")
    @ApiResponse(code = 400, message = "获取婚否记录失败")
    @RequestMapping(value = "/political/cmfGbMarriage/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfGbMarriage cmfGbMarriage) throws IOException {
        return new ResponseEntity<>(iCmfGbMarriageService.selectCmfGbMarriageList(cmfGbMarriage), HttpStatus.OK);
    }

    @ApiOperation(value = "婚否记录表", notes = "获取婚否记录表详情")
    @ApiResponse(code = 400, message = "获取婚否详情失败")
    @RequestMapping(value = "/political/cmfGbMarriage/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfGbMarriageService.selectCmfGbMarriageById(id),HttpStatus.OK);
    }
}
