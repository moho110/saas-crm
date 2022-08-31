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
import com.vrmlstudio.system.domain.CmfSystemprivateinc;
import com.vrmlstudio.system.service.ICmfSystemprivateincService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 系统权限配置Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-system/systemprivateinc")
public class CmfSystemprivateincController extends BaseController
{
    @Autowired
    private ICmfSystemprivateincService cmfSystemprivateincService;

    /**
     * 查询系统权限配置列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateinc:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSystemprivateinc cmfSystemprivateinc)
    {
        startPage();
        List<CmfSystemprivateinc> list = cmfSystemprivateincService.selectCmfSystemprivateincList(cmfSystemprivateinc);
        return getDataTable(list);
    }

    /**
     * 导出系统权限配置列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateinc:export')")
    @Log(title = "系统权限配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSystemprivateinc cmfSystemprivateinc)
    {
        List<CmfSystemprivateinc> list = cmfSystemprivateincService.selectCmfSystemprivateincList(cmfSystemprivateinc);
        ExcelUtil<CmfSystemprivateinc> util = new ExcelUtil<CmfSystemprivateinc>(CmfSystemprivateinc.class);
        util.exportExcel(response, list, "系统权限配置数据");
    }

    /**
     * 获取系统权限配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateinc:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSystemprivateincService.selectCmfSystemprivateincById(id));
    }

    /**
     * 新增系统权限配置
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateinc:add')")
    @Log(title = "系统权限配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSystemprivateinc cmfSystemprivateinc)
    {
        return toAjax(cmfSystemprivateincService.insertCmfSystemprivateinc(cmfSystemprivateinc));
    }

    /**
     * 修改系统权限配置
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateinc:edit')")
    @Log(title = "系统权限配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSystemprivateinc cmfSystemprivateinc)
    {
        return toAjax(cmfSystemprivateincService.updateCmfSystemprivateinc(cmfSystemprivateinc));
    }

    /**
     * 删除系统权限配置
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateinc:remove')")
    @Log(title = "系统权限配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSystemprivateincService.deleteCmfSystemprivateincByIds(ids));
    }
}
