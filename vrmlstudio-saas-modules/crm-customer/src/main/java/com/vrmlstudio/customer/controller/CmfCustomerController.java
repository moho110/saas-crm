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
import com.vrmlstudio.customer.domain.CmfCustomer;
import com.vrmlstudio.customer.service.ICmfCustomerService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 客户Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-customer/customer")
public class CmfCustomerController extends BaseController
{
    @Autowired
    private ICmfCustomerService cmfCustomerService;

    /**
     * 查询客户列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCustomer cmfCustomer)
    {
        startPage();
        List<CmfCustomer> list = cmfCustomerService.selectCmfCustomerList(cmfCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customer:export')")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCustomer cmfCustomer)
    {
        List<CmfCustomer> list = cmfCustomerService.selectCmfCustomerList(cmfCustomer);
        ExcelUtil<CmfCustomer> util = new ExcelUtil<CmfCustomer>(CmfCustomer.class);
        util.exportExcel(response, list, "客户数据");
    }

    /**
     * 获取客户详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCustomerService.selectCmfCustomerById(id));
    }

    /**
     * 新增客户
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customer:add')")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCustomer cmfCustomer)
    {
        return toAjax(cmfCustomerService.insertCmfCustomer(cmfCustomer));
    }

    /**
     * 修改客户
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customer:edit')")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCustomer cmfCustomer)
    {
        return toAjax(cmfCustomerService.updateCmfCustomer(cmfCustomer));
    }

    /**
     * 删除客户
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:customer:remove')")
    @Log(title = "客户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCustomerService.deleteCmfCustomerByIds(ids));
    }
}
