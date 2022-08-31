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
import com.vrmlstudio.basicdata.domain.CmfCertificate;
import com.vrmlstudio.basicdata.service.ICmfCertificateService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 认证Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-basicdata/certificate")
public class CmfCertificateController extends BaseController
{
    @Autowired
    private ICmfCertificateService cmfCertificateService;

    /**
     * 查询认证列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:certificate:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCertificate cmfCertificate)
    {
        startPage();
        List<CmfCertificate> list = cmfCertificateService.selectCmfCertificateList(cmfCertificate);
        return getDataTable(list);
    }

    /**
     * 导出认证列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:certificate:export')")
    @Log(title = "认证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCertificate cmfCertificate)
    {
        List<CmfCertificate> list = cmfCertificateService.selectCmfCertificateList(cmfCertificate);
        ExcelUtil<CmfCertificate> util = new ExcelUtil<CmfCertificate>(CmfCertificate.class);
        util.exportExcel(response, list, "认证数据");
    }

    /**
     * 获取认证详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:certificate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCertificateService.selectCmfCertificateById(id));
    }

    /**
     * 新增认证
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:certificate:add')")
    @Log(title = "认证", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCertificate cmfCertificate)
    {
        return toAjax(cmfCertificateService.insertCmfCertificate(cmfCertificate));
    }

    /**
     * 修改认证
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:certificate:edit')")
    @Log(title = "认证", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCertificate cmfCertificate)
    {
        return toAjax(cmfCertificateService.updateCmfCertificate(cmfCertificate));
    }

    /**
     * 删除认证
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:certificate:remove')")
    @Log(title = "认证", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCertificateService.deleteCmfCertificateByIds(ids));
    }
}
