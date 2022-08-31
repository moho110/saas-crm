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
import com.vrmlstudio.basicdata.domain.CmfRecycleBin;
import com.vrmlstudio.basicdata.service.ICmfRecycleBinService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 回收站Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/basicbin")
public class CmfRecycleBinController extends BaseController
{
    @Autowired
    private ICmfRecycleBinService cmfRecycleBinService;

    /**
     * 查询回收站列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:basicbin:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfRecycleBin cmfRecycleBin)
    {
        startPage();
        List<CmfRecycleBin> list = cmfRecycleBinService.selectCmfRecycleBinList(cmfRecycleBin);
        return getDataTable(list);
    }

    /**
     * 导出回收站列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:basicbin:export')")
    @Log(title = "回收站", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfRecycleBin cmfRecycleBin)
    {
        List<CmfRecycleBin> list = cmfRecycleBinService.selectCmfRecycleBinList(cmfRecycleBin);
        ExcelUtil<CmfRecycleBin> util = new ExcelUtil<CmfRecycleBin>(CmfRecycleBin.class);
        util.exportExcel(response, list, "回收站数据");
    }

    /**
     * 获取回收站详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:basicbin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfRecycleBinService.selectCmfRecycleBinById(id));
    }

    /**
     * 新增回收站
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:basicbin:add')")
    @Log(title = "回收站", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfRecycleBin cmfRecycleBin)
    {
        return toAjax(cmfRecycleBinService.insertCmfRecycleBin(cmfRecycleBin));
    }

    /**
     * 修改回收站
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:basicbin:edit')")
    @Log(title = "回收站", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfRecycleBin cmfRecycleBin)
    {
        return toAjax(cmfRecycleBinService.updateCmfRecycleBin(cmfRecycleBin));
    }

    /**
     * 删除回收站
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:basicbin:remove')")
    @Log(title = "回收站", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfRecycleBinService.deleteCmfRecycleBinByIds(ids));
    }
}
