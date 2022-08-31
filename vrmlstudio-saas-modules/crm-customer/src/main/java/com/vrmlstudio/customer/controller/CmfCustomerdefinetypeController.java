package com.vrmlstudio.customer.controller;

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
import com.vrmlstudio.customer.domain.CmfCustomerdefinetype;
import com.vrmlstudio.customer.service.ICmfCustomerdefinetypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 客户定义类型Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-customer/customerdefinetype")
public class CmfCustomerdefinetypeController extends BaseController
{
    @Autowired
    private ICmfCustomerdefinetypeService cmfCustomerdefinetypeService;

    /**
     * 查询客户定义类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerdefinetype:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCustomerdefinetype cmfCustomerdefinetype)
    {
        startPage();
        List<CmfCustomerdefinetype> list = cmfCustomerdefinetypeService.selectCmfCustomerdefinetypeList(cmfCustomerdefinetype);
        return getDataTable(list);
    }

    /**
     * 导出客户定义类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerdefinetype:export')")
    @Log(title = "客户定义类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCustomerdefinetype cmfCustomerdefinetype)
    {
        List<CmfCustomerdefinetype> list = cmfCustomerdefinetypeService.selectCmfCustomerdefinetypeList(cmfCustomerdefinetype);
        ExcelUtil<CmfCustomerdefinetype> util = new ExcelUtil<CmfCustomerdefinetype>(CmfCustomerdefinetype.class);
        util.exportExcel(response, list, "客户定义类型数据");
    }

    /**
     * 获取客户定义类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerdefinetype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCustomerdefinetypeService.selectCmfCustomerdefinetypeById(id));
    }

    /**
     * 新增客户定义类型
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerdefinetype:add')")
    @Log(title = "客户定义类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCustomerdefinetype cmfCustomerdefinetype)
    {
        return toAjax(cmfCustomerdefinetypeService.insertCmfCustomerdefinetype(cmfCustomerdefinetype));
    }

    /**
     * 修改客户定义类型
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerdefinetype:edit')")
    @Log(title = "客户定义类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCustomerdefinetype cmfCustomerdefinetype)
    {
        return toAjax(cmfCustomerdefinetypeService.updateCmfCustomerdefinetype(cmfCustomerdefinetype));
    }

    /**
     * 删除客户定义类型
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerdefinetype:remove')")
    @Log(title = "客户定义类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCustomerdefinetypeService.deleteCmfCustomerdefinetypeByIds(ids));
    }
}
