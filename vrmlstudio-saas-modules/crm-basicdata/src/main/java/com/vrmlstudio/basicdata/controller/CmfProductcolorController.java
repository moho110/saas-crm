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
import com.vrmlstudio.basicdata.domain.CmfProductcolor;
import com.vrmlstudio.basicdata.service.ICmfProductcolorService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 产品颜色Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/productcolor")
public class CmfProductcolorController extends BaseController
{
    @Autowired
    private ICmfProductcolorService cmfProductcolorService;

    /**
     * 查询产品颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productcolor:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfProductcolor cmfProductcolor)
    {
        startPage();
        List<CmfProductcolor> list = cmfProductcolorService.selectCmfProductcolorList(cmfProductcolor);
        return getDataTable(list);
    }

    /**
     * 导出产品颜色列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productcolor:export')")
    @Log(title = "产品颜色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfProductcolor cmfProductcolor)
    {
        List<CmfProductcolor> list = cmfProductcolorService.selectCmfProductcolorList(cmfProductcolor);
        ExcelUtil<CmfProductcolor> util = new ExcelUtil<CmfProductcolor>(CmfProductcolor.class);
        util.exportExcel(response, list, "产品颜色数据");
    }

    /**
     * 获取产品颜色详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productcolor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfProductcolorService.selectCmfProductcolorById(id));
    }

    /**
     * 新增产品颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productcolor:add')")
    @Log(title = "产品颜色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfProductcolor cmfProductcolor)
    {
        return toAjax(cmfProductcolorService.insertCmfProductcolor(cmfProductcolor));
    }

    /**
     * 修改产品颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productcolor:edit')")
    @Log(title = "产品颜色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfProductcolor cmfProductcolor)
    {
        return toAjax(cmfProductcolorService.updateCmfProductcolor(cmfProductcolor));
    }

    /**
     * 删除产品颜色
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productcolor:remove')")
    @Log(title = "产品颜色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfProductcolorService.deleteCmfProductcolorByIds(ids));
    }
}
