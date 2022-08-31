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
import com.vrmlstudio.system.domain.CmfSystemprivateconfig;
import com.vrmlstudio.system.service.ICmfSystemprivateconfigService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 系统权限配置Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-system/systemprivateconfig")
public class CmfSystemprivateconfigController extends BaseController
{
    @Autowired
    private ICmfSystemprivateconfigService cmfSystemprivateconfigService;

    /**
     * 查询系统权限配置列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateconfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSystemprivateconfig cmfSystemprivateconfig)
    {
        startPage();
        List<CmfSystemprivateconfig> list = cmfSystemprivateconfigService.selectCmfSystemprivateconfigList(cmfSystemprivateconfig);
        return getDataTable(list);
    }

    /**
     * 导出系统权限配置列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateconfig:export')")
    @Log(title = "系统权限配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSystemprivateconfig cmfSystemprivateconfig)
    {
        List<CmfSystemprivateconfig> list = cmfSystemprivateconfigService.selectCmfSystemprivateconfigList(cmfSystemprivateconfig);
        ExcelUtil<CmfSystemprivateconfig> util = new ExcelUtil<CmfSystemprivateconfig>(CmfSystemprivateconfig.class);
        util.exportExcel(response, list, "系统权限配置数据");
    }

    /**
     * 获取系统权限配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateconfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSystemprivateconfigService.selectCmfSystemprivateconfigById(id));
    }

    /**
     * 新增系统权限配置
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateconfig:add')")
    @Log(title = "系统权限配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSystemprivateconfig cmfSystemprivateconfig)
    {
        return toAjax(cmfSystemprivateconfigService.insertCmfSystemprivateconfig(cmfSystemprivateconfig));
    }

    /**
     * 修改系统权限配置
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateconfig:edit')")
    @Log(title = "系统权限配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSystemprivateconfig cmfSystemprivateconfig)
    {
        return toAjax(cmfSystemprivateconfigService.updateCmfSystemprivateconfig(cmfSystemprivateconfig));
    }

    /**
     * 删除系统权限配置
     */
    @PreAuthorize("@ss.hasPermi('crm-system:systemprivateconfig:remove')")
    @Log(title = "系统权限配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSystemprivateconfigService.deleteCmfSystemprivateconfigByIds(ids));
    }
}
