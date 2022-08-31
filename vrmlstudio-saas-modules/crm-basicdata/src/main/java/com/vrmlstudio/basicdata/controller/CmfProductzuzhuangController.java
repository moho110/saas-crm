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
import com.vrmlstudio.basicdata.domain.CmfProductzuzhuang;
import com.vrmlstudio.basicdata.service.ICmfProductzuzhuangService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 产品组装Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/productzuzhuang")
public class CmfProductzuzhuangController extends BaseController
{
    @Autowired
    private ICmfProductzuzhuangService cmfProductzuzhuangService;

    /**
     * 查询产品组装列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuang:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfProductzuzhuang cmfProductzuzhuang)
    {
        startPage();
        List<CmfProductzuzhuang> list = cmfProductzuzhuangService.selectCmfProductzuzhuangList(cmfProductzuzhuang);
        return getDataTable(list);
    }

    /**
     * 导出产品组装列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuang:export')")
    @Log(title = "产品组装", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfProductzuzhuang cmfProductzuzhuang)
    {
        List<CmfProductzuzhuang> list = cmfProductzuzhuangService.selectCmfProductzuzhuangList(cmfProductzuzhuang);
        ExcelUtil<CmfProductzuzhuang> util = new ExcelUtil<CmfProductzuzhuang>(CmfProductzuzhuang.class);
        util.exportExcel(response, list, "产品组装数据");
    }

    /**
     * 获取产品组装详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuang:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfProductzuzhuangService.selectCmfProductzuzhuangById(id));
    }

    /**
     * 新增产品组装
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuang:add')")
    @Log(title = "产品组装", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfProductzuzhuang cmfProductzuzhuang)
    {
        return toAjax(cmfProductzuzhuangService.insertCmfProductzuzhuang(cmfProductzuzhuang));
    }

    /**
     * 修改产品组装
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuang:edit')")
    @Log(title = "产品组装", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfProductzuzhuang cmfProductzuzhuang)
    {
        return toAjax(cmfProductzuzhuangService.updateCmfProductzuzhuang(cmfProductzuzhuang));
    }

    /**
     * 删除产品组装
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:productzuzhuang:remove')")
    @Log(title = "产品组装", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfProductzuzhuangService.deleteCmfProductzuzhuangByIds(ids));
    }
}
