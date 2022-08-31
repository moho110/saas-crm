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
import com.vrmlstudio.basicdata.domain.CmfCrmDictServicetype;
import com.vrmlstudio.basicdata.service.ICmfCrmDictServicetypeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 服务类型Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-basicdata/servicetype")
public class CmfCrmDictServicetypeController extends BaseController
{
    @Autowired
    private ICmfCrmDictServicetypeService cmfCrmDictServicetypeService;

    /**
     * 查询服务类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicetype:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCrmDictServicetype cmfCrmDictServicetype)
    {
        startPage();
        List<CmfCrmDictServicetype> list = cmfCrmDictServicetypeService.selectCmfCrmDictServicetypeList(cmfCrmDictServicetype);
        return getDataTable(list);
    }

    /**
     * 导出服务类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicetype:export')")
    @Log(title = "服务类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCrmDictServicetype cmfCrmDictServicetype)
    {
        List<CmfCrmDictServicetype> list = cmfCrmDictServicetypeService.selectCmfCrmDictServicetypeList(cmfCrmDictServicetype);
        ExcelUtil<CmfCrmDictServicetype> util = new ExcelUtil<CmfCrmDictServicetype>(CmfCrmDictServicetype.class);
        util.exportExcel(response, list, "服务类型数据");
    }

    /**
     * 获取服务类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicetype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCrmDictServicetypeService.selectCmfCrmDictServicetypeById(id));
    }

    /**
     * 新增服务类型
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicetype:add')")
    @Log(title = "服务类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCrmDictServicetype cmfCrmDictServicetype)
    {
        return toAjax(cmfCrmDictServicetypeService.insertCmfCrmDictServicetype(cmfCrmDictServicetype));
    }

    /**
     * 修改服务类型
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicetype:edit')")
    @Log(title = "服务类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCrmDictServicetype cmfCrmDictServicetype)
    {
        return toAjax(cmfCrmDictServicetypeService.updateCmfCrmDictServicetype(cmfCrmDictServicetype));
    }

    /**
     * 删除服务类型
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicetype:remove')")
    @Log(title = "服务类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCrmDictServicetypeService.deleteCmfCrmDictServicetypeByIds(ids));
    }
}
