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
import com.vrmlstudio.system.domain.CmfSystemLog;
import com.vrmlstudio.system.service.ICmfSystemLogService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 系统日志Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-system/log")
public class CmfSystemLogController extends BaseController
{
    @Autowired
    private ICmfSystemLogService cmfSystemLogService;

    /**
     * 查询系统日志列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSystemLog cmfSystemLog)
    {
        startPage();
        List<CmfSystemLog> list = cmfSystemLogService.selectCmfSystemLogList(cmfSystemLog);
        return getDataTable(list);
    }

    /**
     * 导出系统日志列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:log:export')")
    @Log(title = "系统日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSystemLog cmfSystemLog)
    {
        List<CmfSystemLog> list = cmfSystemLogService.selectCmfSystemLogList(cmfSystemLog);
        ExcelUtil<CmfSystemLog> util = new ExcelUtil<CmfSystemLog>(CmfSystemLog.class);
        util.exportExcel(response, list, "系统日志数据");
    }

    /**
     * 获取系统日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-system:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSystemLogService.selectCmfSystemLogById(id));
    }

    /**
     * 新增系统日志
     */
    @PreAuthorize("@ss.hasPermi('crm-system:log:add')")
    @Log(title = "系统日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSystemLog cmfSystemLog)
    {
        return toAjax(cmfSystemLogService.insertCmfSystemLog(cmfSystemLog));
    }

    /**
     * 修改系统日志
     */
    @PreAuthorize("@ss.hasPermi('crm-system:log:edit')")
    @Log(title = "系统日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSystemLog cmfSystemLog)
    {
        return toAjax(cmfSystemLogService.updateCmfSystemLog(cmfSystemLog));
    }

    /**
     * 删除系统日志
     */
    @PreAuthorize("@ss.hasPermi('crm-system:log:remove')")
    @Log(title = "系统日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSystemLogService.deleteCmfSystemLogByIds(ids));
    }
}
