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
import com.vrmlstudio.mytable.domain.CmfEmailstate;
import com.vrmlstudio.mytable.service.ICmfEmailstateService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 邮件状态Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-mytable/emailstate")
public class CmfEmailstateController extends BaseController
{
    @Autowired
    private ICmfEmailstateService cmfEmailstateService;

    /**
     * 查询邮件状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:emailstate:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEmailstate cmfEmailstate)
    {
        startPage();
        List<CmfEmailstate> list = cmfEmailstateService.selectCmfEmailstateList(cmfEmailstate);
        return getDataTable(list);
    }

    /**
     * 导出邮件状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:emailstate:export')")
    @Log(title = "邮件状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEmailstate cmfEmailstate)
    {
        List<CmfEmailstate> list = cmfEmailstateService.selectCmfEmailstateList(cmfEmailstate);
        ExcelUtil<CmfEmailstate> util = new ExcelUtil<CmfEmailstate>(CmfEmailstate.class);
        util.exportExcel(response, list, "邮件状态数据");
    }

    /**
     * 获取邮件状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:emailstate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEmailstateService.selectCmfEmailstateById(id));
    }

    /**
     * 新增邮件状态
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:emailstate:add')")
    @Log(title = "邮件状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEmailstate cmfEmailstate)
    {
        return toAjax(cmfEmailstateService.insertCmfEmailstate(cmfEmailstate));
    }

    /**
     * 修改邮件状态
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:emailstate:edit')")
    @Log(title = "邮件状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEmailstate cmfEmailstate)
    {
        return toAjax(cmfEmailstateService.updateCmfEmailstate(cmfEmailstate));
    }

    /**
     * 删除邮件状态
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:emailstate:remove')")
    @Log(title = "邮件状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEmailstateService.deleteCmfEmailstateByIds(ids));
    }
}
