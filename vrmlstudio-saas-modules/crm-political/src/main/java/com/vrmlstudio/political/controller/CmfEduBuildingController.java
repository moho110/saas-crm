package com.vrmlstudio.political.controller;

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
import com.vrmlstudio.political.domain.CmfEduBuilding;
import com.vrmlstudio.political.service.ICmfEduBuildingService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 教学楼Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-political/edubuilding")
public class CmfEduBuildingController extends BaseController
{
    @Autowired
    private ICmfEduBuildingService cmfEduBuildingService;

    /**
     * 查询教学楼列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:building:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduBuilding cmfEduBuilding)
    {
        startPage();
        List<CmfEduBuilding> list = cmfEduBuildingService.selectCmfEduBuildingList(cmfEduBuilding);
        return getDataTable(list);
    }

    /**
     * 导出教学楼列表
     */
    @PreAuthorize("@ss.hasPermi('crm-political:building:export')")
    @Log(title = "教学楼", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduBuilding cmfEduBuilding)
    {
        List<CmfEduBuilding> list = cmfEduBuildingService.selectCmfEduBuildingList(cmfEduBuilding);
        ExcelUtil<CmfEduBuilding> util = new ExcelUtil<CmfEduBuilding>(CmfEduBuilding.class);
        util.exportExcel(response, list, "教学楼数据");
    }

    /**
     * 获取教学楼详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-political:building:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduBuildingService.selectCmfEduBuildingById(id));
    }

    /**
     * 新增教学楼
     */
    @PreAuthorize("@ss.hasPermi('crm-political:building:add')")
    @Log(title = "教学楼", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduBuilding cmfEduBuilding)
    {
        return toAjax(cmfEduBuildingService.insertCmfEduBuilding(cmfEduBuilding));
    }

    /**
     * 修改教学楼
     */
    @PreAuthorize("@ss.hasPermi('crm-political:building:edit')")
    @Log(title = "教学楼", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduBuilding cmfEduBuilding)
    {
        return toAjax(cmfEduBuildingService.updateCmfEduBuilding(cmfEduBuilding));
    }

    /**
     * 删除教学楼
     */
    @PreAuthorize("@ss.hasPermi('crm-political:building:remove')")
    @Log(title = "教学楼", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduBuildingService.deleteCmfEduBuildingByIds(ids));
    }
}
