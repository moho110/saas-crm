package api;

import com.vrmlstudio.hr.domain.CmfEduXingzhengJiabanbuxiu;
import com.vrmlstudio.hr.service.ICmfEduXingzhengJiabanbuxiuService;
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
 * 加班补休记录表
 */
@Api(value = "加班补休记录表")
@RestController
public class CmfEduXingzhengJiabanbuxiuApi {

    @Autowired
    private ICmfEduXingzhengJiabanbuxiuService iCmfEduXingzhengJiabanbuxiuService;

    @ApiOperation(value = "加班补休记录表", notes = "获取加班补休记录表")
    @ApiResponse(code = 400, message = "获取加班补休记录失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengJiabanbuxiu/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfEduXingzhengJiabanbuxiu cmfEduXingzhengJiabanbuxiu) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengJiabanbuxiuService.selectCmfEduXingzhengJiabanbuxiuList(cmfEduXingzhengJiabanbuxiu), HttpStatus.OK);
    }

    @ApiOperation(value = "加班补休记录表", notes = "获取加班补休记录表详情")
    @ApiResponse(code = 400, message = "获取加班补休详情失败")
    @RequestMapping(value = "/hr/cmfEduXingzhengJiabanbuxiu/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfEduXingzhengJiabanbuxiuService.selectCmfEduXingzhengJiabanbuxiuById(id),HttpStatus.OK);
    }
}
