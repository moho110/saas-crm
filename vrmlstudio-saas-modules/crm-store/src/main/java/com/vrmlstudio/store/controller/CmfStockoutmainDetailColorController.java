package com.vrmlstudio.store.controller;

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
import com.vrmlstudio.store.domain.CmfStockoutmainDetailColor;
import com.vrmlstudio.store.service.ICmfStockoutmainDetailColorService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 仓库出库明细颜色Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-store/outdetailcolor")
public class CmfStockoutmainDetailColorController extends BaseController
{
    @Autowired
    private ICmfStockoutmainDetailColorService cmfStockoutmainDetailColorService;

    /**
     * 查询仓库出库明细颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:outdetailcolor:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfStockoutmainDetailColor cmfStockoutmainDetailColor)
    {
        startPage();
        List<CmfStockoutmainDetailColor> list = cmfStockoutmainDetailColorService.selectCmfStockoutmainDetailColorList(cmfStockoutmainDetailColor);
        return getDataTable(list);
    }

    /**
     * 导出仓库出库明细颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:outdetailcolor:export')")
    @Log(title = "仓库出库明细颜色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfStockoutmainDetailColor cmfStockoutmainDetailColor)
    {
        List<CmfStockoutmainDetailColor> list = cmfStockoutmainDetailColorService.selectCmfStockoutmainDetailColorList(cmfStockoutmainDetailColor);
        ExcelUtil<CmfStockoutmainDetailColor> util = new ExcelUtil<CmfStockoutmainDetailColor>(CmfStockoutmainDetailColor.class);
        util.exportExcel(response, list, "仓库出库明细颜色数据");
    }

    /**
     * 获取仓库出库明细颜色详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-store:outdetailcolor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfStockoutmainDetailColorService.selectCmfStockoutmainDetailColorById(id));
    }

    /**
     * 新增仓库出库明细颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-store:outdetailcolor:add')")
    @Log(title = "仓库出库明细颜色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfStockoutmainDetailColor cmfStockoutmainDetailColor)
    {
        return toAjax(cmfStockoutmainDetailColorService.insertCmfStockoutmainDetailColor(cmfStockoutmainDetailColor));
    }

    /**
     * 修改仓库出库明细颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-store:outdetailcolor:edit')")
    @Log(title = "仓库出库明细颜色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfStockoutmainDetailColor cmfStockoutmainDetailColor)
    {
        return toAjax(cmfStockoutmainDetailColorService.updateCmfStockoutmainDetailColor(cmfStockoutmainDetailColor));
    }

    /**
     * 删除仓库出库明细颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-store:outdetailcolor:remove')")
    @Log(title = "仓库出库明细颜色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfStockoutmainDetailColorService.deleteCmfStockoutmainDetailColorByIds(ids));
    }
}
