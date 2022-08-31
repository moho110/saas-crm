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
import com.vrmlstudio.basicdata.domain.CmfProducttype;
import com.vrmlstudio.basicdata.service.ICmfProducttypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 产品类型Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/producttype")
public class CmfProducttypeController extends BaseController
{
    @Autowired
    private ICmfProducttypeService cmfProducttypeService;

    /**
     * 查询产品类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:producttype:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfProducttype cmfProducttype)
    {
        startPage();
        List<CmfProducttype> list = cmfProducttypeService.selectCmfProducttypeList(cmfProducttype);
        return getDataTable(list);
    }

    /**
     * 导出产品类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:producttype:export')")
    @Log(title = "产品类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfProducttype cmfProducttype)
    {
        List<CmfProducttype> list = cmfProducttypeService.selectCmfProducttypeList(cmfProducttype);
        ExcelUtil<CmfProducttype> util = new ExcelUtil<CmfProducttype>(CmfProducttype.class);
        util.exportExcel(response, list, "产品类型数据");
    }

    /**
     * 获取产品类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:producttype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfProducttypeService.selectCmfProducttypeById(id));
    }

    /**
     * 新增产品类型
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:producttype:add')")
    @Log(title = "产品类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfProducttype cmfProducttype)
    {
        return toAjax(cmfProducttypeService.insertCmfProducttype(cmfProducttype));
    }

    /**
     * 修改产品类型
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:producttype:edit')")
    @Log(title = "产品类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfProducttype cmfProducttype)
    {
        return toAjax(cmfProducttypeService.updateCmfProducttype(cmfProducttype));
    }

    /**
     * 删除产品类型
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:producttype:remove')")
    @Log(title = "产品类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfProducttypeService.deleteCmfProducttypeByIds(ids));
    }
}
