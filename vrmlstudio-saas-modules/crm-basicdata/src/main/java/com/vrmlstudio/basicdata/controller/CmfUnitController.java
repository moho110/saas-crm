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
import com.vrmlstudio.basicdata.domain.CmfUnit;
import com.vrmlstudio.basicdata.service.ICmfUnitService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 单位Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/unit")
public class CmfUnitController extends BaseController
{
    @Autowired
    private ICmfUnitService cmfUnitService;

    /**
     * 查询单位列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:unit:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfUnit cmfUnit)
    {
        startPage();
        List<CmfUnit> list = cmfUnitService.selectCmfUnitList(cmfUnit);
        return getDataTable(list);
    }

    /**
     * 导出单位列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:unit:export')")
    @Log(title = "单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfUnit cmfUnit)
    {
        List<CmfUnit> list = cmfUnitService.selectCmfUnitList(cmfUnit);
        ExcelUtil<CmfUnit> util = new ExcelUtil<CmfUnit>(CmfUnit.class);
        util.exportExcel(response, list, "单位数据");
    }

    /**
     * 获取单位详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:unit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfUnitService.selectCmfUnitById(id));
    }

    /**
     * 新增单位
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:unit:add')")
    @Log(title = "单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfUnit cmfUnit)
    {
        return toAjax(cmfUnitService.insertCmfUnit(cmfUnit));
    }

    /**
     * 修改单位
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:unit:edit')")
    @Log(title = "单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfUnit cmfUnit)
    {
        return toAjax(cmfUnitService.updateCmfUnit(cmfUnit));
    }

    /**
     * 删除单位
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:unit:remove')")
    @Log(title = "单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfUnitService.deleteCmfUnitByIds(ids));
    }
}
