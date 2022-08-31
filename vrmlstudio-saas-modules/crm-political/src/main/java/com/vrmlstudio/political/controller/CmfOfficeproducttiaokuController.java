package com.vrmlstudio.political.controller;

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
import com.vrmlstudio.political.domain.CmfOfficeproducttiaoku;
import com.vrmlstudio.political.service.ICmfOfficeproducttiaokuService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 办公用品调库Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-political/officeproducttiaoku")
public class CmfOfficeproducttiaokuController extends BaseController
{
    @Autowired
    private ICmfOfficeproducttiaokuService cmfOfficeproducttiaokuService;

    /**
     * 查询办公用品调库列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttiaoku:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfOfficeproducttiaoku cmfOfficeproducttiaoku)
    {
        startPage();
        List<CmfOfficeproducttiaoku> list = cmfOfficeproducttiaokuService.selectCmfOfficeproducttiaokuList(cmfOfficeproducttiaoku);
        return getDataTable(list);
    }

    /**
     * 导出办公用品调库列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttiaoku:export')")
    @Log(title = "办公用品调库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfOfficeproducttiaoku cmfOfficeproducttiaoku)
    {
        List<CmfOfficeproducttiaoku> list = cmfOfficeproducttiaokuService.selectCmfOfficeproducttiaokuList(cmfOfficeproducttiaoku);
        ExcelUtil<CmfOfficeproducttiaoku> util = new ExcelUtil<CmfOfficeproducttiaoku>(CmfOfficeproducttiaoku.class);
        util.exportExcel(response, list, "办公用品调库数据");
    }

    /**
     * 获取办公用品调库详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttiaoku:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfOfficeproducttiaokuService.selectCmfOfficeproducttiaokuById(id));
    }

    /**
     * 新增办公用品调库
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttiaoku:add')")
    @Log(title = "办公用品调库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfOfficeproducttiaoku cmfOfficeproducttiaoku)
    {
        return toAjax(cmfOfficeproducttiaokuService.insertCmfOfficeproducttiaoku(cmfOfficeproducttiaoku));
    }

    /**
     * 修改办公用品调库
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttiaoku:edit')")
    @Log(title = "办公用品调库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfOfficeproducttiaoku cmfOfficeproducttiaoku)
    {
        return toAjax(cmfOfficeproducttiaokuService.updateCmfOfficeproducttiaoku(cmfOfficeproducttiaoku));
    }

    /**
     * 删除办公用品调库
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproducttiaoku:remove')")
    @Log(title = "办公用品调库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfOfficeproducttiaokuService.deleteCmfOfficeproducttiaokuByIds(ids));
    }
}
