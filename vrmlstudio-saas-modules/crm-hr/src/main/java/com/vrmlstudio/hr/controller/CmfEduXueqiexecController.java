package com.vrmlstudio.hr.controller;

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
import com.vrmlstudio.hr.domain.CmfEduXueqiexec;
import com.vrmlstudio.hr.service.ICmfEduXueqiexecService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 学期执行Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/xueqiexec")
public class CmfEduXueqiexecController extends BaseController
{
    @Autowired
    private ICmfEduXueqiexecService cmfEduXueqiexecService;

    /**
     * 查询学期执行列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xueqiexec:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduXueqiexec cmfEduXueqiexec)
    {
        startPage();
        List<CmfEduXueqiexec> list = cmfEduXueqiexecService.selectCmfEduXueqiexecList(cmfEduXueqiexec);
        return getDataTable(list);
    }

    /**
     * 导出学期执行列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xueqiexec:export')")
    @Log(title = "学期执行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduXueqiexec cmfEduXueqiexec)
    {
        List<CmfEduXueqiexec> list = cmfEduXueqiexecService.selectCmfEduXueqiexecList(cmfEduXueqiexec);
        ExcelUtil<CmfEduXueqiexec> util = new ExcelUtil<CmfEduXueqiexec>(CmfEduXueqiexec.class);
        util.exportExcel(response, list, "学期执行数据");
    }

    /**
     * 获取学期执行详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xueqiexec:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduXueqiexecService.selectCmfEduXueqiexecById(id));
    }

    /**
     * 新增学期执行
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xueqiexec:add')")
    @Log(title = "学期执行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduXueqiexec cmfEduXueqiexec)
    {
        return toAjax(cmfEduXueqiexecService.insertCmfEduXueqiexec(cmfEduXueqiexec));
    }

    /**
     * 修改学期执行
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xueqiexec:edit')")
    @Log(title = "学期执行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduXueqiexec cmfEduXueqiexec)
    {
        return toAjax(cmfEduXueqiexecService.updateCmfEduXueqiexec(cmfEduXueqiexec));
    }

    /**
     * 删除学期执行
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xueqiexec:remove')")
    @Log(title = "学期执行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduXueqiexecService.deleteCmfEduXueqiexecByIds(ids));
    }
}
