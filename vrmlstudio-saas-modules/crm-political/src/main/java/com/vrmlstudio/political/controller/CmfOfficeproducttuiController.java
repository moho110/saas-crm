package com.vrmlstudio.political.controller;

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
import com.vrmlstudio.political.domain.CmfOfficeproducttui;
import com.vrmlstudio.political.service.ICmfOfficeproducttuiService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 办公用品归还Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-political/officeproducttui")
public class CmfOfficeproducttuiController extends BaseController
{
    @Autowired
    private ICmfOfficeproducttuiService cmfOfficeproducttuiService;

    /**
     * 查询办公用品归还列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttui:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfOfficeproducttui cmfOfficeproducttui)
    {
        startPage();
        List<CmfOfficeproducttui> list = cmfOfficeproducttuiService.selectCmfOfficeproducttuiList(cmfOfficeproducttui);
        return getDataTable(list);
    }

    /**
     * 导出办公用品归还列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttui:export')")
    @Log(title = "办公用品归还", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfOfficeproducttui cmfOfficeproducttui)
    {
        List<CmfOfficeproducttui> list = cmfOfficeproducttuiService.selectCmfOfficeproducttuiList(cmfOfficeproducttui);
        ExcelUtil<CmfOfficeproducttui> util = new ExcelUtil<CmfOfficeproducttui>(CmfOfficeproducttui.class);
        util.exportExcel(response, list, "办公用品归还数据");
    }

    /**
     * 获取办公用品归还详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttui:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfOfficeproducttuiService.selectCmfOfficeproducttuiById(id));
    }

    /**
     * 新增办公用品归还
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttui:add')")
    @Log(title = "办公用品归还", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfOfficeproducttui cmfOfficeproducttui)
    {
        return toAjax(cmfOfficeproducttuiService.insertCmfOfficeproducttui(cmfOfficeproducttui));
    }

    /**
     * 修改办公用品归还
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttui:edit')")
    @Log(title = "办公用品归还", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfOfficeproducttui cmfOfficeproducttui)
    {
        return toAjax(cmfOfficeproducttuiService.updateCmfOfficeproducttui(cmfOfficeproducttui));
    }

    /**
     * 删除办公用品归还
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttui:remove')")
    @Log(title = "办公用品归还", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfOfficeproducttuiService.deleteCmfOfficeproducttuiByIds(ids));
    }
}
