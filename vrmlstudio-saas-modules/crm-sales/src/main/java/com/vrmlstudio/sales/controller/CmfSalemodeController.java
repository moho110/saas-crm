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
import com.vrmlstudio.sales.domain.CmfSalemode;
import com.vrmlstudio.sales.service.ICmfSalemodeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 销售模式Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-sales/salemode")
public class CmfSalemodeController extends BaseController
{
    @Autowired
    private ICmfSalemodeService cmfSalemodeService;

    /**
     * 查询销售模式列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:salemode:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSalemode cmfSalemode)
    {
        startPage();
        List<CmfSalemode> list = cmfSalemodeService.selectCmfSalemodeList(cmfSalemode);
        return getDataTable(list);
    }

    /**
     * 导出销售模式列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:salemode:export')")
    @Log(title = "销售模式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSalemode cmfSalemode)
    {
        List<CmfSalemode> list = cmfSalemodeService.selectCmfSalemodeList(cmfSalemode);
        ExcelUtil<CmfSalemode> util = new ExcelUtil<CmfSalemode>(CmfSalemode.class);
        util.exportExcel(response, list, "销售模式数据");
    }

    /**
     * 获取销售模式详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:salemode:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSalemodeService.selectCmfSalemodeById(id));
    }

    /**
     * 新增销售模式
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:salemode:add')")
    @Log(title = "销售模式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSalemode cmfSalemode)
    {
        return toAjax(cmfSalemodeService.insertCmfSalemode(cmfSalemode));
    }

    /**
     * 修改销售模式
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:salemode:edit')")
    @Log(title = "销售模式", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSalemode cmfSalemode)
    {
        return toAjax(cmfSalemodeService.updateCmfSalemode(cmfSalemode));
    }

    /**
     * 删除销售模式
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:salemode:remove')")
    @Log(title = "销售模式", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSalemodeService.deleteCmfSalemodeByIds(ids));
    }
}
