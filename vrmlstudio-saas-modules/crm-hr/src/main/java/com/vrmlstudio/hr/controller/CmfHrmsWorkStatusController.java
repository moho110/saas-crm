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
import com.vrmlstudio.hr.domain.CmfHrmsWorkStatus;
import com.vrmlstudio.hr.service.ICmfHrmsWorkStatusService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 工作人员状态Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-hr/wstatus")
public class CmfHrmsWorkStatusController extends BaseController
{
    @Autowired
    private ICmfHrmsWorkStatusService cmfHrmsWorkStatusService;

    /**
     * 查询工作人员状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:status:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsWorkStatus cmfHrmsWorkStatus)
    {
        startPage();
        List<CmfHrmsWorkStatus> list = cmfHrmsWorkStatusService.selectCmfHrmsWorkStatusList(cmfHrmsWorkStatus);
        return getDataTable(list);
    }

    /**
     * 导出工作人员状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:status:export')")
    @Log(title = "工作人员状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsWorkStatus cmfHrmsWorkStatus)
    {
        List<CmfHrmsWorkStatus> list = cmfHrmsWorkStatusService.selectCmfHrmsWorkStatusList(cmfHrmsWorkStatus);
        ExcelUtil<CmfHrmsWorkStatus> util = new ExcelUtil<CmfHrmsWorkStatus>(CmfHrmsWorkStatus.class);
        util.exportExcel(response, list, "工作人员状态数据");
    }

    /**
     * 获取工作人员状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:status:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsWorkStatusService.selectCmfHrmsWorkStatusById(id));
    }

    /**
     * 新增工作人员状态
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:status:add')")
    @Log(title = "工作人员状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsWorkStatus cmfHrmsWorkStatus)
    {
        return toAjax(cmfHrmsWorkStatusService.insertCmfHrmsWorkStatus(cmfHrmsWorkStatus));
    }

    /**
     * 修改工作人员状态
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:status:edit')")
    @Log(title = "工作人员状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsWorkStatus cmfHrmsWorkStatus)
    {
        return toAjax(cmfHrmsWorkStatusService.updateCmfHrmsWorkStatus(cmfHrmsWorkStatus));
    }

    /**
     * 删除工作人员状态
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:status:remove')")
    @Log(title = "工作人员状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsWorkStatusService.deleteCmfHrmsWorkStatusByIds(ids));
    }
}
