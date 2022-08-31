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
import com.vrmlstudio.political.domain.CmfFixedassetgroup;
import com.vrmlstudio.political.service.ICmfFixedassetgroupService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 固定资产分类Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-political/fixedassetgroup")
public class CmfFixedassetgroupController extends BaseController
{
    @Autowired
    private ICmfFixedassetgroupService cmfFixedassetgroupService;

    /**
     * 查询固定资产分类列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetgroup:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfFixedassetgroup cmfFixedassetgroup)
    {
        startPage();
        List<CmfFixedassetgroup> list = cmfFixedassetgroupService.selectCmfFixedassetgroupList(cmfFixedassetgroup);
        return getDataTable(list);
    }

    /**
     * 导出固定资产分类列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetgroup:export')")
    @Log(title = "固定资产分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfFixedassetgroup cmfFixedassetgroup)
    {
        List<CmfFixedassetgroup> list = cmfFixedassetgroupService.selectCmfFixedassetgroupList(cmfFixedassetgroup);
        ExcelUtil<CmfFixedassetgroup> util = new ExcelUtil<CmfFixedassetgroup>(CmfFixedassetgroup.class);
        util.exportExcel(response, list, "固定资产分类数据");
    }

    /**
     * 获取固定资产分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetgroup:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfFixedassetgroupService.selectCmfFixedassetgroupById(id));
    }

    /**
     * 新增固定资产分类
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetgroup:add')")
    @Log(title = "固定资产分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfFixedassetgroup cmfFixedassetgroup)
    {
        return toAjax(cmfFixedassetgroupService.insertCmfFixedassetgroup(cmfFixedassetgroup));
    }

    /**
     * 修改固定资产分类
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetgroup:edit')")
    @Log(title = "固定资产分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfFixedassetgroup cmfFixedassetgroup)
    {
        return toAjax(cmfFixedassetgroupService.updateCmfFixedassetgroup(cmfFixedassetgroup));
    }

    /**
     * 删除固定资产分类
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetgroup:remove')")
    @Log(title = "固定资产分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfFixedassetgroupService.deleteCmfFixedassetgroupByIds(ids));
    }
}
