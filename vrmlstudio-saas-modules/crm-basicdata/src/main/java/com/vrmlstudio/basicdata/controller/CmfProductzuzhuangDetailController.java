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
import com.vrmlstudio.basicdata.domain.CmfProductzuzhuangDetail;
import com.vrmlstudio.basicdata.service.ICmfProductzuzhuangDetailService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 产品组装明细Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/detail")
public class CmfProductzuzhuangDetailController extends BaseController
{
    @Autowired
    private ICmfProductzuzhuangDetailService cmfProductzuzhuangDetailService;

    /**
     * 查询产品组装明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfProductzuzhuangDetail cmfProductzuzhuangDetail)
    {
        startPage();
        List<CmfProductzuzhuangDetail> list = cmfProductzuzhuangDetailService.selectCmfProductzuzhuangDetailList(cmfProductzuzhuangDetail);
        return getDataTable(list);
    }

    /**
     * 导出产品组装明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:export')")
    @Log(title = "产品组装明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfProductzuzhuangDetail cmfProductzuzhuangDetail)
    {
        List<CmfProductzuzhuangDetail> list = cmfProductzuzhuangDetailService.selectCmfProductzuzhuangDetailList(cmfProductzuzhuangDetail);
        ExcelUtil<CmfProductzuzhuangDetail> util = new ExcelUtil<CmfProductzuzhuangDetail>(CmfProductzuzhuangDetail.class);
        util.exportExcel(response, list, "产品组装明细数据");
    }

    /**
     * 获取产品组装明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfProductzuzhuangDetailService.selectCmfProductzuzhuangDetailById(id));
    }

    /**
     * 新增产品组装明细
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:add')")
    @Log(title = "产品组装明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfProductzuzhuangDetail cmfProductzuzhuangDetail)
    {
        return toAjax(cmfProductzuzhuangDetailService.insertCmfProductzuzhuangDetail(cmfProductzuzhuangDetail));
    }

    /**
     * 修改产品组装明细
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:edit')")
    @Log(title = "产品组装明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfProductzuzhuangDetail cmfProductzuzhuangDetail)
    {
        return toAjax(cmfProductzuzhuangDetailService.updateCmfProductzuzhuangDetail(cmfProductzuzhuangDetail));
    }

    /**
     * 删除产品组装明细
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:detail:remove')")
    @Log(title = "产品组装明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfProductzuzhuangDetailService.deleteCmfProductzuzhuangDetailByIds(ids));
    }
}
