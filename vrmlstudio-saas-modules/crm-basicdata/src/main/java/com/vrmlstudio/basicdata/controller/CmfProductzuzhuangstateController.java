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
import com.vrmlstudio.basicdata.domain.CmfProductzuzhuangstate;
import com.vrmlstudio.basicdata.service.ICmfProductzuzhuangstateService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 产品组装状态Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/productzuzhuangstate")
public class CmfProductzuzhuangstateController extends BaseController
{
    @Autowired
    private ICmfProductzuzhuangstateService cmfProductzuzhuangstateService;

    /**
     * 查询产品组装状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuangstate:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfProductzuzhuangstate cmfProductzuzhuangstate)
    {
        startPage();
        List<CmfProductzuzhuangstate> list = cmfProductzuzhuangstateService.selectCmfProductzuzhuangstateList(cmfProductzuzhuangstate);
        return getDataTable(list);
    }

    /**
     * 导出产品组装状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuangstate:export')")
    @Log(title = "产品组装状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfProductzuzhuangstate cmfProductzuzhuangstate)
    {
        List<CmfProductzuzhuangstate> list = cmfProductzuzhuangstateService.selectCmfProductzuzhuangstateList(cmfProductzuzhuangstate);
        ExcelUtil<CmfProductzuzhuangstate> util = new ExcelUtil<CmfProductzuzhuangstate>(CmfProductzuzhuangstate.class);
        util.exportExcel(response, list, "产品组装状态数据");
    }

    /**
     * 获取产品组装状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuangstate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfProductzuzhuangstateService.selectCmfProductzuzhuangstateById(id));
    }

    /**
     * 新增产品组装状态
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuangstate:add')")
    @Log(title = "产品组装状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfProductzuzhuangstate cmfProductzuzhuangstate)
    {
        return toAjax(cmfProductzuzhuangstateService.insertCmfProductzuzhuangstate(cmfProductzuzhuangstate));
    }

    /**
     * 修改产品组装状态
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuangstate:edit')")
    @Log(title = "产品组装状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfProductzuzhuangstate cmfProductzuzhuangstate)
    {
        return toAjax(cmfProductzuzhuangstateService.updateCmfProductzuzhuangstate(cmfProductzuzhuangstate));
    }

    /**
     * 删除产品组装状态
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuangstate:remove')")
    @Log(title = "产品组装状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfProductzuzhuangstateService.deleteCmfProductzuzhuangstateByIds(ids));
    }
}
