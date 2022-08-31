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
import com.vrmlstudio.customer.domain.CmfCrmShenqingbaobei;
import com.vrmlstudio.customer.service.ICmfCrmShenqingbaobeiService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 申请报备Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-customer/shenqingbaobei")
public class CmfCrmShenqingbaobeiController extends BaseController
{
    @Autowired
    private ICmfCrmShenqingbaobeiService cmfCrmShenqingbaobeiService;

    /**
     * 查询申请报备列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenqingbaobei:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCrmShenqingbaobei cmfCrmShenqingbaobei)
    {
        startPage();
        List<CmfCrmShenqingbaobei> list = cmfCrmShenqingbaobeiService.selectCmfCrmShenqingbaobeiList(cmfCrmShenqingbaobei);
        return getDataTable(list);
    }

    /**
     * 导出申请报备列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenqingbaobei:export')")
    @Log(title = "申请报备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCrmShenqingbaobei cmfCrmShenqingbaobei)
    {
        List<CmfCrmShenqingbaobei> list = cmfCrmShenqingbaobeiService.selectCmfCrmShenqingbaobeiList(cmfCrmShenqingbaobei);
        ExcelUtil<CmfCrmShenqingbaobei> util = new ExcelUtil<CmfCrmShenqingbaobei>(CmfCrmShenqingbaobei.class);
        util.exportExcel(response, list, "申请报备数据");
    }

    /**
     * 获取申请报备详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenqingbaobei:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCrmShenqingbaobeiService.selectCmfCrmShenqingbaobeiById(id));
    }

    /**
     * 新增申请报备
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenqingbaobei:add')")
    @Log(title = "申请报备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCrmShenqingbaobei cmfCrmShenqingbaobei)
    {
        return toAjax(cmfCrmShenqingbaobeiService.insertCmfCrmShenqingbaobei(cmfCrmShenqingbaobei));
    }

    /**
     * 修改申请报备
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenqingbaobei:edit')")
    @Log(title = "申请报备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCrmShenqingbaobei cmfCrmShenqingbaobei)
    {
        return toAjax(cmfCrmShenqingbaobeiService.updateCmfCrmShenqingbaobei(cmfCrmShenqingbaobei));
    }

    /**
     * 删除申请报备
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenqingbaobei:remove')")
    @Log(title = "申请报备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCrmShenqingbaobeiService.deleteCmfCrmShenqingbaobeiByIds(ids));
    }
}
