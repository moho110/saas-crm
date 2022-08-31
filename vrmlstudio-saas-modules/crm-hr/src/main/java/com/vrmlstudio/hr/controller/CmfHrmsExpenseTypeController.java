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
import com.vrmlstudio.hr.domain.CmfHrmsExpenseType;
import com.vrmlstudio.hr.service.ICmfHrmsExpenseTypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 费用类型Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/type")
public class CmfHrmsExpenseTypeController extends BaseController
{
    @Autowired
    private ICmfHrmsExpenseTypeService cmfHrmsExpenseTypeService;

    /**
     * 查询费用类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsExpenseType cmfHrmsExpenseType)
    {
        startPage();
        List<CmfHrmsExpenseType> list = cmfHrmsExpenseTypeService.selectCmfHrmsExpenseTypeList(cmfHrmsExpenseType);
        return getDataTable(list);
    }

    /**
     * 导出费用类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:type:export')")
    @Log(title = "费用类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsExpenseType cmfHrmsExpenseType)
    {
        List<CmfHrmsExpenseType> list = cmfHrmsExpenseTypeService.selectCmfHrmsExpenseTypeList(cmfHrmsExpenseType);
        ExcelUtil<CmfHrmsExpenseType> util = new ExcelUtil<CmfHrmsExpenseType>(CmfHrmsExpenseType.class);
        util.exportExcel(response, list, "费用类型数据");
    }

    /**
     * 获取费用类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsExpenseTypeService.selectCmfHrmsExpenseTypeById(id));
    }

    /**
     * 新增费用类型
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:type:add')")
    @Log(title = "费用类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsExpenseType cmfHrmsExpenseType)
    {
        return toAjax(cmfHrmsExpenseTypeService.insertCmfHrmsExpenseType(cmfHrmsExpenseType));
    }

    /**
     * 修改费用类型
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:type:edit')")
    @Log(title = "费用类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsExpenseType cmfHrmsExpenseType)
    {
        return toAjax(cmfHrmsExpenseTypeService.updateCmfHrmsExpenseType(cmfHrmsExpenseType));
    }

    /**
     * 删除费用类型
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:type:remove')")
    @Log(title = "费用类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsExpenseTypeService.deleteCmfHrmsExpenseTypeByIds(ids));
    }
}
