package com.vrmlstudio.sales.controller;

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
import com.vrmlstudio.sales.domain.CmfExchange;
import com.vrmlstudio.sales.service.ICmfExchangeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 积分兑换Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-sales/exchange")
public class CmfExchangeController extends BaseController
{
    @Autowired
    private ICmfExchangeService cmfExchangeService;

    /**
     * 查询积分兑换列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:exchange:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfExchange cmfExchange)
    {
        startPage();
        List<CmfExchange> list = cmfExchangeService.selectCmfExchangeList(cmfExchange);
        return getDataTable(list);
    }

    /**
     * 导出积分兑换列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:exchange:export')")
    @Log(title = "积分兑换", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfExchange cmfExchange)
    {
        List<CmfExchange> list = cmfExchangeService.selectCmfExchangeList(cmfExchange);
        ExcelUtil<CmfExchange> util = new ExcelUtil<CmfExchange>(CmfExchange.class);
        util.exportExcel(response, list, "积分兑换数据");
    }

    /**
     * 获取积分兑换详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:exchange:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfExchangeService.selectCmfExchangeById(id));
    }

    /**
     * 新增积分兑换
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:exchange:add')")
    @Log(title = "积分兑换", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfExchange cmfExchange)
    {
        return toAjax(cmfExchangeService.insertCmfExchange(cmfExchange));
    }

    /**
     * 修改积分兑换
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:exchange:edit')")
    @Log(title = "积分兑换", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfExchange cmfExchange)
    {
        return toAjax(cmfExchangeService.updateCmfExchange(cmfExchange));
    }

    /**
     * 删除积分兑换
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:exchange:remove')")
    @Log(title = "积分兑换", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfExchangeService.deleteCmfExchangeByIds(ids));
    }
}
