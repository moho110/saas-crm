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
import com.vrmlstudio.hr.domain.CmfHrmsWorkerZhicheng;
import com.vrmlstudio.hr.service.ICmfHrmsWorkerZhichengService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 工作人员职称Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-hr/zhicheng")
public class CmfHrmsWorkerZhichengController extends BaseController
{
    @Autowired
    private ICmfHrmsWorkerZhichengService cmfHrmsWorkerZhichengService;

    /**
     * 查询工作人员职称列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhicheng:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsWorkerZhicheng cmfHrmsWorkerZhicheng)
    {
        startPage();
        List<CmfHrmsWorkerZhicheng> list = cmfHrmsWorkerZhichengService.selectCmfHrmsWorkerZhichengList(cmfHrmsWorkerZhicheng);
        return getDataTable(list);
    }

    /**
     * 导出工作人员职称列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhicheng:export')")
    @Log(title = "工作人员职称", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsWorkerZhicheng cmfHrmsWorkerZhicheng)
    {
        List<CmfHrmsWorkerZhicheng> list = cmfHrmsWorkerZhichengService.selectCmfHrmsWorkerZhichengList(cmfHrmsWorkerZhicheng);
        ExcelUtil<CmfHrmsWorkerZhicheng> util = new ExcelUtil<CmfHrmsWorkerZhicheng>(CmfHrmsWorkerZhicheng.class);
        util.exportExcel(response, list, "工作人员职称数据");
    }

    /**
     * 获取工作人员职称详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhicheng:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsWorkerZhichengService.selectCmfHrmsWorkerZhichengById(id));
    }

    /**
     * 新增工作人员职称
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhicheng:add')")
    @Log(title = "工作人员职称", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsWorkerZhicheng cmfHrmsWorkerZhicheng)
    {
        return toAjax(cmfHrmsWorkerZhichengService.insertCmfHrmsWorkerZhicheng(cmfHrmsWorkerZhicheng));
    }

    /**
     * 修改工作人员职称
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhicheng:edit')")
    @Log(title = "工作人员职称", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsWorkerZhicheng cmfHrmsWorkerZhicheng)
    {
        return toAjax(cmfHrmsWorkerZhichengService.updateCmfHrmsWorkerZhicheng(cmfHrmsWorkerZhicheng));
    }

    /**
     * 删除工作人员职称
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhicheng:remove')")
    @Log(title = "工作人员职称", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsWorkerZhichengService.deleteCmfHrmsWorkerZhichengByIds(ids));
    }
}
