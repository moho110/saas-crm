package com.vrmlstudio.hr.controller;

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
import com.vrmlstudio.hr.domain.CmfEduXingzhengGroup;
import com.vrmlstudio.hr.service.ICmfEduXingzhengGroupService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 行政分类Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/xingzhenggroup")
public class CmfEduXingzhengGroupController extends BaseController
{
    @Autowired
    private ICmfEduXingzhengGroupService cmfEduXingzhengGroupService;

    /**
     * 查询行政分类列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhenggroup:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduXingzhengGroup cmfEduXingzhengGroup)
    {
        startPage();
        List<CmfEduXingzhengGroup> list = cmfEduXingzhengGroupService.selectCmfEduXingzhengGroupList(cmfEduXingzhengGroup);
        return getDataTable(list);
    }

    /**
     * 导出行政分类列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhenggroup:export')")
    @Log(title = "行政分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduXingzhengGroup cmfEduXingzhengGroup)
    {
        List<CmfEduXingzhengGroup> list = cmfEduXingzhengGroupService.selectCmfEduXingzhengGroupList(cmfEduXingzhengGroup);
        ExcelUtil<CmfEduXingzhengGroup> util = new ExcelUtil<CmfEduXingzhengGroup>(CmfEduXingzhengGroup.class);
        util.exportExcel(response, list, "行政分类数据");
    }

    /**
     * 获取行政分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhenggroup:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduXingzhengGroupService.selectCmfEduXingzhengGroupById(id));
    }

    /**
     * 新增行政分类
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhenggroup:add')")
    @Log(title = "行政分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduXingzhengGroup cmfEduXingzhengGroup)
    {
        return toAjax(cmfEduXingzhengGroupService.insertCmfEduXingzhengGroup(cmfEduXingzhengGroup));
    }

    /**
     * 修改行政分类
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhenggroup:edit')")
    @Log(title = "行政分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduXingzhengGroup cmfEduXingzhengGroup)
    {
        return toAjax(cmfEduXingzhengGroupService.updateCmfEduXingzhengGroup(cmfEduXingzhengGroup));
    }

    /**
     * 删除行政分类
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhenggroup:remove')")
    @Log(title = "行政分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduXingzhengGroupService.deleteCmfEduXingzhengGroupByIds(ids));
    }
}
