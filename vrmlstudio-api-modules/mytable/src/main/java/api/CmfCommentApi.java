package api;

import com.vrmlstudio.mytable.domain.CmfComment;
import com.vrmlstudio.mytable.service.ICmfCommentService;
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
 * 评论记录表
 */
@Api(value = "评论记录表")
@RestController
public class CmfCommentApi {

    @Autowired
    private ICmfCommentService iCmfCommentService;

    @ApiOperation(value = "评论记录表", notes = "获取评论记录表")
    @ApiResponse(code = 400, message = "获取评论记录失败")
    @RequestMapping(value = "/mytable/cmfComment/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfComment cmfComment) throws IOException {
        return new ResponseEntity<>(iCmfCommentService.selectCmfCommentList(cmfComment), HttpStatus.OK);
    }

    @ApiOperation(value = "评论记录表", notes = "获取评论记录表详情")
    @ApiResponse(code = 400, message = "获取评论详情失败")
    @RequestMapping(value = "/mytable/cmfComment/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfCommentService.selectCmfCommentById(id),HttpStatus.OK);
    }
}
