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
import com.vrmlstudio.hr.domain.CmfDictWeekday;
import com.vrmlstudio.hr.service.ICmfDictWeekdayService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 周工作日Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/weekday")
public class CmfDictWeekdayController extends BaseController
{
    @Autowired
    private ICmfDictWeekdayService cmfDictWeekdayService;

    /**
     * 查询周工作日列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:weekday:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfDictWeekday cmfDictWeekday)
    {
        startPage();
        List<CmfDictWeekday> list = cmfDictWeekdayService.selectCmfDictWeekdayList(cmfDictWeekday);
        return getDataTable(list);
    }

    /**
     * 导出周工作日列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:weekday:export')")
    @Log(title = "周工作日", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfDictWeekday cmfDictWeekday)
    {
        List<CmfDictWeekday> list = cmfDictWeekdayService.selectCmfDictWeekdayList(cmfDictWeekday);
        ExcelUtil<CmfDictWeekday> util = new ExcelUtil<CmfDictWeekday>(CmfDictWeekday.class);
        util.exportExcel(response, list, "周工作日数据");
    }

    /**
     * 获取周工作日详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:weekday:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfDictWeekdayService.selectCmfDictWeekdayById(id));
    }

    /**
     * 新增周工作日
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:weekday:add')")
    @Log(title = "周工作日", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfDictWeekday cmfDictWeekday)
    {
        return toAjax(cmfDictWeekdayService.insertCmfDictWeekday(cmfDictWeekday));
    }

    /**
     * 修改周工作日
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:weekday:edit')")
    @Log(title = "周工作日", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfDictWeekday cmfDictWeekday)
    {
        return toAjax(cmfDictWeekdayService.updateCmfDictWeekday(cmfDictWeekday));
    }

    /**
     * 删除周工作日
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:weekday:remove')")
    @Log(title = "周工作日", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfDictWeekdayService.deleteCmfDictWeekdayByIds(ids));
    }
}
