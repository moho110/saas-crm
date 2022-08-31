package api;

import com.vrmlstudio.hr.domain.CmfEduXingzhengTiaoxiububan;
import com.vrmlstudio.hr.service.ICmfEduXingzhengTiaoxiububanService;
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
 * 调休补班记录表
 */
@Api(value = "调休补班记录表")
@RestController
public class CmfEduXingzhengTiaoxiububanApi {

    @Autowired
    private ICmfEduXingzhengTiaoxiububanService iCmfEduXingzhengTiaoxiububanService;

    @ApiOperation(value = "调休补班记录表", notes = "获取调休补班记录表")
    @ApiResponse(code = 400, message = "获取调休补班记录失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengTiaoxiububan/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduXingzhengTiaoxiububan cmfEduXingzhengTiaoxiububan) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengTiaoxiububanService.selectCmfEduXingzhengTiaoxiububanList(cmfEduXingzhengTiaoxiububan), HttpStatus.OK);
    }

    @ApiOperation(value = "调休补班记录表", notes = "获取调休补班记录表详情")
    @ApiResponse(code = 400, message = "获取调休补班详情失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengTiaoxiububan/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengTiaoxiububanService.selectCmfEduXingzhengTiaoxiububanById(id),HttpStatus.OK);
    }
}
