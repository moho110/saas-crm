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
import com.vrmlstudio.sales.domain.CmfShoupiaorecord;
import com.vrmlstudio.sales.service.ICmfShoupiaorecordService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 收票记录Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-sales/shoupiaorecord")
public class CmfShoupiaorecordController extends BaseController
{
    @Autowired
    private ICmfShoupiaorecordService cmfShoupiaorecordService;

    /**
     * 查询收票记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:shoupiaorecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfShoupiaorecord cmfShoupiaorecord)
    {
        startPage();
        List<CmfShoupiaorecord> list = cmfShoupiaorecordService.selectCmfShoupiaorecordList(cmfShoupiaorecord);
        return getDataTable(list);
    }

    /**
     * 导出收票记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:shoupiaorecord:export')")
    @Log(title = "收票记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfShoupiaorecord cmfShoupiaorecord)
    {
        List<CmfShoupiaorecord> list = cmfShoupiaorecordService.selectCmfShoupiaorecordList(cmfShoupiaorecord);
        ExcelUtil<CmfShoupiaorecord> util = new ExcelUtil<CmfShoupiaorecord>(CmfShoupiaorecord.class);
        util.exportExcel(response, list, "收票记录数据");
    }

    /**
     * 获取收票记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:shoupiaorecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfShoupiaorecordService.selectCmfShoupiaorecordById(id));
    }

    /**
     * 新增收票记录
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:shoupiaorecord:add')")
    @Log(title = "收票记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfShoupiaorecord cmfShoupiaorecord)
    {
        return toAjax(cmfShoupiaorecordService.insertCmfShoupiaorecord(cmfShoupiaorecord));
    }

    /**
     * 修改收票记录
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:shoupiaorecord:edit')")
    @Log(title = "收票记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfShoupiaorecord cmfShoupiaorecord)
    {
        return toAjax(cmfShoupiaorecordService.updateCmfShoupiaorecord(cmfShoupiaorecord));
    }

    /**
     * 删除收票记录
     */
    @PreAuthorize("@ss.hasPermi('crm-sales:shoupiaorecord:remove')")
    @Log(title = "收票记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfShoupiaorecordService.deleteCmfShoupiaorecordByIds(ids));
    }
}
