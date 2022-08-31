package com.vrmlstudio.mytable.controller;

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
import com.vrmlstudio.mytable.domain.CmfCrmMytable;
import com.vrmlstudio.mytable.service.ICmfCrmMytableService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 桌面模块表Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-mytable/mytable")
public class CmfCrmMytableController extends BaseController
{
    @Autowired
    private ICmfCrmMytableService cmfCrmMytableService;

    /**
     * 查询桌面模块表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytable:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCrmMytable cmfCrmMytable)
    {
        startPage();
        List<CmfCrmMytable> list = cmfCrmMytableService.selectCmfCrmMytableList(cmfCrmMytable);
        return getDataTable(list);
    }

    /**
     * 导出桌面模块表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytable:export')")
    @Log(title = "桌面模块表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCrmMytable cmfCrmMytable)
    {
        List<CmfCrmMytable> list = cmfCrmMytableService.selectCmfCrmMytableList(cmfCrmMytable);
        ExcelUtil<CmfCrmMytable> util = new ExcelUtil<CmfCrmMytable>(CmfCrmMytable.class);
        util.exportExcel(response, list, "桌面模块表数据");
    }

    /**
     * 获取桌面模块表详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmfCrmMytableService.selectCmfCrmMytableById(id));
    }

    /**
     * 新增桌面模块表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytable:add')")
    @Log(title = "桌面模块表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCrmMytable cmfCrmMytable)
    {
        return toAjax(cmfCrmMytableService.insertCmfCrmMytable(cmfCrmMytable));
    }

    /**
     * 修改桌面模块表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytable:edit')")
    @Log(title = "桌面模块表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCrmMytable cmfCrmMytable)
    {
        return toAjax(cmfCrmMytableService.updateCmfCrmMytable(cmfCrmMytable));
    }

    /**
     * 删除桌面模块表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytable:remove')")
    @Log(title = "桌面模块表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmfCrmMytableService.deleteCmfCrmMytableByIds(ids));
    }
}
