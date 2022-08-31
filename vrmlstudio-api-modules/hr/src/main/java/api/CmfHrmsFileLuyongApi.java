package api;

import com.vrmlstudio.hr.domain.CmfHrmsFileLuyong;
import com.vrmlstudio.hr.service.ICmfHrmsFileLuyongService;
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
 * 录用记录表
 */
@Api(value = "录用记录表")
@RestController
public class CmfHrmsFileLuyongApi {

    @Autowired
    private ICmfHrmsFileLuyongService iCmfHrmsFileLuyongService;

    @ApiOperation(value = "录用记录表", notes = "获取录用记录表")
    @ApiResponse(code = 400, message = "获取录用记录失败")
    @RequestMapping(value = "/hr/cmfHrmsFileLuyong/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsFileLuyong cmfHrmsFileLuyong) throws IOException {
        return new ResponseEntity<>(iCmfHrmsFileLuyongService.selectCmfHrmsFileLuyongList(cmfHrmsFileLuyong), HttpStatus.OK);
    }

    @ApiOperation(value = "录用记录表", notes = "获取录用记录表详情")
    @ApiResponse(code = 400, message = "获取录用详情失败")
    @RequestMapping(value = "/hr/cmfHrmsFileLuyong/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsFileLuyongService.selectCmfHrmsFileLuyongById(id),HttpStatus.OK);
    }
}
