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
import com.vrmlstudio.mytable.domain.CmfEmail;
import com.vrmlstudio.mytable.service.ICmfEmailService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 发送邮件Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-mytable/email")
public class CmfEmailController extends BaseController
{
    @Autowired
    private ICmfEmailService cmfEmailService;

    /**
     * 查询发送邮件列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:email:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEmail cmfEmail)
    {
        startPage();
        List<CmfEmail> list = cmfEmailService.selectCmfEmailList(cmfEmail);
        return getDataTable(list);
    }

    /**
     * 导出发送邮件列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:email:export')")
    @Log(title = "发送邮件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEmail cmfEmail)
    {
        List<CmfEmail> list = cmfEmailService.selectCmfEmailList(cmfEmail);
        ExcelUtil<CmfEmail> util = new ExcelUtil<CmfEmail>(CmfEmail.class);
        util.exportExcel(response, list, "发送邮件数据");
    }

    /**
     * 获取发送邮件详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:email:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEmailService.selectCmfEmailById(id));
    }

    /**
     * 新增发送邮件
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:email:add')")
    @Log(title = "发送邮件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEmail cmfEmail)
    {
        return toAjax(cmfEmailService.insertCmfEmail(cmfEmail));
    }

    /**
     * 修改发送邮件
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:email:edit')")
    @Log(title = "发送邮件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEmail cmfEmail)
    {
        return toAjax(cmfEmailService.updateCmfEmail(cmfEmail));
    }

    /**
     * 删除发送邮件
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:email:remove')")
    @Log(title = "发送邮件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEmailService.deleteCmfEmailByIds(ids));
    }
}
