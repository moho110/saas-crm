package api;

import com.vrmlstudio.hr.domain.CmfEduXingzhengYearcheck;
import com.vrmlstudio.hr.service.ICmfEduXingzhengYearcheckService;
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
 * 行政年审记录表
 */
@Api(value = "行政年审记录表")
@RestController
public class CmfEduXingzhengYearcheckApi {

    @Autowired
    private ICmfEduXingzhengYearcheckService iCmfEduXingzhengYearcheckService;

    @ApiOperation(value = "行政年审记录表", notes = "获取行政年审记录表")
    @ApiResponse(code = 400, message = "获取行政年审记录失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengYearcheck/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduXingzhengYearcheck cmfEduXingzhengYearcheck) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengYearcheckService.selectCmfEduXingzhengYearcheckList(cmfEduXingzhengYearcheck), HttpStatus.OK);
    }

    @ApiOperation(value = "行政年审记录表", notes = "获取行政年审记录表详情")
    @ApiResponse(code = 400, message = "获取行政年审详情失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengYearcheck/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengYearcheckService.selectCmfEduXingzhengYearcheckById(id),HttpStatus.OK);
    }
}
