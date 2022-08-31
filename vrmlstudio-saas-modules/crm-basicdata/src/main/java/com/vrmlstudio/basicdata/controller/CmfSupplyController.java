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
import com.vrmlstudio.basicdata.domain.CmfSupply;
import com.vrmlstudio.basicdata.service.ICmfSupplyService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 供应商Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/supply")
public class CmfSupplyController extends BaseController
{
    @Autowired
    private ICmfSupplyService cmfSupplyService;

    /**
     * 查询供应商列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supply:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSupply cmfSupply)
    {
        startPage();
        List<CmfSupply> list = cmfSupplyService.selectCmfSupplyList(cmfSupply);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supply:export')")
    @Log(title = "供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSupply cmfSupply)
    {
        List<CmfSupply> list = cmfSupplyService.selectCmfSupplyList(cmfSupply);
        ExcelUtil<CmfSupply> util = new ExcelUtil<CmfSupply>(CmfSupply.class);
        util.exportExcel(response, list, "供应商数据");
    }

    /**
     * 获取供应商详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmfSupplyService.selectCmfSupplyById(id));
    }

    /**
     * 新增供应商
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supply:add')")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSupply cmfSupply)
    {
        return toAjax(cmfSupplyService.insertCmfSupply(cmfSupply));
    }

    /**
     * 修改供应商
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supply:edit')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSupply cmfSupply)
    {
        return toAjax(cmfSupplyService.updateCmfSupply(cmfSupply));
    }

    /**
     * 删除供应商
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supply:remove')")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmfSupplyService.deleteCmfSupplyByIds(ids));
    }
}
