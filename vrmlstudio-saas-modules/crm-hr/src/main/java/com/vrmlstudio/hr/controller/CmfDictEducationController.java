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
import com.vrmlstudio.hr.domain.CmfDictEducation;
import com.vrmlstudio.hr.service.ICmfDictEducationService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 教育明细Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/education")
public class CmfDictEducationController extends BaseController
{
    @Autowired
    private ICmfDictEducationService cmfDictEducationService;

    /**
     * 查询教育明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:education:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfDictEducation cmfDictEducation)
    {
        startPage();
        List<CmfDictEducation> list = cmfDictEducationService.selectCmfDictEducationList(cmfDictEducation);
        return getDataTable(list);
    }

    /**
     * 导出教育明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:education:export')")
    @Log(title = "教育明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfDictEducation cmfDictEducation)
    {
        List<CmfDictEducation> list = cmfDictEducationService.selectCmfDictEducationList(cmfDictEducation);
        ExcelUtil<CmfDictEducation> util = new ExcelUtil<CmfDictEducation>(CmfDictEducation.class);
        util.exportExcel(response, list, "教育明细数据");
    }

    /**
     * 获取教育明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:education:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfDictEducationService.selectCmfDictEducationById(id));
    }

    /**
     * 新增教育明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:education:add')")
    @Log(title = "教育明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfDictEducation cmfDictEducation)
    {
        return toAjax(cmfDictEducationService.insertCmfDictEducation(cmfDictEducation));
    }

    /**
     * 修改教育明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:education:edit')")
    @Log(title = "教育明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfDictEducation cmfDictEducation)
    {
        return toAjax(cmfDictEducationService.updateCmfDictEducation(cmfDictEducation));
    }

    /**
     * 删除教育明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:education:remove')")
    @Log(title = "教育明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfDictEducationService.deleteCmfDictEducationByIds(ids));
    }
}
