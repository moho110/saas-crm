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
import com.vrmlstudio.hr.domain.CmfHrmsSocialrelation;
import com.vrmlstudio.hr.service.ICmfHrmsSocialrelationService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 社会关系Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-hr/socialrelation")
public class CmfHrmsSocialrelationController extends BaseController
{
    @Autowired
    private ICmfHrmsSocialrelationService cmfHrmsSocialrelationService;

    /**
     * 查询社会关系列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:socialrelation:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsSocialrelation cmfHrmsSocialrelation)
    {
        startPage();
        List<CmfHrmsSocialrelation> list = cmfHrmsSocialrelationService.selectCmfHrmsSocialrelationList(cmfHrmsSocialrelation);
        return getDataTable(list);
    }

    /**
     * 导出社会关系列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:socialrelation:export')")
    @Log(title = "社会关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsSocialrelation cmfHrmsSocialrelation)
    {
        List<CmfHrmsSocialrelation> list = cmfHrmsSocialrelationService.selectCmfHrmsSocialrelationList(cmfHrmsSocialrelation);
        ExcelUtil<CmfHrmsSocialrelation> util = new ExcelUtil<CmfHrmsSocialrelation>(CmfHrmsSocialrelation.class);
        util.exportExcel(response, list, "社会关系数据");
    }

    /**
     * 获取社会关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:socialrelation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsSocialrelationService.selectCmfHrmsSocialrelationById(id));
    }

    /**
     * 新增社会关系
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:socialrelation:add')")
    @Log(title = "社会关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsSocialrelation cmfHrmsSocialrelation)
    {
        return toAjax(cmfHrmsSocialrelationService.insertCmfHrmsSocialrelation(cmfHrmsSocialrelation));
    }

    /**
     * 修改社会关系
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:socialrelation:edit')")
    @Log(title = "社会关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsSocialrelation cmfHrmsSocialrelation)
    {
        return toAjax(cmfHrmsSocialrelationService.updateCmfHrmsSocialrelation(cmfHrmsSocialrelation));
    }

    /**
     * 删除社会关系
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:socialrelation:remove')")
    @Log(title = "社会关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsSocialrelationService.deleteCmfHrmsSocialrelationByIds(ids));
    }
}
