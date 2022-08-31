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
import com.vrmlstudio.mytable.domain.CmfCalltype;
import com.vrmlstudio.mytable.service.ICmfCalltypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 来电类型Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-mytable/calltype")
public class CmfCalltypeController extends BaseController
{
    @Autowired
    private ICmfCalltypeService cmfCalltypeService;

    /**
     * 查询来电类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:calltype:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCalltype cmfCalltype)
    {
        startPage();
        List<CmfCalltype> list = cmfCalltypeService.selectCmfCalltypeList(cmfCalltype);
        return getDataTable(list);
    }

    /**
     * 导出来电类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:calltype:export')")
    @Log(title = "来电类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCalltype cmfCalltype)
    {
        List<CmfCalltype> list = cmfCalltypeService.selectCmfCalltypeList(cmfCalltype);
        ExcelUtil<CmfCalltype> util = new ExcelUtil<CmfCalltype>(CmfCalltype.class);
        util.exportExcel(response, list, "来电类型数据");
    }

    /**
     * 获取来电类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:calltype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCalltypeService.selectCmfCalltypeById(id));
    }

    /**
     * 新增来电类型
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:calltype:add')")
    @Log(title = "来电类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCalltype cmfCalltype)
    {
        return toAjax(cmfCalltypeService.insertCmfCalltype(cmfCalltype));
    }

    /**
     * 修改来电类型
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:calltype:edit')")
    @Log(title = "来电类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCalltype cmfCalltype)
    {
        return toAjax(cmfCalltypeService.updateCmfCalltype(cmfCalltype));
    }

    /**
     * 删除来电类型
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:calltype:remove')")
    @Log(title = "来电类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCalltypeService.deleteCmfCalltypeByIds(ids));
    }
}
