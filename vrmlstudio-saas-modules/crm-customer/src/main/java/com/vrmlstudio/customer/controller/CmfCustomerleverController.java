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
import com.vrmlstudio.customer.domain.CmfCustomerlever;
import com.vrmlstudio.customer.service.ICmfCustomerleverService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 客户等级Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-customer/customerlever")
public class CmfCustomerleverController extends BaseController
{
    @Autowired
    private ICmfCustomerleverService cmfCustomerleverService;

    /**
     * 查询客户等级列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerlever:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCustomerlever cmfCustomerlever)
    {
        startPage();
        List<CmfCustomerlever> list = cmfCustomerleverService.selectCmfCustomerleverList(cmfCustomerlever);
        return getDataTable(list);
    }

    /**
     * 导出客户等级列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerlever:export')")
    @Log(title = "客户等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCustomerlever cmfCustomerlever)
    {
        List<CmfCustomerlever> list = cmfCustomerleverService.selectCmfCustomerleverList(cmfCustomerlever);
        ExcelUtil<CmfCustomerlever> util = new ExcelUtil<CmfCustomerlever>(CmfCustomerlever.class);
        util.exportExcel(response, list, "客户等级数据");
    }

    /**
     * 获取客户等级详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerlever:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCustomerleverService.selectCmfCustomerleverById(id));
    }

    /**
     * 新增客户等级
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerlever:add')")
    @Log(title = "客户等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCustomerlever cmfCustomerlever)
    {
        return toAjax(cmfCustomerleverService.insertCmfCustomerlever(cmfCustomerlever));
    }

    /**
     * 修改客户等级
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerlever:edit')")
    @Log(title = "客户等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCustomerlever cmfCustomerlever)
    {
        return toAjax(cmfCustomerleverService.updateCmfCustomerlever(cmfCustomerlever));
    }

    /**
     * 删除客户等级
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customerlever:remove')")
    @Log(title = "客户等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCustomerleverService.deleteCmfCustomerleverByIds(ids));
    }
}
