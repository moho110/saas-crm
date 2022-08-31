package com.vrmlstudio.buy.controller;

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
import com.vrmlstudio.buy.domain.CmfBuyplanmainDetailColor;
import com.vrmlstudio.buy.service.ICmfBuyplanmainDetailColorService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 采购计划明细表颜色Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-buy/color")
public class CmfBuyplanmainDetailColorController extends BaseController
{
    @Autowired
    private ICmfBuyplanmainDetailColorService cmfBuyplanmainDetailColorService;

    /**
     * 查询采购计划明细表颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:color:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfBuyplanmainDetailColor cmfBuyplanmainDetailColor)
    {
        startPage();
        List<CmfBuyplanmainDetailColor> list = cmfBuyplanmainDetailColorService.selectCmfBuyplanmainDetailColorList(cmfBuyplanmainDetailColor);
        return getDataTable(list);
    }

    /**
     * 导出采购计划明细表颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:color:export')")
    @Log(title = "采购计划明细表颜色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfBuyplanmainDetailColor cmfBuyplanmainDetailColor)
    {
        List<CmfBuyplanmainDetailColor> list = cmfBuyplanmainDetailColorService.selectCmfBuyplanmainDetailColorList(cmfBuyplanmainDetailColor);
        ExcelUtil<CmfBuyplanmainDetailColor> util = new ExcelUtil<CmfBuyplanmainDetailColor>(CmfBuyplanmainDetailColor.class);
        util.exportExcel(response, list, "采购计划明细表颜色数据");
    }

    /**
     * 获取采购计划明细表颜色详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:color:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfBuyplanmainDetailColorService.selectCmfBuyplanmainDetailColorById(id));
    }

    /**
     * 新增采购计划明细表颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:color:add')")
    @Log(title = "采购计划明细表颜色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfBuyplanmainDetailColor cmfBuyplanmainDetailColor)
    {
        return toAjax(cmfBuyplanmainDetailColorService.insertCmfBuyplanmainDetailColor(cmfBuyplanmainDetailColor));
    }

    /**
     * 修改采购计划明细表颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:color:edit')")
    @Log(title = "采购计划明细表颜色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfBuyplanmainDetailColor cmfBuyplanmainDetailColor)
    {
        return toAjax(cmfBuyplanmainDetailColorService.updateCmfBuyplanmainDetailColor(cmfBuyplanmainDetailColor));
    }

    /**
     * 删除采购计划明细表颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:color:remove')")
    @Log(title = "采购计划明细表颜色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfBuyplanmainDetailColorService.deleteCmfBuyplanmainDetailColorByIds(ids));
    }
}
