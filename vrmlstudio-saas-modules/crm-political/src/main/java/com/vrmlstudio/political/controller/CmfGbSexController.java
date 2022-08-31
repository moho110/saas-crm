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
import com.vrmlstudio.political.domain.CmfGbSex;
import com.vrmlstudio.political.service.ICmfGbSexService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 性别Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-political/sex")
public class CmfGbSexController extends BaseController
{
    @Autowired
    private ICmfGbSexService cmfGbSexService;

    /**
     * 查询性别列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:sex:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfGbSex cmfGbSex)
    {
        startPage();
        List<CmfGbSex> list = cmfGbSexService.selectCmfGbSexList(cmfGbSex);
        return getDataTable(list);
    }

    /**
     * 导出性别列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:sex:export')")
    @Log(title = "性别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfGbSex cmfGbSex)
    {
        List<CmfGbSex> list = cmfGbSexService.selectCmfGbSexList(cmfGbSex);
        ExcelUtil<CmfGbSex> util = new ExcelUtil<CmfGbSex>(CmfGbSex.class);
        util.exportExcel(response, list, "性别数据");
    }

    /**
     * 获取性别详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:sex:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfGbSexService.selectCmfGbSexById(id));
    }

    /**
     * 新增性别
     */
    @PreAuthorize("@ss.hasPermi('crm-political:sex:add')")
    @Log(title = "性别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfGbSex cmfGbSex)
    {
        return toAjax(cmfGbSexService.insertCmfGbSex(cmfGbSex));
    }

    /**
     * 修改性别
     */
    @PreAuthorize("@ss.hasPermi('crm-political:sex:edit')")
    @Log(title = "性别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfGbSex cmfGbSex)
    {
        return toAjax(cmfGbSexService.updateCmfGbSex(cmfGbSex));
    }

    /**
     * 删除性别
     */
    @PreAuthorize("@ss.hasPermi('crm-political:sex:remove')")
    @Log(title = "性别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfGbSexService.deleteCmfGbSexByIds(ids));
    }
}
