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
import com.vrmlstudio.hr.domain.CmfHrmsWorkerHetong;
import com.vrmlstudio.hr.service.ICmfHrmsWorkerHetongService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 工作人员合同Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-hr/workerhetong")
public class CmfHrmsWorkerHetongController extends BaseController
{
    @Autowired
    private ICmfHrmsWorkerHetongService cmfHrmsWorkerHetongService;

    /**
     * 查询工作人员合同列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workerhetong:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsWorkerHetong cmfHrmsWorkerHetong)
    {
        startPage();
        List<CmfHrmsWorkerHetong> list = cmfHrmsWorkerHetongService.selectCmfHrmsWorkerHetongList(cmfHrmsWorkerHetong);
        return getDataTable(list);
    }

    /**
     * 导出工作人员合同列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workerhetong:export')")
    @Log(title = "工作人员合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsWorkerHetong cmfHrmsWorkerHetong)
    {
        List<CmfHrmsWorkerHetong> list = cmfHrmsWorkerHetongService.selectCmfHrmsWorkerHetongList(cmfHrmsWorkerHetong);
        ExcelUtil<CmfHrmsWorkerHetong> util = new ExcelUtil<CmfHrmsWorkerHetong>(CmfHrmsWorkerHetong.class);
        util.exportExcel(response, list, "工作人员合同数据");
    }

    /**
     * 获取工作人员合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workerhetong:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsWorkerHetongService.selectCmfHrmsWorkerHetongById(id));
    }

    /**
     * 新增工作人员合同
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workerhetong:add')")
    @Log(title = "工作人员合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsWorkerHetong cmfHrmsWorkerHetong)
    {
        return toAjax(cmfHrmsWorkerHetongService.insertCmfHrmsWorkerHetong(cmfHrmsWorkerHetong));
    }

    /**
     * 修改工作人员合同
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workerhetong:edit')")
    @Log(title = "工作人员合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsWorkerHetong cmfHrmsWorkerHetong)
    {
        return toAjax(cmfHrmsWorkerHetongService.updateCmfHrmsWorkerHetong(cmfHrmsWorkerHetong));
    }

    /**
     * 删除工作人员合同
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workerhetong:remove')")
    @Log(title = "工作人员合同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsWorkerHetongService.deleteCmfHrmsWorkerHetongByIds(ids));
    }
}
