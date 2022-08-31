package com.vrmlstudio.mytable.controller;

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
import com.vrmlstudio.mytable.domain.CmfCrmMytableNote;
import com.vrmlstudio.mytable.service.ICmfCrmMytableNoteService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 桌面便签表Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-mytable/mytablenotes")
public class CmfCrmMytableNoteController extends BaseController
{
    @Autowired
    private ICmfCrmMytableNoteService cmfCrmMytableNoteService;

    /**
     * 查询桌面便签表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytablenotes:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCrmMytableNote cmfCrmMytableNote)
    {
        startPage();
        List<CmfCrmMytableNote> list = cmfCrmMytableNoteService.selectCmfCrmMytableNoteList(cmfCrmMytableNote);
        return getDataTable(list);
    }

    /**
     * 导出桌面便签表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytablenotes:export')")
    @Log(title = "桌面便签表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCrmMytableNote cmfCrmMytableNote)
    {
        List<CmfCrmMytableNote> list = cmfCrmMytableNoteService.selectCmfCrmMytableNoteList(cmfCrmMytableNote);
        ExcelUtil<CmfCrmMytableNote> util = new ExcelUtil<CmfCrmMytableNote>(CmfCrmMytableNote.class);
        util.exportExcel(response, list, "桌面便签表数据");
    }

    /**
     * 获取桌面便签表详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytablenotes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCrmMytableNoteService.selectCmfCrmMytableNoteById(id));
    }

    /**
     * 新增桌面便签表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytablenotes:add')")
    @Log(title = "桌面便签表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCrmMytableNote cmfCrmMytableNote)
    {
        return toAjax(cmfCrmMytableNoteService.insertCmfCrmMytableNote(cmfCrmMytableNote));
    }

    /**
     * 修改桌面便签表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytablenotes:edit')")
    @Log(title = "桌面便签表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCrmMytableNote cmfCrmMytableNote)
    {
        return toAjax(cmfCrmMytableNoteService.updateCmfCrmMytableNote(cmfCrmMytableNote));
    }

    /**
     * 删除桌面便签表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:mytablenotes:remove')")
    @Log(title = "桌面便签表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCrmMytableNoteService.deleteCmfCrmMytableNoteByIds(ids));
    }
}
