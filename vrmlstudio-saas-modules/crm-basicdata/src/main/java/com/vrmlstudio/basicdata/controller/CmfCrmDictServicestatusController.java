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
import com.vrmlstudio.basicdata.domain.CmfCrmDictServicestatus;
import com.vrmlstudio.basicdata.service.ICmfCrmDictServicestatusService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 服务状态Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-basicdata/servicestatus")
public class CmfCrmDictServicestatusController extends BaseController
{
    @Autowired
    private ICmfCrmDictServicestatusService cmfCrmDictServicestatusService;

    /**
     * 查询服务状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicestatus:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCrmDictServicestatus cmfCrmDictServicestatus)
    {
        startPage();
        List<CmfCrmDictServicestatus> list = cmfCrmDictServicestatusService.selectCmfCrmDictServicestatusList(cmfCrmDictServicestatus);
        return getDataTable(list);
    }

    /**
     * 导出服务状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicestatus:export')")
    @Log(title = "服务状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCrmDictServicestatus cmfCrmDictServicestatus)
    {
        List<CmfCrmDictServicestatus> list = cmfCrmDictServicestatusService.selectCmfCrmDictServicestatusList(cmfCrmDictServicestatus);
        ExcelUtil<CmfCrmDictServicestatus> util = new ExcelUtil<CmfCrmDictServicestatus>(CmfCrmDictServicestatus.class);
        util.exportExcel(response, list, "服务状态数据");
    }

    /**
     * 获取服务状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicestatus:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCrmDictServicestatusService.selectCmfCrmDictServicestatusById(id));
    }

    /**
     * 新增服务状态
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicestatus:add')")
    @Log(title = "服务状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCrmDictServicestatus cmfCrmDictServicestatus)
    {
        return toAjax(cmfCrmDictServicestatusService.insertCmfCrmDictServicestatus(cmfCrmDictServicestatus));
    }

    /**
     * 修改服务状态
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicestatus:edit')")
    @Log(title = "服务状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCrmDictServicestatus cmfCrmDictServicestatus)
    {
        return toAjax(cmfCrmDictServicestatusService.updateCmfCrmDictServicestatus(cmfCrmDictServicestatus));
    }

    /**
     * 删除服务状态
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:servicestatus:remove')")
    @Log(title = "服务状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCrmDictServicestatusService.deleteCmfCrmDictServicestatusByIds(ids));
    }
}
