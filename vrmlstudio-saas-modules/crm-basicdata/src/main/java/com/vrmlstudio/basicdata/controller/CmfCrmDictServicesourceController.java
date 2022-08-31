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
import com.vrmlstudio.basicdata.domain.CmfCrmDictServicesource;
import com.vrmlstudio.basicdata.service.ICmfCrmDictServicesourceService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 服务来源Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-basicdata/servicesource")
public class CmfCrmDictServicesourceController extends BaseController
{
    @Autowired
    private ICmfCrmDictServicesourceService cmfCrmDictServicesourceService;

    /**
     * 查询服务来源列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicesource:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCrmDictServicesource cmfCrmDictServicesource)
    {
        startPage();
        List<CmfCrmDictServicesource> list = cmfCrmDictServicesourceService.selectCmfCrmDictServicesourceList(cmfCrmDictServicesource);
        return getDataTable(list);
    }

    /**
     * 导出服务来源列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicesource:export')")
    @Log(title = "服务来源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCrmDictServicesource cmfCrmDictServicesource)
    {
        List<CmfCrmDictServicesource> list = cmfCrmDictServicesourceService.selectCmfCrmDictServicesourceList(cmfCrmDictServicesource);
        ExcelUtil<CmfCrmDictServicesource> util = new ExcelUtil<CmfCrmDictServicesource>(CmfCrmDictServicesource.class);
        util.exportExcel(response, list, "服务来源数据");
    }

    /**
     * 获取服务来源详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicesource:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCrmDictServicesourceService.selectCmfCrmDictServicesourceById(id));
    }

    /**
     * 新增服务来源
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicesource:add')")
    @Log(title = "服务来源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCrmDictServicesource cmfCrmDictServicesource)
    {
        return toAjax(cmfCrmDictServicesourceService.insertCmfCrmDictServicesource(cmfCrmDictServicesource));
    }

    /**
     * 修改服务来源
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicesource:edit')")
    @Log(title = "服务来源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCrmDictServicesource cmfCrmDictServicesource)
    {
        return toAjax(cmfCrmDictServicesourceService.updateCmfCrmDictServicesource(cmfCrmDictServicesource));
    }

    /**
     * 删除服务来源
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicesource:remove')")
    @Log(title = "服务来源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCrmDictServicesourceService.deleteCmfCrmDictServicesourceByIds(ids));
    }
}
