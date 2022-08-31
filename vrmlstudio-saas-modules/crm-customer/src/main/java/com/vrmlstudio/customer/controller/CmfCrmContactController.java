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
import com.vrmlstudio.customer.domain.CmfCrmContact;
import com.vrmlstudio.customer.service.ICmfCrmContactService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 联系方式Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-customer/contact")
public class CmfCrmContactController extends BaseController
{
    @Autowired
    private ICmfCrmContactService cmfCrmContactService;

    /**
     * 查询联系方式列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contact:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCrmContact cmfCrmContact)
    {
        startPage();
        List<CmfCrmContact> list = cmfCrmContactService.selectCmfCrmContactList(cmfCrmContact);
        return getDataTable(list);
    }

    /**
     * 导出联系方式列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contact:export')")
    @Log(title = "联系方式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCrmContact cmfCrmContact)
    {
        List<CmfCrmContact> list = cmfCrmContactService.selectCmfCrmContactList(cmfCrmContact);
        ExcelUtil<CmfCrmContact> util = new ExcelUtil<CmfCrmContact>(CmfCrmContact.class);
        util.exportExcel(response, list, "联系方式数据");
    }

    /**
     * 获取联系方式详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contact:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCrmContactService.selectCmfCrmContactById(id));
    }

    /**
     * 新增联系方式
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contact:add')")
    @Log(title = "联系方式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCrmContact cmfCrmContact)
    {
        return toAjax(cmfCrmContactService.insertCmfCrmContact(cmfCrmContact));
    }

    /**
     * 修改联系方式
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contact:edit')")
    @Log(title = "联系方式", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCrmContact cmfCrmContact)
    {
        return toAjax(cmfCrmContactService.updateCmfCrmContact(cmfCrmContact));
    }

    /**
     * 删除联系方式
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contact:remove')")
    @Log(title = "联系方式", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCrmContactService.deleteCmfCrmContactByIds(ids));
    }
}
