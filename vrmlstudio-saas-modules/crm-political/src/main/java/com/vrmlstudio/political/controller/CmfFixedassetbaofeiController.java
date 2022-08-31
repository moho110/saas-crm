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
import com.vrmlstudio.political.domain.CmfFixedassetbaofei;
import com.vrmlstudio.political.service.ICmfFixedassetbaofeiService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 固定资产报废Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-political/fixedassetbaofei")
public class CmfFixedassetbaofeiController extends BaseController
{
    @Autowired
    private ICmfFixedassetbaofeiService cmfFixedassetbaofeiService;

    /**
     * 查询固定资产报废列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetbaofei:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfFixedassetbaofei cmfFixedassetbaofei)
    {
        startPage();
        List<CmfFixedassetbaofei> list = cmfFixedassetbaofeiService.selectCmfFixedassetbaofeiList(cmfFixedassetbaofei);
        return getDataTable(list);
    }

    /**
     * 导出固定资产报废列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetbaofei:export')")
    @Log(title = "固定资产报废", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfFixedassetbaofei cmfFixedassetbaofei)
    {
        List<CmfFixedassetbaofei> list = cmfFixedassetbaofeiService.selectCmfFixedassetbaofeiList(cmfFixedassetbaofei);
        ExcelUtil<CmfFixedassetbaofei> util = new ExcelUtil<CmfFixedassetbaofei>(CmfFixedassetbaofei.class);
        util.exportExcel(response, list, "固定资产报废数据");
    }

    /**
     * 获取固定资产报废详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetbaofei:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfFixedassetbaofeiService.selectCmfFixedassetbaofeiById(id));
    }

    /**
     * 新增固定资产报废
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetbaofei:add')")
    @Log(title = "固定资产报废", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfFixedassetbaofei cmfFixedassetbaofei)
    {
        return toAjax(cmfFixedassetbaofeiService.insertCmfFixedassetbaofei(cmfFixedassetbaofei));
    }

    /**
     * 修改固定资产报废
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetbaofei:edit')")
    @Log(title = "固定资产报废", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfFixedassetbaofei cmfFixedassetbaofei)
    {
        return toAjax(cmfFixedassetbaofeiService.updateCmfFixedassetbaofei(cmfFixedassetbaofei));
    }

    /**
     * 删除固定资产报废
     */
    @PreAuthorize("@ss.hasPermi('crm-political:fixedassetbaofei:remove')")
    @Log(title = "固定资产报废", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfFixedassetbaofeiService.deleteCmfFixedassetbaofeiByIds(ids));
    }
}
