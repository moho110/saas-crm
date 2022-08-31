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
import com.vrmlstudio.hr.domain.CmfHrmsRewardPunishment;
import com.vrmlstudio.hr.service.ICmfHrmsRewardPunishmentService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 人员奖惩Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-hr/punishment")
public class CmfHrmsRewardPunishmentController extends BaseController
{
    @Autowired
    private ICmfHrmsRewardPunishmentService cmfHrmsRewardPunishmentService;

    /**
     * 查询人员奖惩列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:punishment:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsRewardPunishment cmfHrmsRewardPunishment)
    {
        startPage();
        List<CmfHrmsRewardPunishment> list = cmfHrmsRewardPunishmentService.selectCmfHrmsRewardPunishmentList(cmfHrmsRewardPunishment);
        return getDataTable(list);
    }

    /**
     * 导出人员奖惩列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:punishment:export')")
    @Log(title = "人员奖惩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsRewardPunishment cmfHrmsRewardPunishment)
    {
        List<CmfHrmsRewardPunishment> list = cmfHrmsRewardPunishmentService.selectCmfHrmsRewardPunishmentList(cmfHrmsRewardPunishment);
        ExcelUtil<CmfHrmsRewardPunishment> util = new ExcelUtil<CmfHrmsRewardPunishment>(CmfHrmsRewardPunishment.class);
        util.exportExcel(response, list, "人员奖惩数据");
    }

    /**
     * 获取人员奖惩详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:punishment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsRewardPunishmentService.selectCmfHrmsRewardPunishmentById(id));
    }

    /**
     * 新增人员奖惩
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:punishment:add')")
    @Log(title = "人员奖惩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsRewardPunishment cmfHrmsRewardPunishment)
    {
        return toAjax(cmfHrmsRewardPunishmentService.insertCmfHrmsRewardPunishment(cmfHrmsRewardPunishment));
    }

    /**
     * 修改人员奖惩
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:punishment:edit')")
    @Log(title = "人员奖惩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsRewardPunishment cmfHrmsRewardPunishment)
    {
        return toAjax(cmfHrmsRewardPunishmentService.updateCmfHrmsRewardPunishment(cmfHrmsRewardPunishment));
    }

    /**
     * 删除人员奖惩
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:punishment:remove')")
    @Log(title = "人员奖惩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsRewardPunishmentService.deleteCmfHrmsRewardPunishmentByIds(ids));
    }
}
