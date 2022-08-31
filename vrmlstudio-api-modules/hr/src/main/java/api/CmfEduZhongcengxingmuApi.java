package api;

import com.vrmlstudio.hr.domain.CmfEduZhongcengxingmu;
import com.vrmlstudio.hr.service.ICmfEduZhongcengxingmuService;
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
 * 中层项目记录表
 */
@Api(value = "中层项目记录表")
@RestController
public class CmfEduZhongcengxingmuApi {

    @Autowired
    private ICmfEduZhongcengxingmuService iCmfEduZhongcengxingmuService;

    @ApiOperation(value = "中层项目记录表", notes = "获取中层项目记录表")
    @ApiResponse(code = 400, message = "获取中层项目记录失败")
    @RequestMapping(value = "/hr/cmfEduZhongcengxingmu/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduZhongcengxingmu cmfEduZhongcengxingmu) throws IOException {
        return new ResponseEntity<>(iCmfEduZhongcengxingmuService.selectCmfEduZhongcengxingmuList(cmfEduZhongcengxingmu), HttpStatus.OK);
    }

    @ApiOperation(value = "中层项目记录表", notes = "获取中层项目记录表详情")
    @ApiResponse(code = 400, message = "获取中层项目详情失败")
    @RequestMapping(value = "/hr/cmfEduZhongcengxingmu/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduZhongcengxingmuService.selectCmfEduZhongcengxingmuById(id),HttpStatus.OK);
    }
}
