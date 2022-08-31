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
import com.vrmlstudio.sales.domain.CmfFahuotype;
import com.vrmlstudio.sales.service.ICmfFahuotypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 发货类型Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-sales/fahuotype")
public class CmfFahuotypeController extends BaseController
{
    @Autowired
    private ICmfFahuotypeService cmfFahuotypeService;

    /**
     * 查询发货类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:fahuotype:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfFahuotype cmfFahuotype)
    {
        startPage();
        List<CmfFahuotype> list = cmfFahuotypeService.selectCmfFahuotypeList(cmfFahuotype);
        return getDataTable(list);
    }

    /**
     * 导出发货类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:fahuotype:export')")
    @Log(title = "发货类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfFahuotype cmfFahuotype)
    {
        List<CmfFahuotype> list = cmfFahuotypeService.selectCmfFahuotypeList(cmfFahuotype);
        ExcelUtil<CmfFahuotype> util = new ExcelUtil<CmfFahuotype>(CmfFahuotype.class);
        util.exportExcel(response, list, "发货类型数据");
    }

    /**
     * 获取发货类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:fahuotype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfFahuotypeService.selectCmfFahuotypeById(id));
    }

    /**
     * 新增发货类型
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:fahuotype:add')")
    @Log(title = "发货类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfFahuotype cmfFahuotype)
    {
        return toAjax(cmfFahuotypeService.insertCmfFahuotype(cmfFahuotype));
    }

    /**
     * 修改发货类型
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:fahuotype:edit')")
    @Log(title = "发货类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfFahuotype cmfFahuotype)
    {
        return toAjax(cmfFahuotypeService.updateCmfFahuotype(cmfFahuotype));
    }

    /**
     * 删除发货类型
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:fahuotype:remove')")
    @Log(title = "发货类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfFahuotypeService.deleteCmfFahuotypeByIds(ids));
    }
}
