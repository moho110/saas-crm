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
import com.vrmlstudio.finance.domain.CmfAccessprepays;
import com.vrmlstudio.finance.service.ICmfAccessprepaysService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 预付款记录Controller
 * 
 * @author VRer
 * @date 2022-04-13
 */
@RestController
@RequestMapping("/crm-finance/accessprepays")
public class CmfAccessprepaysController extends BaseController
{
    @Autowired
    private ICmfAccessprepaysService cmfAccessprepaysService;

    /**
     * 查询预付款记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessprepays:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfAccessprepays cmfAccessprepays)
    {
        startPage();
        List<CmfAccessprepays> list = cmfAccessprepaysService.selectCmfAccessprepaysList(cmfAccessprepays);
        return getDataTable(list);
    }

    /**
     * 导出预付款记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessprepays:export')")
    @Log(title = "预付款记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfAccessprepays cmfAccessprepays)
    {
        List<CmfAccessprepays> list = cmfAccessprepaysService.selectCmfAccessprepaysList(cmfAccessprepays);
        ExcelUtil<CmfAccessprepays> util = new ExcelUtil<CmfAccessprepays>(CmfAccessprepays.class);
        util.exportExcel(response, list, "预付款记录数据");
    }

    /**
     * 获取预付款记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessprepays:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfAccessprepaysService.selectCmfAccessprepaysById(id));
    }

    /**
     * 新增预付款记录
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessprepays:add')")
    @Log(title = "预付款记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfAccessprepays cmfAccessprepays)
    {
        return toAjax(cmfAccessprepaysService.insertCmfAccessprepays(cmfAccessprepays));
    }

    /**
     * 修改预付款记录
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessprepays:edit')")
    @Log(title = "预付款记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfAccessprepays cmfAccessprepays)
    {
        return toAjax(cmfAccessprepaysService.updateCmfAccessprepays(cmfAccessprepays));
    }

    /**
     * 删除预付款记录
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessprepays:remove')")
    @Log(title = "预付款记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfAccessprepaysService.deleteCmfAccessprepaysByIds(ids));
    }
}
