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
import com.vrmlstudio.political.domain.CmfGbPolitical;
import com.vrmlstudio.political.service.ICmfGbPoliticalService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 政治面貌Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-political/political")
public class CmfGbPoliticalController extends BaseController
{
    @Autowired
    private ICmfGbPoliticalService cmfGbPoliticalService;

    /**
     * 查询政治面貌列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:political:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfGbPolitical cmfGbPolitical)
    {
        startPage();
        List<CmfGbPolitical> list = cmfGbPoliticalService.selectCmfGbPoliticalList(cmfGbPolitical);
        return getDataTable(list);
    }

    /**
     * 导出政治面貌列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:political:export')")
    @Log(title = "政治面貌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfGbPolitical cmfGbPolitical)
    {
        List<CmfGbPolitical> list = cmfGbPoliticalService.selectCmfGbPoliticalList(cmfGbPolitical);
        ExcelUtil<CmfGbPolitical> util = new ExcelUtil<CmfGbPolitical>(CmfGbPolitical.class);
        util.exportExcel(response, list, "政治面貌数据");
    }

    /**
     * 获取政治面貌详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:political:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfGbPoliticalService.selectCmfGbPoliticalById(id));
    }

    /**
     * 新增政治面貌
     */
    @PreAuthorize("@ss.hasPermi('crm-political:political:add')")
    @Log(title = "政治面貌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfGbPolitical cmfGbPolitical)
    {
        return toAjax(cmfGbPoliticalService.insertCmfGbPolitical(cmfGbPolitical));
    }

    /**
     * 修改政治面貌
     */
    @PreAuthorize("@ss.hasPermi('crm-political:political:edit')")
    @Log(title = "政治面貌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfGbPolitical cmfGbPolitical)
    {
        return toAjax(cmfGbPoliticalService.updateCmfGbPolitical(cmfGbPolitical));
    }

    /**
     * 删除政治面貌
     */
    @PreAuthorize("@ss.hasPermi('crm-political:political:remove')")
    @Log(title = "政治面貌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfGbPoliticalService.deleteCmfGbPoliticalByIds(ids));
    }
}
