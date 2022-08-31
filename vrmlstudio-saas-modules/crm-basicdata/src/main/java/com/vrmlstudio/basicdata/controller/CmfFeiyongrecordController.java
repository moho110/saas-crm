package com.vrmlstudio.basicdata.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vrmlstudio.common.annotation.Log;
import com.vrmlstudio.common.core.controller.BaseController;
import com.vrmlstudio.common.core.domain.AjaxResult;
import com.vrmlstudio.common.enums.BusinessType;
import com.vrmlstudio.basicdata.domain.CmfFeiyongrecord;
import com.vrmlstudio.basicdata.service.ICmfFeiyongrecordService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 费用记录Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-basicdata/feiyongrecord")
public class CmfFeiyongrecordController extends BaseController
{
    @Autowired
    private ICmfFeiyongrecordService cmfFeiyongrecordService;

    /**
     * 查询费用记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:feiyongrecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfFeiyongrecord cmfFeiyongrecord)
    {
        startPage();
        List<CmfFeiyongrecord> list = cmfFeiyongrecordService.selectCmfFeiyongrecordList(cmfFeiyongrecord);
        return getDataTable(list);
    }

    /**
     * 导出费用记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:feiyongrecord:export')")
    @Log(title = "费用记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfFeiyongrecord cmfFeiyongrecord)
    {
        List<CmfFeiyongrecord> list = cmfFeiyongrecordService.selectCmfFeiyongrecordList(cmfFeiyongrecord);
        ExcelUtil<CmfFeiyongrecord> util = new ExcelUtil<CmfFeiyongrecord>(CmfFeiyongrecord.class);
        util.exportExcel(response, list, "费用记录数据");
    }

    /**
     * 获取费用记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:feiyongrecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfFeiyongrecordService.selectCmfFeiyongrecordById(id));
    }

    /**
     * 新增费用记录
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:feiyongrecord:add')")
    @Log(title = "费用记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfFeiyongrecord cmfFeiyongrecord)
    {
        return toAjax(cmfFeiyongrecordService.insertCmfFeiyongrecord(cmfFeiyongrecord));
    }

    /**
     * 修改费用记录
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:feiyongrecord:edit')")
    @Log(title = "费用记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfFeiyongrecord cmfFeiyongrecord)
    {
        return toAjax(cmfFeiyongrecordService.updateCmfFeiyongrecord(cmfFeiyongrecord));
    }

    /**
     * 删除费用记录
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:feiyongrecord:remove')")
    @Log(title = "费用记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfFeiyongrecordService.deleteCmfFeiyongrecordByIds(ids));
    }
}
