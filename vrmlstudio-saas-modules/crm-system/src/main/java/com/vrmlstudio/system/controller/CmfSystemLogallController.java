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
import com.vrmlstudio.system.domain.CmfSystemLogall;
import com.vrmlstudio.system.service.ICmfSystemLogallService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 系统操作日志Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-system/logall")
public class CmfSystemLogallController extends BaseController
{
    @Autowired
    private ICmfSystemLogallService cmfSystemLogallService;

    /**
     * 查询系统操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logall:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSystemLogall cmfSystemLogall)
    {
        startPage();
        List<CmfSystemLogall> list = cmfSystemLogallService.selectCmfSystemLogallList(cmfSystemLogall);
        return getDataTable(list);
    }

    /**
     * 导出系统操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logall:export')")
    @Log(title = "系统操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSystemLogall cmfSystemLogall)
    {
        List<CmfSystemLogall> list = cmfSystemLogallService.selectCmfSystemLogallList(cmfSystemLogall);
        ExcelUtil<CmfSystemLogall> util = new ExcelUtil<CmfSystemLogall>(CmfSystemLogall.class);
        util.exportExcel(response, list, "系统操作日志数据");
    }

    /**
     * 获取系统操作日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logall:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSystemLogallService.selectCmfSystemLogallById(id));
    }

    /**
     * 新增系统操作日志
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logall:add')")
    @Log(title = "系统操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSystemLogall cmfSystemLogall)
    {
        return toAjax(cmfSystemLogallService.insertCmfSystemLogall(cmfSystemLogall));
    }

    /**
     * 修改系统操作日志
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logall:edit')")
    @Log(title = "系统操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSystemLogall cmfSystemLogall)
    {
        return toAjax(cmfSystemLogallService.updateCmfSystemLogall(cmfSystemLogall));
    }

    /**
     * 删除系统操作日志
     */
    @PreAuthorize("@ss.hasPermi('crm-system:logall:remove')")
    @Log(title = "系统操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSystemLogallService.deleteCmfSystemLogallByIds(ids));
    }
}
