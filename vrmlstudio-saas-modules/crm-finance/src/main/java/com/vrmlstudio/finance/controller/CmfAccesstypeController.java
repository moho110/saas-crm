package com.vrmlstudio.finance.controller;

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
import com.vrmlstudio.finance.domain.CmfAccesstype;
import com.vrmlstudio.finance.service.ICmfAccesstypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 帐户操作类型Controller
 * 
 * @author VRer
 * @date 2022-04-13
 */
@RestController
@RequestMapping("/crm-finance/accesstype")
public class CmfAccesstypeController extends BaseController
{
    @Autowired
    private ICmfAccesstypeService cmfAccesstypeService;

    /**
     * 查询帐户操作类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesstype:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfAccesstype cmfAccesstype)
    {
        startPage();
        List<CmfAccesstype> list = cmfAccesstypeService.selectCmfAccesstypeList(cmfAccesstype);
        return getDataTable(list);
    }

    /**
     * 导出帐户操作类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesstype:export')")
    @Log(title = "帐户操作类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfAccesstype cmfAccesstype)
    {
        List<CmfAccesstype> list = cmfAccesstypeService.selectCmfAccesstypeList(cmfAccesstype);
        ExcelUtil<CmfAccesstype> util = new ExcelUtil<CmfAccesstype>(CmfAccesstype.class);
        util.exportExcel(response, list, "帐户操作类型数据");
    }

    /**
     * 获取帐户操作类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesstype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfAccesstypeService.selectCmfAccesstypeById(id));
    }

    /**
     * 新增帐户操作类型
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesstype:add')")
    @Log(title = "帐户操作类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfAccesstype cmfAccesstype)
    {
        return toAjax(cmfAccesstypeService.insertCmfAccesstype(cmfAccesstype));
    }

    /**
     * 修改帐户操作类型
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesstype:edit')")
    @Log(title = "帐户操作类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfAccesstype cmfAccesstype)
    {
        return toAjax(cmfAccesstypeService.updateCmfAccesstype(cmfAccesstype));
    }

    /**
     * 删除帐户操作类型
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accesstype:remove')")
    @Log(title = "帐户操作类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfAccesstypeService.deleteCmfAccesstypeByIds(ids));
    }
}
