package api;

import com.vrmlstudio.political.domain.CmfDictSatisfaction;
import com.vrmlstudio.political.service.ICmfDictSatisfactionService;
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
 * 满意度记录表
 */
@Api(value = "满意度记录表")
@RestController
public class CmfDictSatisfactionApi {

    @Autowired
    private ICmfDictSatisfactionService iCmfDictSatisfactionService;

    @ApiOperation(value = "满意度记录表", notes = "获取满意度记录表")
    @ApiResponse(code = 400, message = "获取满意度记录失败")
    @RequestMapping(value = "/political/cmfDictSatisfaction/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfDictSatisfaction cmfDictSatisfaction) throws IOException {
        return new ResponseEntity<>(iCmfDictSatisfactionService.selectCmfDictSatisfactionList(cmfDictSatisfaction), HttpStatus.OK);
    }

    @ApiOperation(value = "满意度记录表", notes = "获取满意度记录表详情")
    @ApiResponse(code = 400, message = "获取满意度详情失败")
    @RequestMapping(value = "/political/cmfDictSatisfaction/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfDictSatisfactionService.selectCmfDictSatisfactionById(id),HttpStatus.OK);
    }
}
