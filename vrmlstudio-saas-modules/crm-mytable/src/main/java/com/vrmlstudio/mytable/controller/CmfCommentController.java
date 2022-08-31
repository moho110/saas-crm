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
import com.vrmlstudio.mytable.domain.CmfComment;
import com.vrmlstudio.mytable.service.ICmfCommentService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;

/**
 * 评论表Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-mytable/comment")
public class CmfCommentController extends BaseController
{
    @Autowired
    private ICmfCommentService cmfCommentService;

    /**
     * 查询评论表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:comment:list')")
    @GetMapping("/list")
    public AjaxResult list(CmfComment cmfComment)
    {
        List<CmfComment> list = cmfCommentService.selectCmfCommentList(cmfComment);
        return AjaxResult.success(list);
    }

    /**
     * 导出评论表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:comment:export')")
    @Log(title = "评论表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfComment cmfComment)
    {
        List<CmfComment> list = cmfCommentService.selectCmfCommentList(cmfComment);
        ExcelUtil<CmfComment> util = new ExcelUtil<CmfComment>(CmfComment.class);
        util.exportExcel(response, list, "评论表数据");
    }

    /**
     * 获取评论表详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCommentService.selectCmfCommentById(id));
    }

    /**
     * 新增评论表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:comment:add')")
    @Log(title = "评论表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfComment cmfComment)
    {
        return toAjax(cmfCommentService.insertCmfComment(cmfComment));
    }

    /**
     * 修改评论表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:comment:edit')")
    @Log(title = "评论表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfComment cmfComment)
    {
        return toAjax(cmfCommentService.updateCmfComment(cmfComment));
    }

    /**
     * 删除评论表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:comment:remove')")
    @Log(title = "评论表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCommentService.deleteCmfCommentByIds(ids));
    }
}
