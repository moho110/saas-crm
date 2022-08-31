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
import com.vrmlstudio.buy.domain.CmfBuyplanmainMingxi;
import com.vrmlstudio.buy.service.ICmfBuyplanmainMingxiService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 采购单名细Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-buy/mingxi")
public class CmfBuyplanmainMingxiController extends BaseController
{
    @Autowired
    private ICmfBuyplanmainMingxiService cmfBuyplanmainMingxiService;

    /**
     * 查询采购单名细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:mingxi:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfBuyplanmainMingxi cmfBuyplanmainMingxi)
    {
        startPage();
        List<CmfBuyplanmainMingxi> list = cmfBuyplanmainMingxiService.selectCmfBuyplanmainMingxiList(cmfBuyplanmainMingxi);
        return getDataTable(list);
    }

    /**
     * 导出采购单名细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:mingxi:export')")
    @Log(title = "采购单名细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfBuyplanmainMingxi cmfBuyplanmainMingxi)
    {
        List<CmfBuyplanmainMingxi> list = cmfBuyplanmainMingxiService.selectCmfBuyplanmainMingxiList(cmfBuyplanmainMingxi);
        ExcelUtil<CmfBuyplanmainMingxi> util = new ExcelUtil<CmfBuyplanmainMingxi>(CmfBuyplanmainMingxi.class);
        util.exportExcel(response, list, "采购单名细数据");
    }

    /**
     * 获取采购单名细详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:mingxi:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfBuyplanmainMingxiService.selectCmfBuyplanmainMingxiById(id));
    }

    /**
     * 新增采购单名细
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:mingxi:add')")
    @Log(title = "采购单名细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfBuyplanmainMingxi cmfBuyplanmainMingxi)
    {
        return toAjax(cmfBuyplanmainMingxiService.insertCmfBuyplanmainMingxi(cmfBuyplanmainMingxi));
    }

    /**
     * 修改采购单名细
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:mingxi:edit')")
    @Log(title = "采购单名细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfBuyplanmainMingxi cmfBuyplanmainMingxi)
    {
        return toAjax(cmfBuyplanmainMingxiService.updateCmfBuyplanmainMingxi(cmfBuyplanmainMingxi));
    }

    /**
     * 删除采购单名细
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:mingxi:remove')")
    @Log(title = "采购单名细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfBuyplanmainMingxiService.deleteCmfBuyplanmainMingxiByIds(ids));
    }
}
