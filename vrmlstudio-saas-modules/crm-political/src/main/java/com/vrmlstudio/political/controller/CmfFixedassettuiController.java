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
import com.vrmlstudio.political.domain.CmfFixedassettui;
import com.vrmlstudio.political.service.ICmfFixedassettuiService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 固定资产归还Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-political/fixedassettui")
public class CmfFixedassettuiController extends BaseController
{
    @Autowired
    private ICmfFixedassettuiService cmfFixedassettuiService;

    /**
     * 查询固定资产归还列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassettui:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfFixedassettui cmfFixedassettui)
    {
        startPage();
        List<CmfFixedassettui> list = cmfFixedassettuiService.selectCmfFixedassettuiList(cmfFixedassettui);
        return getDataTable(list);
    }

    /**
     * 导出固定资产归还列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassettui:export')")
    @Log(title = "固定资产归还", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfFixedassettui cmfFixedassettui)
    {
        List<CmfFixedassettui> list = cmfFixedassettuiService.selectCmfFixedassettuiList(cmfFixedassettui);
        ExcelUtil<CmfFixedassettui> util = new ExcelUtil<CmfFixedassettui>(CmfFixedassettui.class);
        util.exportExcel(response, list, "固定资产归还数据");
    }

    /**
     * 获取固定资产归还详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassettui:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfFixedassettuiService.selectCmfFixedassettuiById(id));
    }

    /**
     * 新增固定资产归还
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassettui:add')")
    @Log(title = "固定资产归还", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfFixedassettui cmfFixedassettui)
    {
        return toAjax(cmfFixedassettuiService.insertCmfFixedassettui(cmfFixedassettui));
    }

    /**
     * 修改固定资产归还
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassettui:edit')")
    @Log(title = "固定资产归还", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfFixedassettui cmfFixedassettui)
    {
        return toAjax(cmfFixedassettuiService.updateCmfFixedassettui(cmfFixedassettui));
    }

    /**
     * 删除固定资产归还
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassettui:remove')")
    @Log(title = "固定资产归还", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfFixedassettuiService.deleteCmfFixedassettuiByIds(ids));
    }
}
