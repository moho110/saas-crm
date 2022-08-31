package api;

import com.vrmlstudio.political.domain.CmfWuBuildinginformation;
import com.vrmlstudio.political.service.ICmfWuBuildinginformationService;
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
 * 建筑信息记录表
 */
@Api(value = "建筑信息记录表")
@RestController
public class CmfWuBuildinginformationApi {

    @Autowired
    private ICmfWuBuildinginformationService iCmfWuBuildinginformationService;

    @ApiOperation(value = "建筑信息记录表", notes = "获取建筑信息记录表")
    @ApiResponse(code = 400, message = "获取建筑信息记录失败")
    @RequestMapping(value = "/political/cmfWuBuildinginformation/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfWuBuildinginformation cmfWuBuildinginformation) throws IOException {
        return new ResponseEntity<>(iCmfWuBuildinginformationService.selectCmfWuBuildinginformationList(cmfWuBuildinginformation), HttpStatus.OK);
    }

    @ApiOperation(value = "建筑信息记录表", notes = "获取建筑信息记录表详情")
    @ApiResponse(code = 400, message = "获取建筑信息详情失败")
    @RequestMapping(value = "/political/cmfWuBuildinginformation/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfWuBuildinginformationService.selectCmfWuBuildinginformationById(id),HttpStatus.OK);
    }
}
