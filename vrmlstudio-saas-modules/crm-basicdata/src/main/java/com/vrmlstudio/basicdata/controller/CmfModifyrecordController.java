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
import com.vrmlstudio.basicdata.domain.CmfModifyrecord;
import com.vrmlstudio.basicdata.service.ICmfModifyrecordService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 修改记录Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/modifyrecord")
public class CmfModifyrecordController extends BaseController
{
    @Autowired
    private ICmfModifyrecordService cmfModifyrecordService;

    /**
     * 查询修改记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:modifyrecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfModifyrecord cmfModifyrecord)
    {
        startPage();
        List<CmfModifyrecord> list = cmfModifyrecordService.selectCmfModifyrecordList(cmfModifyrecord);
        return getDataTable(list);
    }

    /**
     * 导出修改记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:modifyrecord:export')")
    @Log(title = "修改记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfModifyrecord cmfModifyrecord)
    {
        List<CmfModifyrecord> list = cmfModifyrecordService.selectCmfModifyrecordList(cmfModifyrecord);
        ExcelUtil<CmfModifyrecord> util = new ExcelUtil<CmfModifyrecord>(CmfModifyrecord.class);
        util.exportExcel(response, list, "修改记录数据");
    }

    /**
     * 获取修改记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:modifyrecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfModifyrecordService.selectCmfModifyrecordById(id));
    }

    /**
     * 新增修改记录
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:modifyrecord:add')")
    @Log(title = "修改记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfModifyrecord cmfModifyrecord)
    {
        return toAjax(cmfModifyrecordService.insertCmfModifyrecord(cmfModifyrecord));
    }

    /**
     * 修改修改记录
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:modifyrecord:edit')")
    @Log(title = "修改记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfModifyrecord cmfModifyrecord)
    {
        return toAjax(cmfModifyrecordService.updateCmfModifyrecord(cmfModifyrecord));
    }

    /**
     * 删除修改记录
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:modifyrecord:remove')")
    @Log(title = "修改记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfModifyrecordService.deleteCmfModifyrecordByIds(ids));
    }
}
