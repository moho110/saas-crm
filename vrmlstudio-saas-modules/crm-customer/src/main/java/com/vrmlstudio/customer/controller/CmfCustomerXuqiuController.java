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
import com.vrmlstudio.customer.domain.CmfCustomerXuqiu;
import com.vrmlstudio.customer.service.ICmfCustomerXuqiuService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 客户需求Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-customer/xuqiu")
public class CmfCustomerXuqiuController extends BaseController
{
    @Autowired
    private ICmfCustomerXuqiuService cmfCustomerXuqiuService;

    /**
     * 查询客户需求列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:xuqiu:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCustomerXuqiu cmfCustomerXuqiu)
    {
        startPage();
        List<CmfCustomerXuqiu> list = cmfCustomerXuqiuService.selectCmfCustomerXuqiuList(cmfCustomerXuqiu);
        return getDataTable(list);
    }

    /**
     * 导出客户需求列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:xuqiu:export')")
    @Log(title = "客户需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCustomerXuqiu cmfCustomerXuqiu)
    {
        List<CmfCustomerXuqiu> list = cmfCustomerXuqiuService.selectCmfCustomerXuqiuList(cmfCustomerXuqiu);
        ExcelUtil<CmfCustomerXuqiu> util = new ExcelUtil<CmfCustomerXuqiu>(CmfCustomerXuqiu.class);
        util.exportExcel(response, list, "客户需求数据");
    }

    /**
     * 获取客户需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:xuqiu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCustomerXuqiuService.selectCmfCustomerXuqiuById(id));
    }

    /**
     * 新增客户需求
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:xuqiu:add')")
    @Log(title = "客户需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCustomerXuqiu cmfCustomerXuqiu)
    {
        return toAjax(cmfCustomerXuqiuService.insertCmfCustomerXuqiu(cmfCustomerXuqiu));
    }

    /**
     * 修改客户需求
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:xuqiu:edit')")
    @Log(title = "客户需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCustomerXuqiu cmfCustomerXuqiu)
    {
        return toAjax(cmfCustomerXuqiuService.updateCmfCustomerXuqiu(cmfCustomerXuqiu));
    }

    /**
     * 删除客户需求
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:xuqiu:remove')")
    @Log(title = "客户需求", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCustomerXuqiuService.deleteCmfCustomerXuqiuByIds(ids));
    }
}
