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
import com.vrmlstudio.hr.domain.CmfHrmsWorkexperience;
import com.vrmlstudio.hr.service.ICmfHrmsWorkexperienceService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 工作人员工作经验Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-hr/workexperience")
public class CmfHrmsWorkexperienceController extends BaseController
{
    @Autowired
    private ICmfHrmsWorkexperienceService cmfHrmsWorkexperienceService;

    /**
     * 查询工作人员工作经验列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workexperience:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsWorkexperience cmfHrmsWorkexperience)
    {
        startPage();
        List<CmfHrmsWorkexperience> list = cmfHrmsWorkexperienceService.selectCmfHrmsWorkexperienceList(cmfHrmsWorkexperience);
        return getDataTable(list);
    }

    /**
     * 导出工作人员工作经验列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workexperience:export')")
    @Log(title = "工作人员工作经验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsWorkexperience cmfHrmsWorkexperience)
    {
        List<CmfHrmsWorkexperience> list = cmfHrmsWorkexperienceService.selectCmfHrmsWorkexperienceList(cmfHrmsWorkexperience);
        ExcelUtil<CmfHrmsWorkexperience> util = new ExcelUtil<CmfHrmsWorkexperience>(CmfHrmsWorkexperience.class);
        util.exportExcel(response, list, "工作人员工作经验数据");
    }

    /**
     * 获取工作人员工作经验详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workexperience:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsWorkexperienceService.selectCmfHrmsWorkexperienceById(id));
    }

    /**
     * 新增工作人员工作经验
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workexperience:add')")
    @Log(title = "工作人员工作经验", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsWorkexperience cmfHrmsWorkexperience)
    {
        return toAjax(cmfHrmsWorkexperienceService.insertCmfHrmsWorkexperience(cmfHrmsWorkexperience));
    }

    /**
     * 修改工作人员工作经验
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workexperience:edit')")
    @Log(title = "工作人员工作经验", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsWorkexperience cmfHrmsWorkexperience)
    {
        return toAjax(cmfHrmsWorkexperienceService.updateCmfHrmsWorkexperience(cmfHrmsWorkexperience));
    }

    /**
     * 删除工作人员工作经验
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workexperience:remove')")
    @Log(title = "工作人员工作经验", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsWorkexperienceService.deleteCmfHrmsWorkexperienceByIds(ids));
    }
}
