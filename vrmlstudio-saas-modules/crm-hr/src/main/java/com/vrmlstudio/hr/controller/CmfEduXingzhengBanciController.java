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
import com.vrmlstudio.hr.domain.CmfEduXingzhengBanci;
import com.vrmlstudio.hr.service.ICmfEduXingzhengBanciService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 行政班次Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/xingzhengbanci")
public class CmfEduXingzhengBanciController extends BaseController
{
    @Autowired
    private ICmfEduXingzhengBanciService cmfEduXingzhengBanciService;

    /**
     * 查询行政班次列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengbanci:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduXingzhengBanci cmfEduXingzhengBanci)
    {
        startPage();
        List<CmfEduXingzhengBanci> list = cmfEduXingzhengBanciService.selectCmfEduXingzhengBanciList(cmfEduXingzhengBanci);
        return getDataTable(list);
    }

    /**
     * 导出行政班次列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengbanci:export')")
    @Log(title = "行政班次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduXingzhengBanci cmfEduXingzhengBanci)
    {
        List<CmfEduXingzhengBanci> list = cmfEduXingzhengBanciService.selectCmfEduXingzhengBanciList(cmfEduXingzhengBanci);
        ExcelUtil<CmfEduXingzhengBanci> util = new ExcelUtil<CmfEduXingzhengBanci>(CmfEduXingzhengBanci.class);
        util.exportExcel(response, list, "行政班次数据");
    }

    /**
     * 获取行政班次详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengbanci:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduXingzhengBanciService.selectCmfEduXingzhengBanciById(id));
    }

    /**
     * 新增行政班次
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengbanci:add')")
    @Log(title = "行政班次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduXingzhengBanci cmfEduXingzhengBanci)
    {
        return toAjax(cmfEduXingzhengBanciService.insertCmfEduXingzhengBanci(cmfEduXingzhengBanci));
    }

    /**
     * 修改行政班次
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengbanci:edit')")
    @Log(title = "行政班次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduXingzhengBanci cmfEduXingzhengBanci)
    {
        return toAjax(cmfEduXingzhengBanciService.updateCmfEduXingzhengBanci(cmfEduXingzhengBanci));
    }

    /**
     * 删除行政班次
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengbanci:remove')")
    @Log(title = "行政班次", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduXingzhengBanciService.deleteCmfEduXingzhengBanciByIds(ids));
    }
}
