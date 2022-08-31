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
import com.vrmlstudio.store.domain.CmfStockchangemain;
import com.vrmlstudio.store.service.ICmfStockchangemainService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 仓库管理调拔Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-store/stockchangemain")
public class CmfStockchangemainController extends BaseController
{
    @Autowired
    private ICmfStockchangemainService cmfStockchangemainService;

    /**
     * 查询仓库管理调拔列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangemain:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfStockchangemain cmfStockchangemain)
    {
        startPage();
        List<CmfStockchangemain> list = cmfStockchangemainService.selectCmfStockchangemainList(cmfStockchangemain);
        return getDataTable(list);
    }

    /**
     * 导出仓库管理调拔列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangemain:export')")
    @Log(title = "仓库管理调拔", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfStockchangemain cmfStockchangemain)
    {
        List<CmfStockchangemain> list = cmfStockchangemainService.selectCmfStockchangemainList(cmfStockchangemain);
        ExcelUtil<CmfStockchangemain> util = new ExcelUtil<CmfStockchangemain>(CmfStockchangemain.class);
        util.exportExcel(response, list, "仓库管理调拔数据");
    }

    /**
     * 获取仓库管理调拔详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangemain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfStockchangemainService.selectCmfStockchangemainById(id));
    }

    /**
     * 新增仓库管理调拔
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangemain:add')")
    @Log(title = "仓库管理调拔", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfStockchangemain cmfStockchangemain)
    {
        return toAjax(cmfStockchangemainService.insertCmfStockchangemain(cmfStockchangemain));
    }

    /**
     * 修改仓库管理调拔
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangemain:edit')")
    @Log(title = "仓库管理调拔", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfStockchangemain cmfStockchangemain)
    {
        return toAjax(cmfStockchangemainService.updateCmfStockchangemain(cmfStockchangemain));
    }

    /**
     * 删除仓库管理调拔
     */
    @PreAuthorize("@ss.hasPermi('crm-store:stockchangemain:remove')")
    @Log(title = "仓库管理调拔", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfStockchangemainService.deleteCmfStockchangemainByIds(ids));
    }
}
