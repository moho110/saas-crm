package com.vrmlstudio.buy.controller;

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
import com.vrmlstudio.buy.domain.CmfIfneed;
import com.vrmlstudio.buy.service.ICmfIfneedService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 是否需求Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-buy/ifneed")
public class CmfIfneedController extends BaseController
{
    @Autowired
    private ICmfIfneedService cmfIfneedService;

    /**
     * 查询是否需求列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:ifneed:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfIfneed cmfIfneed)
    {
        startPage();
        List<CmfIfneed> list = cmfIfneedService.selectCmfIfneedList(cmfIfneed);
        return getDataTable(list);
    }

    /**
     * 导出是否需求列表
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:ifneed:export')")
    @Log(title = "是否需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfIfneed cmfIfneed)
    {
        List<CmfIfneed> list = cmfIfneedService.selectCmfIfneedList(cmfIfneed);
        ExcelUtil<CmfIfneed> util = new ExcelUtil<CmfIfneed>(CmfIfneed.class);
        util.exportExcel(response, list, "是否需求数据");
    }

    /**
     * 获取是否需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:ifneed:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfIfneedService.selectCmfIfneedById(id));
    }

    /**
     * 新增是否需求
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:ifneed:add')")
    @Log(title = "是否需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfIfneed cmfIfneed)
    {
        return toAjax(cmfIfneedService.insertCmfIfneed(cmfIfneed));
    }

    /**
     * 修改是否需求
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:ifneed:edit')")
    @Log(title = "是否需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfIfneed cmfIfneed)
    {
        return toAjax(cmfIfneedService.updateCmfIfneed(cmfIfneed));
    }

    /**
     * 删除是否需求
     */
    @PreAuthorize("@ss.hasPermi('crm-buy:ifneed:remove')")
    @Log(title = "是否需求", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfIfneedService.deleteCmfIfneedByIds(ids));
    }
}
