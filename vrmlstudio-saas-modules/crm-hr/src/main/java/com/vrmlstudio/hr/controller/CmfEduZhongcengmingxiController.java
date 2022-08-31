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
import com.vrmlstudio.hr.domain.CmfEduZhongcengmingxi;
import com.vrmlstudio.hr.service.ICmfEduZhongcengmingxiService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 中层干部测评内容明细Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/zhongcengmingxi")
public class CmfEduZhongcengmingxiController extends BaseController
{
    @Autowired
    private ICmfEduZhongcengmingxiService cmfEduZhongcengmingxiService;

    /**
     * 查询中层干部测评内容明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengmingxi:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduZhongcengmingxi cmfEduZhongcengmingxi)
    {
        startPage();
        List<CmfEduZhongcengmingxi> list = cmfEduZhongcengmingxiService.selectCmfEduZhongcengmingxiList(cmfEduZhongcengmingxi);
        return getDataTable(list);
    }

    /**
     * 导出中层干部测评内容明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengmingxi:export')")
    @Log(title = "中层干部测评内容明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduZhongcengmingxi cmfEduZhongcengmingxi)
    {
        List<CmfEduZhongcengmingxi> list = cmfEduZhongcengmingxiService.selectCmfEduZhongcengmingxiList(cmfEduZhongcengmingxi);
        ExcelUtil<CmfEduZhongcengmingxi> util = new ExcelUtil<CmfEduZhongcengmingxi>(CmfEduZhongcengmingxi.class);
        util.exportExcel(response, list, "中层干部测评内容明细数据");
    }

    /**
     * 获取中层干部测评内容明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengmingxi:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduZhongcengmingxiService.selectCmfEduZhongcengmingxiById(id));
    }

    /**
     * 新增中层干部测评内容明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengmingxi:add')")
    @Log(title = "中层干部测评内容明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduZhongcengmingxi cmfEduZhongcengmingxi)
    {
        return toAjax(cmfEduZhongcengmingxiService.insertCmfEduZhongcengmingxi(cmfEduZhongcengmingxi));
    }

    /**
     * 修改中层干部测评内容明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengmingxi:edit')")
    @Log(title = "中层干部测评内容明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduZhongcengmingxi cmfEduZhongcengmingxi)
    {
        return toAjax(cmfEduZhongcengmingxiService.updateCmfEduZhongcengmingxi(cmfEduZhongcengmingxi));
    }

    /**
     * 删除中层干部测评内容明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhongcengmingxi:remove')")
    @Log(title = "中层干部测评内容明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduZhongcengmingxiService.deleteCmfEduZhongcengmingxiByIds(ids));
    }
}
