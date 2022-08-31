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
import com.vrmlstudio.hr.domain.CmfHrmsZpjihua;
import com.vrmlstudio.hr.service.ICmfHrmsZpjihuaService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 招聘计划Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-hr/zpjihua")
public class CmfHrmsZpjihuaController extends BaseController
{
    @Autowired
    private ICmfHrmsZpjihuaService cmfHrmsZpjihuaService;

    /**
     * 查询招聘计划列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zpjihua:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsZpjihua cmfHrmsZpjihua)
    {
        startPage();
        List<CmfHrmsZpjihua> list = cmfHrmsZpjihuaService.selectCmfHrmsZpjihuaList(cmfHrmsZpjihua);
        return getDataTable(list);
    }

    /**
     * 导出招聘计划列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zpjihua:export')")
    @Log(title = "招聘计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsZpjihua cmfHrmsZpjihua)
    {
        List<CmfHrmsZpjihua> list = cmfHrmsZpjihuaService.selectCmfHrmsZpjihuaList(cmfHrmsZpjihua);
        ExcelUtil<CmfHrmsZpjihua> util = new ExcelUtil<CmfHrmsZpjihua>(CmfHrmsZpjihua.class);
        util.exportExcel(response, list, "招聘计划数据");
    }

    /**
     * 获取招聘计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zpjihua:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsZpjihuaService.selectCmfHrmsZpjihuaById(id));
    }

    /**
     * 新增招聘计划
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zpjihua:add')")
    @Log(title = "招聘计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsZpjihua cmfHrmsZpjihua)
    {
        return toAjax(cmfHrmsZpjihuaService.insertCmfHrmsZpjihua(cmfHrmsZpjihua));
    }

    /**
     * 修改招聘计划
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zpjihua:edit')")
    @Log(title = "招聘计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsZpjihua cmfHrmsZpjihua)
    {
        return toAjax(cmfHrmsZpjihuaService.updateCmfHrmsZpjihua(cmfHrmsZpjihua));
    }

    /**
     * 删除招聘计划
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zpjihua:remove')")
    @Log(title = "招聘计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsZpjihuaService.deleteCmfHrmsZpjihuaByIds(ids));
    }
}
