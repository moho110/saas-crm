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
import com.vrmlstudio.basicdata.domain.CmfSupplylinkman;
import com.vrmlstudio.basicdata.service.ICmfSupplylinkmanService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 供应商联系人Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/supplylinkman")
public class CmfSupplylinkmanController extends BaseController
{
    @Autowired
    private ICmfSupplylinkmanService cmfSupplylinkmanService;

    /**
     * 查询供应商联系人列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplylinkman:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfSupplylinkman cmfSupplylinkman)
    {
        startPage();
        List<CmfSupplylinkman> list = cmfSupplylinkmanService.selectCmfSupplylinkmanList(cmfSupplylinkman);
        return getDataTable(list);
    }

    /**
     * 导出供应商联系人列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplylinkman:export')")
    @Log(title = "供应商联系人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfSupplylinkman cmfSupplylinkman)
    {
        List<CmfSupplylinkman> list = cmfSupplylinkmanService.selectCmfSupplylinkmanList(cmfSupplylinkman);
        ExcelUtil<CmfSupplylinkman> util = new ExcelUtil<CmfSupplylinkman>(CmfSupplylinkman.class);
        util.exportExcel(response, list, "供应商联系人数据");
    }

    /**
     * 获取供应商联系人详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplylinkman:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfSupplylinkmanService.selectCmfSupplylinkmanById(id));
    }

    /**
     * 新增供应商联系人
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplylinkman:add')")
    @Log(title = "供应商联系人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfSupplylinkman cmfSupplylinkman)
    {
        return toAjax(cmfSupplylinkmanService.insertCmfSupplylinkman(cmfSupplylinkman));
    }

    /**
     * 修改供应商联系人
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplylinkman:edit')")
    @Log(title = "供应商联系人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfSupplylinkman cmfSupplylinkman)
    {
        return toAjax(cmfSupplylinkmanService.updateCmfSupplylinkman(cmfSupplylinkman));
    }

    /**
     * 删除供应商联系人
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:supplylinkman:remove')")
    @Log(title = "供应商联系人", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfSupplylinkmanService.deleteCmfSupplylinkmanByIds(ids));
    }
}
