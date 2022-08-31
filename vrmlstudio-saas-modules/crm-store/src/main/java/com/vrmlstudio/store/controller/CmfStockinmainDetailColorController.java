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
import com.vrmlstudio.store.domain.CmfStockinmainDetailColor;
import com.vrmlstudio.store.service.ICmfStockinmainDetailColorService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 仓库管理入库明细颜色Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-store/incolor")
public class CmfStockinmainDetailColorController extends BaseController
{
    @Autowired
    private ICmfStockinmainDetailColorService cmfStockinmainDetailColorService;

    /**
     * 查询仓库管理入库明细颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:incolor:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfStockinmainDetailColor cmfStockinmainDetailColor)
    {
        startPage();
        List<CmfStockinmainDetailColor> list = cmfStockinmainDetailColorService.selectCmfStockinmainDetailColorList(cmfStockinmainDetailColor);
        return getDataTable(list);
    }

    /**
     * 导出仓库管理入库明细颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:incolor:export')")
    @Log(title = "仓库管理入库明细颜色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfStockinmainDetailColor cmfStockinmainDetailColor)
    {
        List<CmfStockinmainDetailColor> list = cmfStockinmainDetailColorService.selectCmfStockinmainDetailColorList(cmfStockinmainDetailColor);
        ExcelUtil<CmfStockinmainDetailColor> util = new ExcelUtil<CmfStockinmainDetailColor>(CmfStockinmainDetailColor.class);
        util.exportExcel(response, list, "仓库管理入库明细颜色数据");
    }

    /**
     * 获取仓库管理入库明细颜色详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-store:incolor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfStockinmainDetailColorService.selectCmfStockinmainDetailColorById(id));
    }

    /**
     * 新增仓库管理入库明细颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-store:incolor:add')")
    @Log(title = "仓库管理入库明细颜色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfStockinmainDetailColor cmfStockinmainDetailColor)
    {
        return toAjax(cmfStockinmainDetailColorService.insertCmfStockinmainDetailColor(cmfStockinmainDetailColor));
    }

    /**
     * 修改仓库管理入库明细颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-store:incolor:edit')")
    @Log(title = "仓库管理入库明细颜色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfStockinmainDetailColor cmfStockinmainDetailColor)
    {
        return toAjax(cmfStockinmainDetailColorService.updateCmfStockinmainDetailColor(cmfStockinmainDetailColor));
    }

    /**
     * 删除仓库管理入库明细颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-store:incolor:remove')")
    @Log(title = "仓库管理入库明细颜色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfStockinmainDetailColorService.deleteCmfStockinmainDetailColorByIds(ids));
    }
}
