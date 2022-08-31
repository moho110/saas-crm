package com.vrmlstudio.sales.controller;

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
import com.vrmlstudio.sales.domain.CmfSellplanmainDetailColor;
import com.vrmlstudio.sales.service.ICmfSellplanmainDetailColorService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 销售计划明细表颜色Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-sales/scolor")
public class CmfSellplanmainDetailColorController extends BaseController
{
    @Autowired
    private ICmfSellplanmainDetailColorService cmfSellplanmainDetailColorService;

    /**
     * 查询销售计划明细表颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:scolor:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSellplanmainDetailColor cmfSellplanmainDetailColor)
    {
        startPage();
        List<CmfSellplanmainDetailColor> list = cmfSellplanmainDetailColorService.selectCmfSellplanmainDetailColorList(cmfSellplanmainDetailColor);
        return getDataTable(list);
    }

    /**
     * 导出销售计划明细表颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:scolor:export')")
    @Log(title = "销售计划明细表颜色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSellplanmainDetailColor cmfSellplanmainDetailColor)
    {
        List<CmfSellplanmainDetailColor> list = cmfSellplanmainDetailColorService.selectCmfSellplanmainDetailColorList(cmfSellplanmainDetailColor);
        ExcelUtil<CmfSellplanmainDetailColor> util = new ExcelUtil<CmfSellplanmainDetailColor>(CmfSellplanmainDetailColor.class);
        util.exportExcel(response, list, "销售计划明细表颜色数据");
    }

    /**
     * 获取销售计划明细表颜色详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:scolor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSellplanmainDetailColorService.selectCmfSellplanmainDetailColorById(id));
    }

    /**
     * 新增销售计划明细表颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:scolor:add')")
    @Log(title = "销售计划明细表颜色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSellplanmainDetailColor cmfSellplanmainDetailColor)
    {
        return toAjax(cmfSellplanmainDetailColorService.insertCmfSellplanmainDetailColor(cmfSellplanmainDetailColor));
    }

    /**
     * 修改销售计划明细表颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:scolor:edit')")
    @Log(title = "销售计划明细表颜色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSellplanmainDetailColor cmfSellplanmainDetailColor)
    {
        return toAjax(cmfSellplanmainDetailColorService.updateCmfSellplanmainDetailColor(cmfSellplanmainDetailColor));
    }

    /**
     * 删除销售计划明细表颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:scolor:remove')")
    @Log(title = "销售计划明细表颜色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSellplanmainDetailColorService.deleteCmfSellplanmainDetailColorByIds(ids));
    }
}
