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
import com.vrmlstudio.basicdata.domain.CmfProperty;
import com.vrmlstudio.basicdata.service.ICmfPropertyService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 行业归属Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/property")
public class CmfPropertyController extends BaseController
{
    @Autowired
    private ICmfPropertyService cmfPropertyService;

    /**
     * 查询行业归属列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:property:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfProperty cmfProperty)
    {
        startPage();
        List<CmfProperty> list = cmfPropertyService.selectCmfPropertyList(cmfProperty);
        return getDataTable(list);
    }

    /**
     * 导出行业归属列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:property:export')")
    @Log(title = "行业归属", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfProperty cmfProperty)
    {
        List<CmfProperty> list = cmfPropertyService.selectCmfPropertyList(cmfProperty);
        ExcelUtil<CmfProperty> util = new ExcelUtil<CmfProperty>(CmfProperty.class);
        util.exportExcel(response, list, "行业归属数据");
    }

    /**
     * 获取行业归属详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:property:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfPropertyService.selectCmfPropertyById(id));
    }

    /**
     * 新增行业归属
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:property:add')")
    @Log(title = "行业归属", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfProperty cmfProperty)
    {
        return toAjax(cmfPropertyService.insertCmfProperty(cmfProperty));
    }

    /**
     * 修改行业归属
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:property:edit')")
    @Log(title = "行业归属", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfProperty cmfProperty)
    {
        return toAjax(cmfPropertyService.updateCmfProperty(cmfProperty));
    }

    /**
     * 删除行业归属
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:property:remove')")
    @Log(title = "行业归属", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfPropertyService.deleteCmfPropertyByIds(ids));
    }
}
