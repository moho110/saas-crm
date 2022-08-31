package api;

import com.vrmlstudio.hr.domain.CmfEduZhongcengmingxi;
import com.vrmlstudio.hr.service.ICmfEduZhongcengmingxiService;
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
 * 中层明细记录表
 */
@Api(value = "中层明细记录表")
@RestController
public class CmfEduZhongcengmingxiApi {

    @Autowired
    private ICmfEduZhongcengmingxiService iCmfEduZhongcengmingxiService;

    @ApiOperation(value = "中层明细记录表", notes = "获取中层明细记录表")
    @ApiResponse(code = 400, message = "获取中层明细记录失败")
    @RequestMapping(value = "/hr/cmfEduZhongcengmingxi/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduZhongcengmingxi cmfEduZhongcengmingxi) throws IOException {
        return new ResponseEntity<>(iCmfEduZhongcengmingxiService.selectCmfEduZhongcengmingxiList(cmfEduZhongcengmingxi), HttpStatus.OK);
    }

    @ApiOperation(value = "中层明细记录表", notes = "获取中层明细记录表详情")
    @ApiResponse(code = 400, message = "获取中层明细详情失败")
    @RequestMapping(value = "/hr/cmfEduZhongcengmingxi/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduZhongcengmingxiService.selectCmfEduZhongcengmingxiById(id),HttpStatus.OK);
    }
}
