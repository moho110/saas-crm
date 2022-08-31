package api;

import com.vrmlstudio.basicdata.domain.CmfDictDanyuanyongtu;
import com.vrmlstudio.basicdata.service.ICmfDictDanyuanyongtuService;
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
 * 单元用途记录表
 */
@Api(value = "单元用途记录表")
@RestController
public class CmfDictDanyuanyongtuApi {

    @Autowired
    private ICmfDictDanyuanyongtuService iCmfDictDanyuanyongtuService;

    @ApiOperation(value = "单元用途记录表", notes = "获取单元用途记录表")
    @ApiResponse(code = 400, message = "获取单元用途记录失败")
    @RequestMapping(value = "/basic/cmfDictDanyuanyongtu/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfDictDanyuanyongtu cmfDictDanyuanyongtu) throws IOException {
        return new ResponseEntity<>(iCmfDictDanyuanyongtuService.selectCmfDictDanyuanyongtuList(cmfDictDanyuanyongtu), HttpStatus.OK);
    }

    @ApiOperation(value = "单元用途记录表", notes = "获取单元用途记录表详情")
    @ApiResponse(code = 400, message = "获取单元用途详情失败")
    @RequestMapping(value = "/basic/cmfDictDanyuanyongtu/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfDictDanyuanyongtuService.selectCmfDictDanyuanyongtuById(id),HttpStatus.OK);
    }
}
