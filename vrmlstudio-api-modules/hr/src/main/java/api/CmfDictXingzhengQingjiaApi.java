package api;

import com.vrmlstudio.hr.domain.CmfDictXingzhengQingjia;
import com.vrmlstudio.hr.service.ICmfDictXingzhengQingjiaService;
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
 * 行政请假记录表
 */
@Api(value = "行政请假记录表")
@RestController
public class CmfDictXingzhengQingjiaApi {

    @Autowired
    private ICmfDictXingzhengQingjiaService iCmfDictXingzhengQingjiaService;

    @ApiOperation(value = "行政请假记录表", notes = "获取行政请假记录表")
    @ApiResponse(code = 400, message = "获取行政请假记录失败")
    @RequestMapping(value = "/hr/cmfDictXingzhengQingjia/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfDictXingzhengQingjia cmfDictXingzhengQingjia) throws IOException {
        return new ResponseEntity<>(iCmfDictXingzhengQingjiaService.selectCmfDictXingzhengQingjiaList(cmfDictXingzhengQingjia), HttpStatus.OK);
    }

    @ApiOperation(value = "行政请假记录表", notes = "获取行政请假记录表详情")
    @ApiResponse(code = 400, message = "获取行政请假详情失败")
    @RequestMapping(value = "/hr/cmfDictXingzhengQingjia/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfDictXingzhengQingjiaService.selectCmfDictXingzhengQingjiaById(id),HttpStatus.OK);
    }
}
