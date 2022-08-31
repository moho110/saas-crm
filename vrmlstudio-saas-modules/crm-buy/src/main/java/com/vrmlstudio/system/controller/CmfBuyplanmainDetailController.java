package com.vrmlstudio.system.controller;

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
import com.vrmlstudio.system.domain.CmfBuyplanmainDetail;
import com.vrmlstudio.system.service.ICmfBuyplanmainDetailService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 采购单名细Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-buy/detail")
public class CmfBuyplanmainDetailController extends BaseController
{
    @Autowired
    private ICmfBuyplanmainDetailService cmfBuyplanmainDetailService;

    /**
     * 查询采购单名细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfBuyplanmainDetail cmfBuyplanmainDetail)
    {
        startPage();
        List<CmfBuyplanmainDetail> list = cmfBuyplanmainDetailService.selectCmfBuyplanmainDetailList(cmfBuyplanmainDetail);
        return getDataTable(list);
    }

    /**
     * 导出采购单名细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:detail:export')")
    @Log(title = "采购单名细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfBuyplanmainDetail cmfBuyplanmainDetail)
    {
        List<CmfBuyplanmainDetail> list = cmfBuyplanmainDetailService.selectCmfBuyplanmainDetailList(cmfBuyplanmainDetail);
        ExcelUtil<CmfBuyplanmainDetail> util = new ExcelUtil<CmfBuyplanmainDetail>(CmfBuyplanmainDetail.class);
        util.exportExcel(response, list, "采购单名细数据");
    }

    /**
     * 获取采购单名细详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfBuyplanmainDetailService.selectCmfBuyplanmainDetailById(id));
    }

    /**
     * 新增采购单名细
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:detail:add')")
    @Log(title = "采购单名细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfBuyplanmainDetail cmfBuyplanmainDetail)
    {
        return toAjax(cmfBuyplanmainDetailService.insertCmfBuyplanmainDetail(cmfBuyplanmainDetail));
    }

    /**
     * 修改采购单名细
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:detail:edit')")
    @Log(title = "采购单名细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfBuyplanmainDetail cmfBuyplanmainDetail)
    {
        return toAjax(cmfBuyplanmainDetailService.updateCmfBuyplanmainDetail(cmfBuyplanmainDetail));
    }

    /**
     * 删除采购单名细
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:detail:remove')")
    @Log(title = "采购单名细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfBuyplanmainDetailService.deleteCmfBuyplanmainDetailByIds(ids));
    }
}
