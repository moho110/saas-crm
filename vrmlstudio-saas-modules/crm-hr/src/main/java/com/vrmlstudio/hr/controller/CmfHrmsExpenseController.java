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
import com.vrmlstudio.hr.domain.CmfHrmsExpense;
import com.vrmlstudio.hr.service.ICmfHrmsExpenseService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 日常费用Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/expense")
public class CmfHrmsExpenseController extends BaseController
{
    @Autowired
    private ICmfHrmsExpenseService cmfHrmsExpenseService;

    /**
     * 查询日常费用列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:expense:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsExpense cmfHrmsExpense)
    {
        startPage();
        List<CmfHrmsExpense> list = cmfHrmsExpenseService.selectCmfHrmsExpenseList(cmfHrmsExpense);
        return getDataTable(list);
    }

    /**
     * 导出日常费用列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:expense:export')")
    @Log(title = "日常费用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsExpense cmfHrmsExpense)
    {
        List<CmfHrmsExpense> list = cmfHrmsExpenseService.selectCmfHrmsExpenseList(cmfHrmsExpense);
        ExcelUtil<CmfHrmsExpense> util = new ExcelUtil<CmfHrmsExpense>(CmfHrmsExpense.class);
        util.exportExcel(response, list, "日常费用数据");
    }

    /**
     * 获取日常费用详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:expense:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsExpenseService.selectCmfHrmsExpenseById(id));
    }

    /**
     * 新增日常费用
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:expense:add')")
    @Log(title = "日常费用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsExpense cmfHrmsExpense)
    {
        return toAjax(cmfHrmsExpenseService.insertCmfHrmsExpense(cmfHrmsExpense));
    }

    /**
     * 修改日常费用
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:expense:edit')")
    @Log(title = "日常费用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsExpense cmfHrmsExpense)
    {
        return toAjax(cmfHrmsExpenseService.updateCmfHrmsExpense(cmfHrmsExpense));
    }

    /**
     * 删除日常费用
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:expense:remove')")
    @Log(title = "日常费用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsExpenseService.deleteCmfHrmsExpenseByIds(ids));
    }
}
