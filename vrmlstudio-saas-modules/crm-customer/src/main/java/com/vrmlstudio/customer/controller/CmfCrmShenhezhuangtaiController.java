package com.vrmlstudio.customer.controller;

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
import com.vrmlstudio.customer.domain.CmfCrmShenhezhuangtai;
import com.vrmlstudio.customer.service.ICmfCrmShenhezhuangtaiService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 审核状态Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-customer/shenhezhuangtai")
public class CmfCrmShenhezhuangtaiController extends BaseController
{
    @Autowired
    private ICmfCrmShenhezhuangtaiService cmfCrmShenhezhuangtaiService;

    /**
     * 查询审核状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenhezhuangtai:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCrmShenhezhuangtai cmfCrmShenhezhuangtai)
    {
        startPage();
        List<CmfCrmShenhezhuangtai> list = cmfCrmShenhezhuangtaiService.selectCmfCrmShenhezhuangtaiList(cmfCrmShenhezhuangtai);
        return getDataTable(list);
    }

    /**
     * 导出审核状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenhezhuangtai:export')")
    @Log(title = "审核状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCrmShenhezhuangtai cmfCrmShenhezhuangtai)
    {
        List<CmfCrmShenhezhuangtai> list = cmfCrmShenhezhuangtaiService.selectCmfCrmShenhezhuangtaiList(cmfCrmShenhezhuangtai);
        ExcelUtil<CmfCrmShenhezhuangtai> util = new ExcelUtil<CmfCrmShenhezhuangtai>(CmfCrmShenhezhuangtai.class);
        util.exportExcel(response, list, "审核状态数据");
    }

    /**
     * 获取审核状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenhezhuangtai:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCrmShenhezhuangtaiService.selectCmfCrmShenhezhuangtaiById(id));
    }

    /**
     * 新增审核状态
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenhezhuangtai:add')")
    @Log(title = "审核状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCrmShenhezhuangtai cmfCrmShenhezhuangtai)
    {
        return toAjax(cmfCrmShenhezhuangtaiService.insertCmfCrmShenhezhuangtai(cmfCrmShenhezhuangtai));
    }

    /**
     * 修改审核状态
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenhezhuangtai:edit')")
    @Log(title = "审核状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCrmShenhezhuangtai cmfCrmShenhezhuangtai)
    {
        return toAjax(cmfCrmShenhezhuangtaiService.updateCmfCrmShenhezhuangtai(cmfCrmShenhezhuangtai));
    }

    /**
     * 删除审核状态
     */
    @PreAuthorize("@ss.hasPermi('crm-customer:shenhezhuangtai:remove')")
    @Log(title = "审核状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCrmShenhezhuangtaiService.deleteCmfCrmShenhezhuangtaiByIds(ids));
    }
}
