package com.vrmlstudio.mytable.controller;

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
import com.vrmlstudio.mytable.domain.CmfWorkplanshenhe;
import com.vrmlstudio.mytable.service.ICmfWorkplanshenheService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 工作计划审核Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-mytable/workplanshenhe")
public class CmfWorkplanshenheController extends BaseController
{
    @Autowired
    private ICmfWorkplanshenheService cmfWorkplanshenheService;

    /**
     * 查询工作计划审核列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:workplanshenhe:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfWorkplanshenhe cmfWorkplanshenhe)
    {
        startPage();
        List<CmfWorkplanshenhe> list = cmfWorkplanshenheService.selectCmfWorkplanshenheList(cmfWorkplanshenhe);
        return getDataTable(list);
    }

    /**
     * 导出工作计划审核列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:workplanshenhe:export')")
    @Log(title = "工作计划审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfWorkplanshenhe cmfWorkplanshenhe)
    {
        List<CmfWorkplanshenhe> list = cmfWorkplanshenheService.selectCmfWorkplanshenheList(cmfWorkplanshenhe);
        ExcelUtil<CmfWorkplanshenhe> util = new ExcelUtil<CmfWorkplanshenhe>(CmfWorkplanshenhe.class);
        util.exportExcel(response, list, "工作计划审核数据");
    }

    /**
     * 获取工作计划审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:workplanshenhe:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfWorkplanshenheService.selectCmfWorkplanshenheById(id));
    }

    /**
     * 新增工作计划审核
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:workplanshenhe:add')")
    @Log(title = "工作计划审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfWorkplanshenhe cmfWorkplanshenhe)
    {
        return toAjax(cmfWorkplanshenheService.insertCmfWorkplanshenhe(cmfWorkplanshenhe));
    }

    /**
     * 修改工作计划审核
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:workplanshenhe:edit')")
    @Log(title = "工作计划审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfWorkplanshenhe cmfWorkplanshenhe)
    {
        return toAjax(cmfWorkplanshenheService.updateCmfWorkplanshenhe(cmfWorkplanshenhe));
    }

    /**
     * 删除工作计划审核
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:workplanshenhe:remove')")
    @Log(title = "工作计划审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfWorkplanshenheService.deleteCmfWorkplanshenheByIds(ids));
    }
}
