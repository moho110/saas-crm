package api;

import com.vrmlstudio.hr.domain.CmfEduXingzhengGroup;
import com.vrmlstudio.hr.service.ICmfEduXingzhengGroupService;
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
 * 行政分类记录表
 */
@Api(value = "行政分类记录表")
@RestController
public class CmfEduXingzhengGroupApi {

    @Autowired
    private ICmfEduXingzhengGroupService iCmfEduXingzhengGroupService;

    @ApiOperation(value = "行政分类记录表", notes = "获取行政分类记录表")
    @ApiResponse(code = 400, message = "获取行政分类记录失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengGroup/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduXingzhengGroup cmfEduXingzhengGroup) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengGroupService.selectCmfEduXingzhengGroupList(cmfEduXingzhengGroup), HttpStatus.OK);
    }

    @ApiOperation(value = "行政分类记录表", notes = "获取行政分类记录表详情")
    @ApiResponse(code = 400, message = "获取行政分类详情失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengGroup/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengGroupService.selectCmfEduXingzhengGroupById(id),HttpStatus.OK);
    }
}
