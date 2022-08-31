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
import com.vrmlstudio.store.domain.CmfStockchangestate;
import com.vrmlstudio.store.service.ICmfStockchangestateService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 仓库管理调拔状态Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-store/stockchangestate")
public class CmfStockchangestateController extends BaseController
{
    @Autowired
    private ICmfStockchangestateService cmfStockchangestateService;

    /**
     * 查询仓库管理调拔状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangestate:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfStockchangestate cmfStockchangestate)
    {
        startPage();
        List<CmfStockchangestate> list = cmfStockchangestateService.selectCmfStockchangestateList(cmfStockchangestate);
        return getDataTable(list);
    }

    /**
     * 导出仓库管理调拔状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangestate:export')")
    @Log(title = "仓库管理调拔状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfStockchangestate cmfStockchangestate)
    {
        List<CmfStockchangestate> list = cmfStockchangestateService.selectCmfStockchangestateList(cmfStockchangestate);
        ExcelUtil<CmfStockchangestate> util = new ExcelUtil<CmfStockchangestate>(CmfStockchangestate.class);
        util.exportExcel(response, list, "仓库管理调拔状态数据");
    }

    /**
     * 获取仓库管理调拔状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangestate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfStockchangestateService.selectCmfStockchangestateById(id));
    }

    /**
     * 新增仓库管理调拔状态
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangestate:add')")
    @Log(title = "仓库管理调拔状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfStockchangestate cmfStockchangestate)
    {
        return toAjax(cmfStockchangestateService.insertCmfStockchangestate(cmfStockchangestate));
    }

    /**
     * 修改仓库管理调拔状态
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangestate:edit')")
    @Log(title = "仓库管理调拔状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfStockchangestate cmfStockchangestate)
    {
        return toAjax(cmfStockchangestateService.updateCmfStockchangestate(cmfStockchangestate));
    }

    /**
     * 删除仓库管理调拔状态
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangestate:remove')")
    @Log(title = "仓库管理调拔状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfStockchangestateService.deleteCmfStockchangestateByIds(ids));
    }
}
