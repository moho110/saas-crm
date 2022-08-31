package api;

import com.vrmlstudio.basicdata.domain.CmfMeasure;
import com.vrmlstudio.basicdata.service.ICmfMeasureService;
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
 * 监测记录表
 */
@Api(value = "监测记录表")
@RestController
public class CmfMeasureApi {

    @Autowired
    private ICmfMeasureService iCmfMeasureService;

    @ApiOperation(value = "监测记录表", notes = "获取监测记录表")
    @ApiResponse(code = 400, message = "获取监测记录失败")
    @RequestMapping(value = "/basic/cmfMeasure/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfMeasure cmfMeasure) throws IOException {
        return new ResponseEntity<>(iCmfMeasureService.selectCmfMeasureList(cmfMeasure), HttpStatus.OK);
    }

    @ApiOperation(value = "监测记录表", notes = "获取监测记录表详情")
    @ApiResponse(code = 400, message = "获取监测详情失败")
    @RequestMapping(value = "/basic/cmfMeasure/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfMeasureService.selectCmfMeasureById(id),HttpStatus.OK);
    }
}
