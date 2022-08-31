package com.vrmlstudio.finance.controller;

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
import com.vrmlstudio.finance.domain.CmfAccesspreshou;
import com.vrmlstudio.finance.service.ICmfAccesspreshouService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 预收款记录Controller
 * 
 * @author VRer
 * @date 2022-04-13
 */
@RestController
@RequestMapping("/crm-finance/accesspreshou")
public class CmfAccesspreshouController extends BaseController
{
    @Autowired
    private ICmfAccesspreshouService cmfAccesspreshouService;

    /**
     * 查询预收款记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesspreshou:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfAccesspreshou cmfAccesspreshou)
    {
        startPage();
        List<CmfAccesspreshou> list = cmfAccesspreshouService.selectCmfAccesspreshouList(cmfAccesspreshou);
        return getDataTable(list);
    }

    /**
     * 导出预收款记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesspreshou:export')")
    @Log(title = "预收款记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfAccesspreshou cmfAccesspreshou)
    {
        List<CmfAccesspreshou> list = cmfAccesspreshouService.selectCmfAccesspreshouList(cmfAccesspreshou);
        ExcelUtil<CmfAccesspreshou> util = new ExcelUtil<CmfAccesspreshou>(CmfAccesspreshou.class);
        util.exportExcel(response, list, "预收款记录数据");
    }

    /**
     * 获取预收款记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesspreshou:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfAccesspreshouService.selectCmfAccesspreshouById(id));
    }

    /**
     * 新增预收款记录
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesspreshou:add')")
    @Log(title = "预收款记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfAccesspreshou cmfAccesspreshou)
    {
        return toAjax(cmfAccesspreshouService.insertCmfAccesspreshou(cmfAccesspreshou));
    }

    /**
     * 修改预收款记录
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesspreshou:edit')")
    @Log(title = "预收款记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfAccesspreshou cmfAccesspreshou)
    {
        return toAjax(cmfAccesspreshouService.updateCmfAccesspreshou(cmfAccesspreshou));
    }

    /**
     * 删除预收款记录
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesspreshou:remove')")
    @Log(title = "预收款记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfAccesspreshouService.deleteCmfAccesspreshouByIds(ids));
    }
}
