package api;

import com.vrmlstudio.hr.domain.CmfHrmsRewardPunishment;
import com.vrmlstudio.hr.service.ICmfHrmsRewardPunishmentService;
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
 * 奖惩记录表
 */
@Api(value = "奖惩记录表")
@RestController
public class CmfHrmsRewardPunishmentApi {

    @Autowired
    private ICmfHrmsRewardPunishmentService iCmfHrmsRewardPunishmentService;

    @ApiOperation(value = "奖惩记录表", notes = "获取奖惩记录表")
    @ApiResponse(code = 400, message = "获取奖惩记录失败")
    @RequestMapping(value = "/hr/cmfHrmsRewardPunishment/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfHrmsRewardPunishment cmfHrmsRewardPunishment) throws IOException {
        return new ResponseEntity<>(iCmfHrmsRewardPunishmentService.selectCmfHrmsRewardPunishmentList(cmfHrmsRewardPunishment), HttpStatus.OK);
    }

    @ApiOperation(value = "奖惩记录表", notes = "获取奖惩记录表详情")
    @ApiResponse(code = 400, message = "获取奖惩详情失败")
    @RequestMapping(value = "/hr/cmfHrmsRewardPunishment/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfHrmsRewardPunishmentService.selectCmfHrmsRewardPunishmentById(id),HttpStatus.OK);
    }
}
