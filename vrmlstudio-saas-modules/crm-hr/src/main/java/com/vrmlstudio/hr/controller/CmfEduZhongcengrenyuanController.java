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
import com.vrmlstudio.hr.domain.CmfEduZhongcengrenyuan;
import com.vrmlstudio.hr.service.ICmfEduZhongcengrenyuanService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 中层干部被评人员明细Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/zhongcengrenyuan")
public class CmfEduZhongcengrenyuanController extends BaseController
{
    @Autowired
    private ICmfEduZhongcengrenyuanService cmfEduZhongcengrenyuanService;

    /**
     * 查询中层干部被评人员明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengrenyuan:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduZhongcengrenyuan cmfEduZhongcengrenyuan)
    {
        startPage();
        List<CmfEduZhongcengrenyuan> list = cmfEduZhongcengrenyuanService.selectCmfEduZhongcengrenyuanList(cmfEduZhongcengrenyuan);
        return getDataTable(list);
    }

    /**
     * 导出中层干部被评人员明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengrenyuan:export')")
    @Log(title = "中层干部被评人员明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduZhongcengrenyuan cmfEduZhongcengrenyuan)
    {
        List<CmfEduZhongcengrenyuan> list = cmfEduZhongcengrenyuanService.selectCmfEduZhongcengrenyuanList(cmfEduZhongcengrenyuan);
        ExcelUtil<CmfEduZhongcengrenyuan> util = new ExcelUtil<CmfEduZhongcengrenyuan>(CmfEduZhongcengrenyuan.class);
        util.exportExcel(response, list, "中层干部被评人员明细数据");
    }

    /**
     * 获取中层干部被评人员明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengrenyuan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduZhongcengrenyuanService.selectCmfEduZhongcengrenyuanById(id));
    }

    /**
     * 新增中层干部被评人员明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengrenyuan:add')")
    @Log(title = "中层干部被评人员明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduZhongcengrenyuan cmfEduZhongcengrenyuan)
    {
        return toAjax(cmfEduZhongcengrenyuanService.insertCmfEduZhongcengrenyuan(cmfEduZhongcengrenyuan));
    }

    /**
     * 修改中层干部被评人员明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengrenyuan:edit')")
    @Log(title = "中层干部被评人员明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduZhongcengrenyuan cmfEduZhongcengrenyuan)
    {
        return toAjax(cmfEduZhongcengrenyuanService.updateCmfEduZhongcengrenyuan(cmfEduZhongcengrenyuan));
    }

    /**
     * 删除中层干部被评人员明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengrenyuan:remove')")
    @Log(title = "中层干部被评人员明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduZhongcengrenyuanService.deleteCmfEduZhongcengrenyuanByIds(ids));
    }
}
