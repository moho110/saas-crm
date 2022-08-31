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
import com.vrmlstudio.sales.domain.CmfSellbilltype;
import com.vrmlstudio.sales.service.ICmfSellbilltypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 销售清单类型Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-sales/sellbilltype")
public class CmfSellbilltypeController extends BaseController
{
    @Autowired
    private ICmfSellbilltypeService cmfSellbilltypeService;

    /**
     * 查询销售清单类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellbilltype:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSellbilltype cmfSellbilltype)
    {
        startPage();
        List<CmfSellbilltype> list = cmfSellbilltypeService.selectCmfSellbilltypeList(cmfSellbilltype);
        return getDataTable(list);
    }

    /**
     * 导出销售清单类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellbilltype:export')")
    @Log(title = "销售清单类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSellbilltype cmfSellbilltype)
    {
        List<CmfSellbilltype> list = cmfSellbilltypeService.selectCmfSellbilltypeList(cmfSellbilltype);
        ExcelUtil<CmfSellbilltype> util = new ExcelUtil<CmfSellbilltype>(CmfSellbilltype.class);
        util.exportExcel(response, list, "销售清单类型数据");
    }

    /**
     * 获取销售清单类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellbilltype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSellbilltypeService.selectCmfSellbilltypeById(id));
    }

    /**
     * 新增销售清单类型
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellbilltype:add')")
    @Log(title = "销售清单类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSellbilltype cmfSellbilltype)
    {
        return toAjax(cmfSellbilltypeService.insertCmfSellbilltype(cmfSellbilltype));
    }

    /**
     * 修改销售清单类型
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellbilltype:edit')")
    @Log(title = "销售清单类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSellbilltype cmfSellbilltype)
    {
        return toAjax(cmfSellbilltypeService.updateCmfSellbilltype(cmfSellbilltype));
    }

    /**
     * 删除销售清单类型
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:sellbilltype:remove')")
    @Log(title = "销售清单类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSellbilltypeService.deleteCmfSellbilltypeByIds(ids));
    }
}
