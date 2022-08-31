package api;

import com.vrmlstudio.sales.domain.CmfSellbilltype;
import com.vrmlstudio.sales.service.ICmfSellbilltypeService;
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
 * 销售清单类型记录表
 */
@Api(value = "销售清单类型记录表")
@RestController
public class CmfSellbilltypeApi {

    @Autowired
    private ICmfSellbilltypeService iCmfSellbilltypeService;

    @ApiOperation(value = "销售清单类型记录表", notes = "获取销售清单类型记录表")
    @ApiResponse(code = 400, message = "获取销售清单类型记录失败")
    @RequestMapping(value = "/sales/cmfSellbilltype/info",method = RequestMethod.GET)
    public ResponseEntity<Object> info(CmfSellbilltype cmfSellbilltype) throws IOException {
        return new ResponseEntity<>(iCmfSellbilltypeService.selectCmfSellbilltypeList(cmfSellbilltype), HttpStatus.OK);
    }

    @ApiOperation(value = "销售清单类型记录表", notes = "获取销售清单类型记录表详情")
    @ApiResponse(code = 400, message = "获取销售清单类型详情失败")
    @RequestMapping(value = "/sales/cmfSellbilltype/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> find(@PathVariable("id") Integer id) throws IOException {
        return new ResponseEntity<>(iCmfSellbilltypeService.selectCmfSellbilltypeById(id),HttpStatus.OK);
    }
}
