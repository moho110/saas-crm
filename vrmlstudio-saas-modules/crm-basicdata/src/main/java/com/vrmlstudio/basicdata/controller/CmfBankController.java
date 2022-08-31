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
import com.vrmlstudio.basicdata.domain.CmfBank;
import com.vrmlstudio.basicdata.service.ICmfBankService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 银行帐户Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-basicdata/bank")
public class CmfBankController extends BaseController
{
    @Autowired
    private ICmfBankService cmfBankService;

    /**
     * 查询银行帐户列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:bank:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfBank cmfBank)
    {
        startPage();
        List<CmfBank> list = cmfBankService.selectCmfBankList(cmfBank);
        return getDataTable(list);
    }

    /**
     * 导出银行帐户列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:bank:export')")
    @Log(title = "银行帐户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfBank cmfBank)
    {
        List<CmfBank> list = cmfBankService.selectCmfBankList(cmfBank);
        ExcelUtil<CmfBank> util = new ExcelUtil<CmfBank>(CmfBank.class);
        util.exportExcel(response, list, "银行帐户数据");
    }

    /**
     * 获取银行帐户详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:bank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfBankService.selectCmfBankById(id));
    }

    /**
     * 新增银行帐户
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:bank:add')")
    @Log(title = "银行帐户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfBank cmfBank)
    {
        return toAjax(cmfBankService.insertCmfBank(cmfBank));
    }

    /**
     * 修改银行帐户
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:bank:edit')")
    @Log(title = "银行帐户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfBank cmfBank)
    {
        return toAjax(cmfBankService.updateCmfBank(cmfBank));
    }

    /**
     * 删除银行帐户
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:bank:remove')")
    @Log(title = "银行帐户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfBankService.deleteCmfBankByIds(ids));
    }
}
