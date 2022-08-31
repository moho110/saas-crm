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
import com.vrmlstudio.basicdata.domain.CmfCmdictCountrycode;
import com.vrmlstudio.basicdata.service.ICmfCmdictCountrycodeService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 国家代码Controller
 * 
 * @author VRer
 * @date 2022-04-14
 */
@RestController
@RequestMapping("/crm-basicdata/countrycode")
public class CmfCmdictCountrycodeController extends BaseController
{
    @Autowired
    private ICmfCmdictCountrycodeService cmfCmdictCountrycodeService;

    /**
     * 查询国家代码列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:countrycode:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfCmdictCountrycode cmfCmdictCountrycode)
    {
        startPage();
        List<CmfCmdictCountrycode> list = cmfCmdictCountrycodeService.selectCmfCmdictCountrycodeList(cmfCmdictCountrycode);
        return getDataTable(list);
    }

    /**
     * 导出国家代码列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:countrycode:export')")
    @Log(title = "国家代码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfCmdictCountrycode cmfCmdictCountrycode)
    {
        List<CmfCmdictCountrycode> list = cmfCmdictCountrycodeService.selectCmfCmdictCountrycodeList(cmfCmdictCountrycode);
        ExcelUtil<CmfCmdictCountrycode> util = new ExcelUtil<CmfCmdictCountrycode>(CmfCmdictCountrycode.class);
        util.exportExcel(response, list, "国家代码数据");
    }

    /**
     * 获取国家代码详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:countrycode:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfCmdictCountrycodeService.selectCmfCmdictCountrycodeById(id));
    }

    /**
     * 新增国家代码
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:countrycode:add')")
    @Log(title = "国家代码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfCmdictCountrycode cmfCmdictCountrycode)
    {
        return toAjax(cmfCmdictCountrycodeService.insertCmfCmdictCountrycode(cmfCmdictCountrycode));
    }

    /**
     * 修改国家代码
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:countrycode:edit')")
    @Log(title = "国家代码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfCmdictCountrycode cmfCmdictCountrycode)
    {
        return toAjax(cmfCmdictCountrycodeService.updateCmfCmdictCountrycode(cmfCmdictCountrycode));
    }

    /**
     * 删除国家代码
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:countrycode:remove')")
    @Log(title = "国家代码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfCmdictCountrycodeService.deleteCmfCmdictCountrycodeByIds(ids));
    }
}
