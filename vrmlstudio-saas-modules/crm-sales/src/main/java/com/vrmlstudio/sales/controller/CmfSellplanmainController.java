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
import com.vrmlstudio.sales.domain.CmfSellplanmain;
import com.vrmlstudio.sales.service.ICmfSellplanmainService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 销售计划Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-sales/sellplanmain")
public class CmfSellplanmainController extends BaseController
{
    @Autowired
    private ICmfSellplanmainService cmfSellplanmainService;

    /**
     * 查询销售计划列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellplanmain:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSellplanmain cmfSellplanmain)
    {
        startPage();
        List<CmfSellplanmain> list = cmfSellplanmainService.selectCmfSellplanmainList(cmfSellplanmain);
        return getDataTable(list);
    }

    /**
     * 导出销售计划列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellplanmain:export')")
    @Log(title = "销售计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSellplanmain cmfSellplanmain)
    {
        List<CmfSellplanmain> list = cmfSellplanmainService.selectCmfSellplanmainList(cmfSellplanmain);
        ExcelUtil<CmfSellplanmain> util = new ExcelUtil<CmfSellplanmain>(CmfSellplanmain.class);
        util.exportExcel(response, list, "销售计划数据");
    }

    /**
     * 获取销售计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellplanmain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSellplanmainService.selectCmfSellplanmainById(id));
    }

    /**
     * 新增销售计划
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellplanmain:add')")
    @Log(title = "销售计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSellplanmain cmfSellplanmain)
    {
        return toAjax(cmfSellplanmainService.insertCmfSellplanmain(cmfSellplanmain));
    }

    /**
     * 修改销售计划
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellplanmain:edit')")
    @Log(title = "销售计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSellplanmain cmfSellplanmain)
    {
        return toAjax(cmfSellplanmainService.updateCmfSellplanmain(cmfSellplanmain));
    }

    /**
     * 删除销售计划
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellplanmain:remove')")
    @Log(title = "销售计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSellplanmainService.deleteCmfSellplanmainByIds(ids));
    }
}
