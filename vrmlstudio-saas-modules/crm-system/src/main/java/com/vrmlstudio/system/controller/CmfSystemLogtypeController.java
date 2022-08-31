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
import com.vrmlstudio.system.domain.CmfSystemLogtype;
import com.vrmlstudio.system.service.ICmfSystemLogtypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 系统日志类型Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-system/logtype")
public class CmfSystemLogtypeController extends BaseController
{
    @Autowired
    private ICmfSystemLogtypeService cmfSystemLogtypeService;

    /**
     * 查询系统日志类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logtype:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSystemLogtype cmfSystemLogtype)
    {
        startPage();
        List<CmfSystemLogtype> list = cmfSystemLogtypeService.selectCmfSystemLogtypeList(cmfSystemLogtype);
        return getDataTable(list);
    }

    /**
     * 导出系统日志类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logtype:export')")
    @Log(title = "系统日志类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSystemLogtype cmfSystemLogtype)
    {
        List<CmfSystemLogtype> list = cmfSystemLogtypeService.selectCmfSystemLogtypeList(cmfSystemLogtype);
        ExcelUtil<CmfSystemLogtype> util = new ExcelUtil<CmfSystemLogtype>(CmfSystemLogtype.class);
        util.exportExcel(response, list, "系统日志类型数据");
    }

    /**
     * 获取系统日志类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logtype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSystemLogtypeService.selectCmfSystemLogtypeById(id));
    }

    /**
     * 新增系统日志类型
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logtype:add')")
    @Log(title = "系统日志类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSystemLogtype cmfSystemLogtype)
    {
        return toAjax(cmfSystemLogtypeService.insertCmfSystemLogtype(cmfSystemLogtype));
    }

    /**
     * 修改系统日志类型
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logtype:edit')")
    @Log(title = "系统日志类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSystemLogtype cmfSystemLogtype)
    {
        return toAjax(cmfSystemLogtypeService.updateCmfSystemLogtype(cmfSystemLogtype));
    }

    /**
     * 删除系统日志类型
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logtype:remove')")
    @Log(title = "系统日志类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSystemLogtypeService.deleteCmfSystemLogtypeByIds(ids));
    }
}
