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
import com.vrmlstudio.hr.domain.CmfHrmsTransferType;
import com.vrmlstudio.hr.service.ICmfHrmsTransferTypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 转职类型Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-hr/trtype")
public class CmfHrmsTransferTypeController extends BaseController
{
    @Autowired
    private ICmfHrmsTransferTypeService cmfHrmsTransferTypeService;

    /**
     * 查询转职类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:trtype:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsTransferType cmfHrmsTransferType)
    {
        startPage();
        List<CmfHrmsTransferType> list = cmfHrmsTransferTypeService.selectCmfHrmsTransferTypeList(cmfHrmsTransferType);
        return getDataTable(list);
    }

    /**
     * 导出转职类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:trtype:export')")
    @Log(title = "转职类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsTransferType cmfHrmsTransferType)
    {
        List<CmfHrmsTransferType> list = cmfHrmsTransferTypeService.selectCmfHrmsTransferTypeList(cmfHrmsTransferType);
        ExcelUtil<CmfHrmsTransferType> util = new ExcelUtil<CmfHrmsTransferType>(CmfHrmsTransferType.class);
        util.exportExcel(response, list, "转职类型数据");
    }

    /**
     * 获取转职类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:trtype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsTransferTypeService.selectCmfHrmsTransferTypeById(id));
    }

    /**
     * 新增转职类型
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:trtype:add')")
    @Log(title = "转职类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsTransferType cmfHrmsTransferType)
    {
        return toAjax(cmfHrmsTransferTypeService.insertCmfHrmsTransferType(cmfHrmsTransferType));
    }

    /**
     * 修改转职类型
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:trtype:edit')")
    @Log(title = "转职类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsTransferType cmfHrmsTransferType)
    {
        return toAjax(cmfHrmsTransferTypeService.updateCmfHrmsTransferType(cmfHrmsTransferType));
    }

    /**
     * 删除转职类型
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:trtype:remove')")
    @Log(title = "转职类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsTransferTypeService.deleteCmfHrmsTransferTypeByIds(ids));
    }
}
