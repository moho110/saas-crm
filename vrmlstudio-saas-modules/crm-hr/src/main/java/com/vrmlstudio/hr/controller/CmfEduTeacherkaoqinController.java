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
import com.vrmlstudio.hr.domain.CmfEduTeacherkaoqin;
import com.vrmlstudio.hr.service.ICmfEduTeacherkaoqinService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 教师考勤Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/teacherkaoqin")
public class CmfEduTeacherkaoqinController extends BaseController
{
    @Autowired
    private ICmfEduTeacherkaoqinService cmfEduTeacherkaoqinService;

    /**
     * 查询教师考勤列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:teacherkaoqin:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduTeacherkaoqin cmfEduTeacherkaoqin)
    {
        startPage();
        List<CmfEduTeacherkaoqin> list = cmfEduTeacherkaoqinService.selectCmfEduTeacherkaoqinList(cmfEduTeacherkaoqin);
        return getDataTable(list);
    }

    /**
     * 导出教师考勤列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:teacherkaoqin:export')")
    @Log(title = "教师考勤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduTeacherkaoqin cmfEduTeacherkaoqin)
    {
        List<CmfEduTeacherkaoqin> list = cmfEduTeacherkaoqinService.selectCmfEduTeacherkaoqinList(cmfEduTeacherkaoqin);
        ExcelUtil<CmfEduTeacherkaoqin> util = new ExcelUtil<CmfEduTeacherkaoqin>(CmfEduTeacherkaoqin.class);
        util.exportExcel(response, list, "教师考勤数据");
    }

    /**
     * 获取教师考勤详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:teacherkaoqin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduTeacherkaoqinService.selectCmfEduTeacherkaoqinById(id));
    }

    /**
     * 新增教师考勤
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:teacherkaoqin:add')")
    @Log(title = "教师考勤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduTeacherkaoqin cmfEduTeacherkaoqin)
    {
        return toAjax(cmfEduTeacherkaoqinService.insertCmfEduTeacherkaoqin(cmfEduTeacherkaoqin));
    }

    /**
     * 修改教师考勤
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:teacherkaoqin:edit')")
    @Log(title = "教师考勤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduTeacherkaoqin cmfEduTeacherkaoqin)
    {
        return toAjax(cmfEduTeacherkaoqinService.updateCmfEduTeacherkaoqin(cmfEduTeacherkaoqin));
    }

    /**
     * 删除教师考勤
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:teacherkaoqin:remove')")
    @Log(title = "教师考勤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduTeacherkaoqinService.deleteCmfEduTeacherkaoqinByIds(ids));
    }
}
