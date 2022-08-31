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
import com.vrmlstudio.customer.domain.CmfContractFlag;
import com.vrmlstudio.customer.service.ICmfContractFlagService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 合同标识Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-customer/contractflag")
public class CmfContractFlagController extends BaseController
{
    @Autowired
    private ICmfContractFlagService cmfContractFlagService;

    /**
     * 查询合同标识列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contractflag:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfContractFlag cmfContractFlag)
    {
        startPage();
        List<CmfContractFlag> list = cmfContractFlagService.selectCmfContractFlagList(cmfContractFlag);
        return getDataTable(list);
    }

    /**
     * 导出合同标识列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contractflag:export')")
    @Log(title = "合同标识", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfContractFlag cmfContractFlag)
    {
        List<CmfContractFlag> list = cmfContractFlagService.selectCmfContractFlagList(cmfContractFlag);
        ExcelUtil<CmfContractFlag> util = new ExcelUtil<CmfContractFlag>(CmfContractFlag.class);
        util.exportExcel(response, list, "合同标识数据");
    }

    /**
     * 获取合同标识详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contractflag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfContractFlagService.selectCmfContractFlagById(id));
    }

    /**
     * 新增合同标识
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contractflag:add')")
    @Log(title = "合同标识", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfContractFlag cmfContractFlag)
    {
        return toAjax(cmfContractFlagService.insertCmfContractFlag(cmfContractFlag));
    }

    /**
     * 修改合同标识
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contractflag:edit')")
    @Log(title = "合同标识", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfContractFlag cmfContractFlag)
    {
        return toAjax(cmfContractFlagService.updateCmfContractFlag(cmfContractFlag));
    }

    /**
     * 删除合同标识
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:contractflag:remove')")
    @Log(title = "合同标识", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfContractFlagService.deleteCmfContractFlagByIds(ids));
    }
}
