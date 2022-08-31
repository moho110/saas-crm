package com.vrmlstudio.political.controller;

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
import com.vrmlstudio.political.domain.CmfOfficeproductleibie;
import com.vrmlstudio.political.service.ICmfOfficeproductleibieService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 办公用品类别Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-political/officeproductleibie")
public class CmfOfficeproductleibieController extends BaseController
{
    @Autowired
    private ICmfOfficeproductleibieService cmfOfficeproductleibieService;

    /**
     * 查询办公用品类别列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproductleibie:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfOfficeproductleibie cmfOfficeproductleibie)
    {
        startPage();
        List<CmfOfficeproductleibie> list = cmfOfficeproductleibieService.selectCmfOfficeproductleibieList(cmfOfficeproductleibie);
        return getDataTable(list);
    }

    /**
     * 导出办公用品类别列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproductleibie:export')")
    @Log(title = "办公用品类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfOfficeproductleibie cmfOfficeproductleibie)
    {
        List<CmfOfficeproductleibie> list = cmfOfficeproductleibieService.selectCmfOfficeproductleibieList(cmfOfficeproductleibie);
        ExcelUtil<CmfOfficeproductleibie> util = new ExcelUtil<CmfOfficeproductleibie>(CmfOfficeproductleibie.class);
        util.exportExcel(response, list, "办公用品类别数据");
    }

    /**
     * 获取办公用品类别详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproductleibie:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfOfficeproductleibieService.selectCmfOfficeproductleibieById(id));
    }

    /**
     * 新增办公用品类别
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproductleibie:add')")
    @Log(title = "办公用品类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfOfficeproductleibie cmfOfficeproductleibie)
    {
        return toAjax(cmfOfficeproductleibieService.insertCmfOfficeproductleibie(cmfOfficeproductleibie));
    }

    /**
     * 修改办公用品类别
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproductleibie:edit')")
    @Log(title = "办公用品类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfOfficeproductleibie cmfOfficeproductleibie)
    {
        return toAjax(cmfOfficeproductleibieService.updateCmfOfficeproductleibie(cmfOfficeproductleibie));
    }

    /**
     * 删除办公用品类别
     */
    @PreAuthorize("@ss.hasPermi('crm-political:officeproductleibie:remove')")
    @Log(title = "办公用品类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfOfficeproductleibieService.deleteCmfOfficeproductleibieByIds(ids));
    }
}
