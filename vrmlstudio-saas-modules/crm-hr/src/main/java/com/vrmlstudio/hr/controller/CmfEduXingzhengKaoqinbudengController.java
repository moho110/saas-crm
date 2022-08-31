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
import com.vrmlstudio.hr.domain.CmfEduXingzhengKaoqinbudeng;
import com.vrmlstudio.hr.service.ICmfEduXingzhengKaoqinbudengService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 行政考勤补登Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/kaoqinbudeng")
public class CmfEduXingzhengKaoqinbudengController extends BaseController
{
    @Autowired
    private ICmfEduXingzhengKaoqinbudengService cmfEduXingzhengKaoqinbudengService;

    /**
     * 查询行政考勤补登列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinbudeng:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduXingzhengKaoqinbudeng cmfEduXingzhengKaoqinbudeng)
    {
        startPage();
        List<CmfEduXingzhengKaoqinbudeng> list = cmfEduXingzhengKaoqinbudengService.selectCmfEduXingzhengKaoqinbudengList(cmfEduXingzhengKaoqinbudeng);
        return getDataTable(list);
    }

    /**
     * 导出行政考勤补登列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinbudeng:export')")
    @Log(title = "行政考勤补登", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduXingzhengKaoqinbudeng cmfEduXingzhengKaoqinbudeng)
    {
        List<CmfEduXingzhengKaoqinbudeng> list = cmfEduXingzhengKaoqinbudengService.selectCmfEduXingzhengKaoqinbudengList(cmfEduXingzhengKaoqinbudeng);
        ExcelUtil<CmfEduXingzhengKaoqinbudeng> util = new ExcelUtil<CmfEduXingzhengKaoqinbudeng>(CmfEduXingzhengKaoqinbudeng.class);
        util.exportExcel(response, list, "行政考勤补登数据");
    }

    /**
     * 获取行政考勤补登详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinbudeng:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduXingzhengKaoqinbudengService.selectCmfEduXingzhengKaoqinbudengById(id));
    }

    /**
     * 新增行政考勤补登
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinbudeng:add')")
    @Log(title = "行政考勤补登", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduXingzhengKaoqinbudeng cmfEduXingzhengKaoqinbudeng)
    {
        return toAjax(cmfEduXingzhengKaoqinbudengService.insertCmfEduXingzhengKaoqinbudeng(cmfEduXingzhengKaoqinbudeng));
    }

    /**
     * 修改行政考勤补登
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinbudeng:edit')")
    @Log(title = "行政考勤补登", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduXingzhengKaoqinbudeng cmfEduXingzhengKaoqinbudeng)
    {
        return toAjax(cmfEduXingzhengKaoqinbudengService.updateCmfEduXingzhengKaoqinbudeng(cmfEduXingzhengKaoqinbudeng));
    }

    /**
     * 删除行政考勤补登
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinbudeng:remove')")
    @Log(title = "行政考勤补登", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduXingzhengKaoqinbudengService.deleteCmfEduXingzhengKaoqinbudengByIds(ids));
    }
}
