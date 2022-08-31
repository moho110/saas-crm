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
import com.vrmlstudio.political.domain.CmfFixedassetin;
import com.vrmlstudio.political.service.ICmfFixedassetinService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 固定资产入库Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-political/fixedassetin")
public class CmfFixedassetinController extends BaseController
{
    @Autowired
    private ICmfFixedassetinService cmfFixedassetinService;

    /**
     * 查询固定资产入库列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetin:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfFixedassetin cmfFixedassetin)
    {
        startPage();
        List<CmfFixedassetin> list = cmfFixedassetinService.selectCmfFixedassetinList(cmfFixedassetin);
        return getDataTable(list);
    }

    /**
     * 导出固定资产入库列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetin:export')")
    @Log(title = "固定资产入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfFixedassetin cmfFixedassetin)
    {
        List<CmfFixedassetin> list = cmfFixedassetinService.selectCmfFixedassetinList(cmfFixedassetin);
        ExcelUtil<CmfFixedassetin> util = new ExcelUtil<CmfFixedassetin>(CmfFixedassetin.class);
        util.exportExcel(response, list, "固定资产入库数据");
    }

    /**
     * 获取固定资产入库详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfFixedassetinService.selectCmfFixedassetinById(id));
    }

    /**
     * 新增固定资产入库
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetin:add')")
    @Log(title = "固定资产入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfFixedassetin cmfFixedassetin)
    {
        return toAjax(cmfFixedassetinService.insertCmfFixedassetin(cmfFixedassetin));
    }

    /**
     * 修改固定资产入库
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetin:edit')")
    @Log(title = "固定资产入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfFixedassetin cmfFixedassetin)
    {
        return toAjax(cmfFixedassetinService.updateCmfFixedassetin(cmfFixedassetin));
    }

    /**
     * 删除固定资产入库
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetin:remove')")
    @Log(title = "固定资产入库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfFixedassetinService.deleteCmfFixedassetinByIds(ids));
    }
}
