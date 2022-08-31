package com.vrmlstudio.system.controller;

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
import com.vrmlstudio.system.domain.CmfSysCode;
import com.vrmlstudio.system.service.ICmfSysCodeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 系统代码Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-system/code")
public class CmfSysCodeController extends BaseController
{
    @Autowired
    private ICmfSysCodeService cmfSysCodeService;

    /**
     * 查询系统代码列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:code:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSysCode cmfSysCode)
    {
        startPage();
        List<CmfSysCode> list = cmfSysCodeService.selectCmfSysCodeList(cmfSysCode);
        return getDataTable(list);
    }

    /**
     * 导出系统代码列表
     */
    @PreAuthorize("@ss.hasPermi('crm-system:code:export')")
    @Log(title = "系统代码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSysCode cmfSysCode)
    {
        List<CmfSysCode> list = cmfSysCodeService.selectCmfSysCodeList(cmfSysCode);
        ExcelUtil<CmfSysCode> util = new ExcelUtil<CmfSysCode>(CmfSysCode.class);
        util.exportExcel(response, list, "系统代码数据");
    }

    /**
     * 获取系统代码详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-system:code:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSysCodeService.selectCmfSysCodeById(id));
    }

    /**
     * 新增系统代码
     */
    @PreAuthorize("@ss.hasPermi('crm-system:code:add')")
    @Log(title = "系统代码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSysCode cmfSysCode)
    {
        return toAjax(cmfSysCodeService.insertCmfSysCode(cmfSysCode));
    }

    /**
     * 修改系统代码
     */
    @PreAuthorize("@ss.hasPermi('crm-system:code:edit')")
    @Log(title = "系统代码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSysCode cmfSysCode)
    {
        return toAjax(cmfSysCodeService.updateCmfSysCode(cmfSysCode));
    }

    /**
     * 删除系统代码
     */
    @PreAuthorize("@ss.hasPermi('crm-system:code:remove')")
    @Log(title = "系统代码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSysCodeService.deleteCmfSysCodeByIds(ids));
    }
}
