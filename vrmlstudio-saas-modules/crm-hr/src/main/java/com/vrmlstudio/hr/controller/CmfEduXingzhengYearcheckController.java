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
import com.vrmlstudio.hr.domain.CmfEduXingzhengYearcheck;
import com.vrmlstudio.hr.service.ICmfEduXingzhengYearcheckService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 行政人员年度考核量化表Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/yearcheck")
public class CmfEduXingzhengYearcheckController extends BaseController
{
    @Autowired
    private ICmfEduXingzhengYearcheckService cmfEduXingzhengYearcheckService;

    /**
     * 查询行政人员年度考核量化表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:yearcheck:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduXingzhengYearcheck cmfEduXingzhengYearcheck)
    {
        startPage();
        List<CmfEduXingzhengYearcheck> list = cmfEduXingzhengYearcheckService.selectCmfEduXingzhengYearcheckList(cmfEduXingzhengYearcheck);
        return getDataTable(list);
    }

    /**
     * 导出行政人员年度考核量化表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:yearcheck:export')")
    @Log(title = "行政人员年度考核量化表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduXingzhengYearcheck cmfEduXingzhengYearcheck)
    {
        List<CmfEduXingzhengYearcheck> list = cmfEduXingzhengYearcheckService.selectCmfEduXingzhengYearcheckList(cmfEduXingzhengYearcheck);
        ExcelUtil<CmfEduXingzhengYearcheck> util = new ExcelUtil<CmfEduXingzhengYearcheck>(CmfEduXingzhengYearcheck.class);
        util.exportExcel(response, list, "行政人员年度考核量化表数据");
    }

    /**
     * 获取行政人员年度考核量化表详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:yearcheck:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduXingzhengYearcheckService.selectCmfEduXingzhengYearcheckById(id));
    }

    /**
     * 新增行政人员年度考核量化表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:yearcheck:add')")
    @Log(title = "行政人员年度考核量化表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduXingzhengYearcheck cmfEduXingzhengYearcheck)
    {
        return toAjax(cmfEduXingzhengYearcheckService.insertCmfEduXingzhengYearcheck(cmfEduXingzhengYearcheck));
    }

    /**
     * 修改行政人员年度考核量化表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:yearcheck:edit')")
    @Log(title = "行政人员年度考核量化表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduXingzhengYearcheck cmfEduXingzhengYearcheck)
    {
        return toAjax(cmfEduXingzhengYearcheckService.updateCmfEduXingzhengYearcheck(cmfEduXingzhengYearcheck));
    }

    /**
     * 删除行政人员年度考核量化表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:yearcheck:remove')")
    @Log(title = "行政人员年度考核量化表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduXingzhengYearcheckService.deleteCmfEduXingzhengYearcheckByIds(ids));
    }
}
