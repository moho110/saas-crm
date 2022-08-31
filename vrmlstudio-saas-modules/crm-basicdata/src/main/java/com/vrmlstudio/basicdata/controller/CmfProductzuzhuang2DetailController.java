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
import com.vrmlstudio.basicdata.domain.CmfProductzuzhuang2Detail;
import com.vrmlstudio.basicdata.service.ICmfProductzuzhuang2DetailService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 产品组装明细Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/2detail")
public class CmfProductzuzhuang2DetailController extends BaseController
{
    @Autowired
    private ICmfProductzuzhuang2DetailService cmfProductzuzhuang2DetailService;

    /**
     * 查询产品组装明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfProductzuzhuang2Detail cmfProductzuzhuang2Detail)
    {
        startPage();
        List<CmfProductzuzhuang2Detail> list = cmfProductzuzhuang2DetailService.selectCmfProductzuzhuang2DetailList(cmfProductzuzhuang2Detail);
        return getDataTable(list);
    }

    /**
     * 导出产品组装明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:export')")
    @Log(title = "产品组装明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfProductzuzhuang2Detail cmfProductzuzhuang2Detail)
    {
        List<CmfProductzuzhuang2Detail> list = cmfProductzuzhuang2DetailService.selectCmfProductzuzhuang2DetailList(cmfProductzuzhuang2Detail);
        ExcelUtil<CmfProductzuzhuang2Detail> util = new ExcelUtil<CmfProductzuzhuang2Detail>(CmfProductzuzhuang2Detail.class);
        util.exportExcel(response, list, "产品组装明细数据");
    }

    /**
     * 获取产品组装明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfProductzuzhuang2DetailService.selectCmfProductzuzhuang2DetailById(id));
    }

    /**
     * 新增产品组装明细
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:add')")
    @Log(title = "产品组装明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfProductzuzhuang2Detail cmfProductzuzhuang2Detail)
    {
        return toAjax(cmfProductzuzhuang2DetailService.insertCmfProductzuzhuang2Detail(cmfProductzuzhuang2Detail));
    }

    /**
     * 修改产品组装明细
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:edit')")
    @Log(title = "产品组装明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfProductzuzhuang2Detail cmfProductzuzhuang2Detail)
    {
        return toAjax(cmfProductzuzhuang2DetailService.updateCmfProductzuzhuang2Detail(cmfProductzuzhuang2Detail));
    }

    /**
     * 删除产品组装明细
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:remove')")
    @Log(title = "产品组装明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfProductzuzhuang2DetailService.deleteCmfProductzuzhuang2DetailByIds(ids));
    }
}
