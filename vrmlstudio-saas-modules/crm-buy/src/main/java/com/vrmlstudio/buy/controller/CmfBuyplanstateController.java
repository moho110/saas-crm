package com.vrmlstudio.buy.controller;

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
import com.vrmlstudio.buy.domain.CmfBuyplanstate;
import com.vrmlstudio.buy.service.ICmfBuyplanstateService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 采购计划状态Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-buy/buyplanstate")
public class CmfBuyplanstateController extends BaseController
{
    @Autowired
    private ICmfBuyplanstateService cmfBuyplanstateService;

    /**
     * 查询采购计划状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:buyplanstate:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfBuyplanstate cmfBuyplanstate)
    {
        startPage();
        List<CmfBuyplanstate> list = cmfBuyplanstateService.selectCmfBuyplanstateList(cmfBuyplanstate);
        return getDataTable(list);
    }

    /**
     * 导出采购计划状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:buyplanstate:export')")
    @Log(title = "采购计划状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfBuyplanstate cmfBuyplanstate)
    {
        List<CmfBuyplanstate> list = cmfBuyplanstateService.selectCmfBuyplanstateList(cmfBuyplanstate);
        ExcelUtil<CmfBuyplanstate> util = new ExcelUtil<CmfBuyplanstate>(CmfBuyplanstate.class);
        util.exportExcel(response, list, "采购计划状态数据");
    }

    /**
     * 获取采购计划状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:buyplanstate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfBuyplanstateService.selectCmfBuyplanstateById(id));
    }

    /**
     * 新增采购计划状态
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:buyplanstate:add')")
    @Log(title = "采购计划状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfBuyplanstate cmfBuyplanstate)
    {
        return toAjax(cmfBuyplanstateService.insertCmfBuyplanstate(cmfBuyplanstate));
    }

    /**
     * 修改采购计划状态
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:buyplanstate:edit')")
    @Log(title = "采购计划状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfBuyplanstate cmfBuyplanstate)
    {
        return toAjax(cmfBuyplanstateService.updateCmfBuyplanstate(cmfBuyplanstate));
    }

    /**
     * 删除采购计划状态
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:buyplanstate:remove')")
    @Log(title = "采购计划状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfBuyplanstateService.deleteCmfBuyplanstateByIds(ids));
    }
}
