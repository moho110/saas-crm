package com.vrmlstudio.finance.controller;

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
import com.vrmlstudio.finance.domain.CmfAccessbank;
import com.vrmlstudio.finance.service.ICmfAccessbankService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 帐号操作Controller
 * 
 * @author VRer
 * @date 2022-04-13
 */
@RestController
@RequestMapping("/crm-finance/accessbank")
public class CmfAccessbankController extends BaseController
{
    @Autowired
    private ICmfAccessbankService cmfAccessbankService;

    /**
     * 查询帐号操作列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessbank:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfAccessbank cmfAccessbank)
    {
        startPage();
        List<CmfAccessbank> list = cmfAccessbankService.selectCmfAccessbankList(cmfAccessbank);
        return getDataTable(list);
    }

    /**
     * 导出帐号操作列表
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessbank:export')")
    @Log(title = "帐号操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfAccessbank cmfAccessbank)
    {
        List<CmfAccessbank> list = cmfAccessbankService.selectCmfAccessbankList(cmfAccessbank);
        ExcelUtil<CmfAccessbank> util = new ExcelUtil<CmfAccessbank>(CmfAccessbank.class);
        util.exportExcel(response, list, "帐号操作数据");
    }

    /**
     * 获取帐号操作详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessbank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfAccessbankService.selectCmfAccessbankById(id));
    }

    /**
     * 新增帐号操作
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessbank:add')")
    @Log(title = "帐号操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfAccessbank cmfAccessbank)
    {
        return toAjax(cmfAccessbankService.insertCmfAccessbank(cmfAccessbank));
    }

    /**
     * 修改帐号操作
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessbank:edit')")
    @Log(title = "帐号操作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfAccessbank cmfAccessbank)
    {
        return toAjax(cmfAccessbankService.updateCmfAccessbank(cmfAccessbank));
    }

    /**
     * 删除帐号操作
     */
    @PreAuthorize("@ss.hasPermi('crm-finance:accessbank:remove')")
    @Log(title = "帐号操作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfAccessbankService.deleteCmfAccessbankByIds(ids));
    }
}
