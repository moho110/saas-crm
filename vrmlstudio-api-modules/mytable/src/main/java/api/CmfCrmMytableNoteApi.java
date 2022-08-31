package api;

import com.vrmlstudio.mytable.domain.CmfCrmMytableNote;
import com.vrmlstudio.mytable.service.ICmfCrmMytableNoteService;
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
 * 便签记录表
 */
@Api(value = "便签记录表")
@RestController
public class CmfCrmMytableNoteApi {

    @Autowired
    private ICmfCrmMytableNoteService iCmfCrmMytableNoteService;

    @ApiOperation(value = "便签记录表", notes = "获取便签记录表")
    @ApiResponse(code = 400, message = "获取便签记录失败")
    @RequestMapping(value = "/mytable/cmfCrmMytableNote/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfCrmMytableNote cmfCrmMytableNote) throws IOException {
        return new ResponseEntity<>(iCmfCrmMytableNoteService.selectCmfCrmMytableNoteList(cmfCrmMytableNote), HttpStatus.OK);
    }

    @ApiOperation(value = "便签记录表", notes = "获取便签记录表详情")
    @ApiResponse(code = 400, message = "获取便签详情失败")
    @RequestMapping(value = "/mytable/cmfCrmMytableNote/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCrmMytableNoteService.selectCmfCrmMytableNoteById(id),HttpStatus.OK);
    }
}
