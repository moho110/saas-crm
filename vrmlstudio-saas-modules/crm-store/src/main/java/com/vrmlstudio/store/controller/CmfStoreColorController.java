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
import com.vrmlstudio.store.domain.CmfStoreColor;
import com.vrmlstudio.store.service.ICmfStoreColorService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 库存明细颜色Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-store/color")
public class CmfStoreColorController extends BaseController
{
    @Autowired
    private ICmfStoreColorService cmfStoreColorService;

    /**
     * 查询库存明细颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:color:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfStoreColor cmfStoreColor)
    {
        startPage();
        List<CmfStoreColor> list = cmfStoreColorService.selectCmfStoreColorList(cmfStoreColor);
        return getDataTable(list);
    }

    /**
     * 导出库存明细颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:color:export')")
    @Log(title = "库存明细颜色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfStoreColor cmfStoreColor)
    {
        List<CmfStoreColor> list = cmfStoreColorService.selectCmfStoreColorList(cmfStoreColor);
        ExcelUtil<CmfStoreColor> util = new ExcelUtil<CmfStoreColor>(CmfStoreColor.class);
        util.exportExcel(response, list, "库存明细颜色数据");
    }

    /**
     * 获取库存明细颜色详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-store:color:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfStoreColorService.selectCmfStoreColorById(id));
    }

    /**
     * 新增库存明细颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-store:color:add')")
    @Log(title = "库存明细颜色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfStoreColor cmfStoreColor)
    {
        return toAjax(cmfStoreColorService.insertCmfStoreColor(cmfStoreColor));
    }

    /**
     * 修改库存明细颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-store:color:edit')")
    @Log(title = "库存明细颜色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfStoreColor cmfStoreColor)
    {
        return toAjax(cmfStoreColorService.updateCmfStoreColor(cmfStoreColor));
    }

    /**
     * 删除库存明细颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-store:color:remove')")
    @Log(title = "库存明细颜色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfStoreColorService.deleteCmfStoreColorByIds(ids));
    }
}
