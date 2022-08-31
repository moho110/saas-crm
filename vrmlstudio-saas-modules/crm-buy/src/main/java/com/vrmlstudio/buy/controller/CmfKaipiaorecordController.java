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
import com.vrmlstudio.buy.domain.CmfKaipiaorecord;
import com.vrmlstudio.buy.service.ICmfKaipiaorecordService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 开票记录Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-buy/kaipiaorecord")
public class CmfKaipiaorecordController extends BaseController
{
    @Autowired
    private ICmfKaipiaorecordService cmfKaipiaorecordService;

    /**
     * 查询开票记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:kaipiaorecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfKaipiaorecord cmfKaipiaorecord)
    {
        startPage();
        List<CmfKaipiaorecord> list = cmfKaipiaorecordService.selectCmfKaipiaorecordList(cmfKaipiaorecord);
        return getDataTable(list);
    }

    /**
     * 导出开票记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:kaipiaorecord:export')")
    @Log(title = "开票记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfKaipiaorecord cmfKaipiaorecord)
    {
        List<CmfKaipiaorecord> list = cmfKaipiaorecordService.selectCmfKaipiaorecordList(cmfKaipiaorecord);
        ExcelUtil<CmfKaipiaorecord> util = new ExcelUtil<CmfKaipiaorecord>(CmfKaipiaorecord.class);
        util.exportExcel(response, list, "开票记录数据");
    }

    /**
     * 获取开票记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:kaipiaorecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfKaipiaorecordService.selectCmfKaipiaorecordById(id));
    }

    /**
     * 新增开票记录
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:kaipiaorecord:add')")
    @Log(title = "开票记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfKaipiaorecord cmfKaipiaorecord)
    {
        return toAjax(cmfKaipiaorecordService.insertCmfKaipiaorecord(cmfKaipiaorecord));
    }

    /**
     * 修改开票记录
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:kaipiaorecord:edit')")
    @Log(title = "开票记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfKaipiaorecord cmfKaipiaorecord)
    {
        return toAjax(cmfKaipiaorecordService.updateCmfKaipiaorecord(cmfKaipiaorecord));
    }

    /**
     * 删除开票记录
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:kaipiaorecord:remove')")
    @Log(title = "开票记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfKaipiaorecordService.deleteCmfKaipiaorecordByIds(ids));
    }
}
