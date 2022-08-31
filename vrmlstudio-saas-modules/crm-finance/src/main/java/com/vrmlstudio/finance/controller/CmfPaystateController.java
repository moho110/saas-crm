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
import com.vrmlstudio.finance.domain.CmfPaystate;
import com.vrmlstudio.finance.service.ICmfPaystateService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 支付状态Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-finance/paystate")
public class CmfPaystateController extends BaseController
{
    @Autowired
    private ICmfPaystateService cmfPaystateService;

    /**
     * 查询支付状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:paystate:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfPaystate cmfPaystate)
    {
        startPage();
        List<CmfPaystate> list = cmfPaystateService.selectCmfPaystateList(cmfPaystate);
        return getDataTable(list);
    }

    /**
     * 导出支付状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:paystate:export')")
    @Log(title = "支付状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfPaystate cmfPaystate)
    {
        List<CmfPaystate> list = cmfPaystateService.selectCmfPaystateList(cmfPaystate);
        ExcelUtil<CmfPaystate> util = new ExcelUtil<CmfPaystate>(CmfPaystate.class);
        util.exportExcel(response, list, "支付状态数据");
    }

    /**
     * 获取支付状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:paystate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfPaystateService.selectCmfPaystateById(id));
    }

    /**
     * 新增支付状态
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:paystate:add')")
    @Log(title = "支付状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfPaystate cmfPaystate)
    {
        return toAjax(cmfPaystateService.insertCmfPaystate(cmfPaystate));
    }

    /**
     * 修改支付状态
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:paystate:edit')")
    @Log(title = "支付状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfPaystate cmfPaystate)
    {
        return toAjax(cmfPaystateService.updateCmfPaystate(cmfPaystate));
    }

    /**
     * 删除支付状态
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:paystate:remove')")
    @Log(title = "支付状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfPaystateService.deleteCmfPaystateByIds(ids));
    }
}
