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
import com.vrmlstudio.store.domain.CmfStock;
import com.vrmlstudio.store.service.ICmfStockService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 仓库管理Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-store/stock")
public class CmfStockController extends BaseController
{
    @Autowired
    private ICmfStockService cmfStockService;

    /**
     * 查询仓库管理列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stock:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfStock cmfStock)
    {
        startPage();
        List<CmfStock> list = cmfStockService.selectCmfStockList(cmfStock);
        return getDataTable(list);
    }

    /**
     * 导出仓库管理列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stock:export')")
    @Log(title = "仓库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfStock cmfStock)
    {
        List<CmfStock> list = cmfStockService.selectCmfStockList(cmfStock);
        ExcelUtil<CmfStock> util = new ExcelUtil<CmfStock>(CmfStock.class);
        util.exportExcel(response, list, "仓库管理数据");
    }

    /**
     * 获取仓库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stock:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfStockService.selectCmfStockById(id));
    }

    /**
     * 新增仓库管理
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stock:add')")
    @Log(title = "仓库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfStock cmfStock)
    {
        return toAjax(cmfStockService.insertCmfStock(cmfStock));
    }

    /**
     * 修改仓库管理
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stock:edit')")
    @Log(title = "仓库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfStock cmfStock)
    {
        return toAjax(cmfStockService.updateCmfStock(cmfStock));
    }

    /**
     * 删除仓库管理
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stock:remove')")
    @Log(title = "仓库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfStockService.deleteCmfStockByIds(ids));
    }
}
