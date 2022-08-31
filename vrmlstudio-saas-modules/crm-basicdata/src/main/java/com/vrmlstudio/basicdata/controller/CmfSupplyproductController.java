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
import com.vrmlstudio.basicdata.domain.CmfSupplyproduct;
import com.vrmlstudio.basicdata.service.ICmfSupplyproductService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 供应商产品Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/supplyproduct")
public class CmfSupplyproductController extends BaseController
{
    @Autowired
    private ICmfSupplyproductService cmfSupplyproductService;

    /**
     * 查询供应商产品列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplyproduct:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSupplyproduct cmfSupplyproduct)
    {
        startPage();
        List<CmfSupplyproduct> list = cmfSupplyproductService.selectCmfSupplyproductList(cmfSupplyproduct);
        return getDataTable(list);
    }

    /**
     * 导出供应商产品列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplyproduct:export')")
    @Log(title = "供应商产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSupplyproduct cmfSupplyproduct)
    {
        List<CmfSupplyproduct> list = cmfSupplyproductService.selectCmfSupplyproductList(cmfSupplyproduct);
        ExcelUtil<CmfSupplyproduct> util = new ExcelUtil<CmfSupplyproduct>(CmfSupplyproduct.class);
        util.exportExcel(response, list, "供应商产品数据");
    }

    /**
     * 获取供应商产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplyproduct:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSupplyproductService.selectCmfSupplyproductById(id));
    }

    /**
     * 新增供应商产品
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplyproduct:add')")
    @Log(title = "供应商产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSupplyproduct cmfSupplyproduct)
    {
        return toAjax(cmfSupplyproductService.insertCmfSupplyproduct(cmfSupplyproduct));
    }

    /**
     * 修改供应商产品
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplyproduct:edit')")
    @Log(title = "供应商产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSupplyproduct cmfSupplyproduct)
    {
        return toAjax(cmfSupplyproductService.updateCmfSupplyproduct(cmfSupplyproduct));
    }

    /**
     * 删除供应商产品
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplyproduct:remove')")
    @Log(title = "供应商产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSupplyproductService.deleteCmfSupplyproductByIds(ids));
    }
}
