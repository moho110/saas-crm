package com.vrmlstudio.basicdata.controller;

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
import com.vrmlstudio.basicdata.domain.CmfDepartment;
import com.vrmlstudio.basicdata.service.ICmfDepartmentService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 部门管理Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-basicdata/department")
public class CmfDepartmentController extends BaseController
{
    @Autowired
    private ICmfDepartmentService cmfDepartmentService;

    /**
     * 查询部门管理列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:department:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfDepartment cmfDepartment)
    {
        startPage();
        List<CmfDepartment> list = cmfDepartmentService.selectCmfDepartmentList(cmfDepartment);
        return getDataTable(list);
    }

    /**
     * 导出部门管理列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:department:export')")
    @Log(title = "部门管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfDepartment cmfDepartment)
    {
        List<CmfDepartment> list = cmfDepartmentService.selectCmfDepartmentList(cmfDepartment);
        ExcelUtil<CmfDepartment> util = new ExcelUtil<CmfDepartment>(CmfDepartment.class);
        util.exportExcel(response, list, "部门管理数据");
    }

    /**
     * 获取部门管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:department:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfDepartmentService.selectCmfDepartmentById(id));
    }

    /**
     * 新增部门管理
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:department:add')")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfDepartment cmfDepartment)
    {
        return toAjax(cmfDepartmentService.insertCmfDepartment(cmfDepartment));
    }

    /**
     * 修改部门管理
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:department:edit')")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfDepartment cmfDepartment)
    {
        return toAjax(cmfDepartmentService.updateCmfDepartment(cmfDepartment));
    }

    /**
     * 删除部门管理
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:department:remove')")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfDepartmentService.deleteCmfDepartmentByIds(ids));
    }
}
