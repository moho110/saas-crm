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
import com.vrmlstudio.hr.domain.CmfDictXingbie;
import com.vrmlstudio.hr.service.ICmfDictXingbieService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 性别Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/xingbie")
public class CmfDictXingbieController extends BaseController
{
    @Autowired
    private ICmfDictXingbieService cmfDictXingbieService;

    /**
     * 查询性别列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingbie:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfDictXingbie cmfDictXingbie)
    {
        startPage();
        List<CmfDictXingbie> list = cmfDictXingbieService.selectCmfDictXingbieList(cmfDictXingbie);
        return getDataTable(list);
    }

    /**
     * 导出性别列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingbie:export')")
    @Log(title = "性别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfDictXingbie cmfDictXingbie)
    {
        List<CmfDictXingbie> list = cmfDictXingbieService.selectCmfDictXingbieList(cmfDictXingbie);
        ExcelUtil<CmfDictXingbie> util = new ExcelUtil<CmfDictXingbie>(CmfDictXingbie.class);
        util.exportExcel(response, list, "性别数据");
    }

    /**
     * 获取性别详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingbie:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfDictXingbieService.selectCmfDictXingbieById(id));
    }

    /**
     * 新增性别
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingbie:add')")
    @Log(title = "性别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfDictXingbie cmfDictXingbie)
    {
        return toAjax(cmfDictXingbieService.insertCmfDictXingbie(cmfDictXingbie));
    }

    /**
     * 修改性别
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingbie:edit')")
    @Log(title = "性别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfDictXingbie cmfDictXingbie)
    {
        return toAjax(cmfDictXingbieService.updateCmfDictXingbie(cmfDictXingbie));
    }

    /**
     * 删除性别
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingbie:remove')")
    @Log(title = "性别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfDictXingbieService.deleteCmfDictXingbieByIds(ids));
    }
}
