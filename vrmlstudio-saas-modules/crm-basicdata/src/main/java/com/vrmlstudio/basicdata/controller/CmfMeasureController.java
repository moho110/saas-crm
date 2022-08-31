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
import com.vrmlstudio.basicdata.domain.CmfMeasure;
import com.vrmlstudio.basicdata.service.ICmfMeasureService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 计量单位Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/measure")
public class CmfMeasureController extends BaseController
{
    @Autowired
    private ICmfMeasureService cmfMeasureService;

    /**
     * 查询计量单位列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:measure:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfMeasure cmfMeasure)
    {
        startPage();
        List<CmfMeasure> list = cmfMeasureService.selectCmfMeasureList(cmfMeasure);
        return getDataTable(list);
    }

    /**
     * 导出计量单位列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:measure:export')")
    @Log(title = "计量单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfMeasure cmfMeasure)
    {
        List<CmfMeasure> list = cmfMeasureService.selectCmfMeasureList(cmfMeasure);
        ExcelUtil<CmfMeasure> util = new ExcelUtil<CmfMeasure>(CmfMeasure.class);
        util.exportExcel(response, list, "计量单位数据");
    }

    /**
     * 获取计量单位详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:measure:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfMeasureService.selectCmfMeasureById(id));
    }

    /**
     * 新增计量单位
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:measure:add')")
    @Log(title = "计量单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfMeasure cmfMeasure)
    {
        return toAjax(cmfMeasureService.insertCmfMeasure(cmfMeasure));
    }

    /**
     * 修改计量单位
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:measure:edit')")
    @Log(title = "计量单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfMeasure cmfMeasure)
    {
        return toAjax(cmfMeasureService.updateCmfMeasure(cmfMeasure));
    }

    /**
     * 删除计量单位
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:measure:remove')")
    @Log(title = "计量单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfMeasureService.deleteCmfMeasureByIds(ids));
    }
}
