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
import com.vrmlstudio.political.domain.CmfFixedassetleibie;
import com.vrmlstudio.political.service.ICmfFixedassetleibieService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 固定资产类别Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-political/fixedassetleibie")
public class CmfFixedassetleibieController extends BaseController
{
    @Autowired
    private ICmfFixedassetleibieService cmfFixedassetleibieService;

    /**
     * 查询固定资产类别列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetleibie:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfFixedassetleibie cmfFixedassetleibie)
    {
        startPage();
        List<CmfFixedassetleibie> list = cmfFixedassetleibieService.selectCmfFixedassetleibieList(cmfFixedassetleibie);
        return getDataTable(list);
    }

    /**
     * 导出固定资产类别列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetleibie:export')")
    @Log(title = "固定资产类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfFixedassetleibie cmfFixedassetleibie)
    {
        List<CmfFixedassetleibie> list = cmfFixedassetleibieService.selectCmfFixedassetleibieList(cmfFixedassetleibie);
        ExcelUtil<CmfFixedassetleibie> util = new ExcelUtil<CmfFixedassetleibie>(CmfFixedassetleibie.class);
        util.exportExcel(response, list, "固定资产类别数据");
    }

    /**
     * 获取固定资产类别详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetleibie:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfFixedassetleibieService.selectCmfFixedassetleibieById(id));
    }

    /**
     * 新增固定资产类别
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetleibie:add')")
    @Log(title = "固定资产类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfFixedassetleibie cmfFixedassetleibie)
    {
        return toAjax(cmfFixedassetleibieService.insertCmfFixedassetleibie(cmfFixedassetleibie));
    }

    /**
     * 修改固定资产类别
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetleibie:edit')")
    @Log(title = "固定资产类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfFixedassetleibie cmfFixedassetleibie)
    {
        return toAjax(cmfFixedassetleibieService.updateCmfFixedassetleibie(cmfFixedassetleibie));
    }

    /**
     * 删除固定资产类别
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetleibie:remove')")
    @Log(title = "固定资产类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfFixedassetleibieService.deleteCmfFixedassetleibieByIds(ids));
    }
}
