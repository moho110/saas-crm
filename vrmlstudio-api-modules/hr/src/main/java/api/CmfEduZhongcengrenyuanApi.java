package api;

import com.vrmlstudio.hr.domain.CmfEduZhongcengrenyuan;
import com.vrmlstudio.hr.service.ICmfEduZhongcengrenyuanService;
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
 * 中层人员记录表
 */
@Api(value = "中层人员记录表")
@RestController
public class CmfEduZhongcengrenyuanApi {

    @Autowired
    private ICmfEduZhongcengrenyuanService iCmfEduZhongcengrenyuanService;

    @ApiOperation(value = "中层人员记录表", notes = "获取中层人员记录表")
    @ApiResponse(code = 400, message = "获取中层人员记录失败")
    @RequestMapping(value = "/hr/cmfEduZhongcengrenyuan/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduZhongcengrenyuan cmfEduZhongcengrenyuan) throws IOException {
        return new ResponseEntity<>(iCmfEduZhongcengrenyuanService.selectCmfEduZhongcengrenyuanList(cmfEduZhongcengrenyuan), HttpStatus.OK);
    }

    @ApiOperation(value = "中层人员记录表", notes = "获取中层人员记录表详情")
    @ApiResponse(code = 400, message = "获取中层人员详情失败")
    @RequestMapping(value = "/hr/cmfEduZhongcengrenyuan/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduZhongcengrenyuanService.selectCmfEduZhongcengrenyuanById(id),HttpStatus.OK);
    }
}
