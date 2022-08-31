package api;

import com.vrmlstudio.basicdata.domain.CmfFeiyongclasse;
import com.vrmlstudio.basicdata.service.ICmfFeiyongclasseService;
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
 * 费用分类记录表
 */
@Api(value = "费用分类记录表")
@RestController
public class CmfFeiyongclasseApi {

    @Autowired
    private ICmfFeiyongclasseService iCmfFeiyongclasseService;

    @ApiOperation(value = "费用分类记录表", notes = "获取费用分类记录表")
    @ApiResponse(code = 400, message = "获取费用分类记录失败")
    @RequestMapping(value = "/basic/cmfFeiyongclasse/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfFeiyongclasse cmfFeiyongclasse) throws IOException {
        return new ResponseEntity<>(iCmfFeiyongclasseService.selectCmfFeiyongclasseList(cmfFeiyongclasse), HttpStatus.OK);
    }

    @ApiOperation(value = "费用分类记录表", notes = "获取费用分类记录表详情")
    @ApiResponse(code = 400, message = "获取费用分类详情失败")
    @RequestMapping(value = "/basic/cmfFeiyongclasse/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfFeiyongclasseService.selectCmfFeiyongclasseById(id),HttpStatus.OK);
    }
}
