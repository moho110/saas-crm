package com.vrmlstudio.system.controller;

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
import com.vrmlstudio.system.domain.CmfSystemtable;
import com.vrmlstudio.system.service.ICmfSystemtableService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 系统表Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-system/systemtable")
public class CmfSystemtableController extends BaseController
{
    @Autowired
    private ICmfSystemtableService cmfSystemtableService;

    /**
     * 查询系统表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemtable:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSystemtable cmfSystemtable)
    {
        startPage();
        List<CmfSystemtable> list = cmfSystemtableService.selectCmfSystemtableList(cmfSystemtable);
        return getDataTable(list);
    }

    /**
     * 导出系统表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemtable:export')")
    @Log(title = "系统表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSystemtable cmfSystemtable)
    {
        List<CmfSystemtable> list = cmfSystemtableService.selectCmfSystemtableList(cmfSystemtable);
        ExcelUtil<CmfSystemtable> util = new ExcelUtil<CmfSystemtable>(CmfSystemtable.class);
        util.exportExcel(response, list, "系统表数据");
    }

    /**
     * 获取系统表详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemtable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSystemtableService.selectCmfSystemtableById(id));
    }

    /**
     * 新增系统表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemtable:add')")
    @Log(title = "系统表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSystemtable cmfSystemtable)
    {
        return toAjax(cmfSystemtableService.insertCmfSystemtable(cmfSystemtable));
    }

    /**
     * 修改系统表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemtable:edit')")
    @Log(title = "系统表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSystemtable cmfSystemtable)
    {
        return toAjax(cmfSystemtableService.updateCmfSystemtable(cmfSystemtable));
    }

    /**
     * 删除系统表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemtable:remove')")
    @Log(title = "系统表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSystemtableService.deleteCmfSystemtableByIds(ids));
    }
}
