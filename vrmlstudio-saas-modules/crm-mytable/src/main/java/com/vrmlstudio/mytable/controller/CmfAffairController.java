package com.vrmlstudio.mytable.controller;

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
import com.vrmlstudio.mytable.domain.CmfAffair;
import com.vrmlstudio.mytable.service.ICmfAffairService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 事务Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-mytable/affair")
public class CmfAffairController extends BaseController
{
    @Autowired
    private ICmfAffairService cmfAffairService;

    /**
     * 查询事务列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:affair:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfAffair cmfAffair)
    {
        startPage();
        List<CmfAffair> list = cmfAffairService.selectCmfAffairList(cmfAffair);
        return getDataTable(list);
    }

    /**
     * 导出事务列表
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:affair:export')")
    @Log(title = "事务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfAffair cmfAffair)
    {
        List<CmfAffair> list = cmfAffairService.selectCmfAffairList(cmfAffair);
        ExcelUtil<CmfAffair> util = new ExcelUtil<CmfAffair>(CmfAffair.class);
        util.exportExcel(response, list, "事务数据");
    }

    /**
     * 获取事务详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:affair:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfAffairService.selectCmfAffairById(id));
    }

    /**
     * 新增事务
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:affair:add')")
    @Log(title = "事务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfAffair cmfAffair)
    {
        return toAjax(cmfAffairService.insertCmfAffair(cmfAffair));
    }

    /**
     * 修改事务
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:affair:edit')")
    @Log(title = "事务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfAffair cmfAffair)
    {
        return toAjax(cmfAffairService.updateCmfAffair(cmfAffair));
    }

    /**
     * 删除事务
     */
    @PreAuthorize("@ss.hasPermi('crm-mytable:affair:remove')")
    @Log(title = "事务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfAffairService.deleteCmfAffairByIds(ids));
    }
}
