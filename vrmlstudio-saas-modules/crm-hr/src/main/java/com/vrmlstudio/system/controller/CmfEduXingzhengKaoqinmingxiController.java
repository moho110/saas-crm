package com.vrmlstudio.system.controller;

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
import com.vrmlstudio.system.domain.CmfEduXingzhengKaoqinmingxi;
import com.vrmlstudio.system.service.ICmfEduXingzhengKaoqinmingxiService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 考勤明细Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/kaoqinmingxi")
public class CmfEduXingzhengKaoqinmingxiController extends BaseController
{
    @Autowired
    private ICmfEduXingzhengKaoqinmingxiService cmfEduXingzhengKaoqinmingxiService;

    /**
     * 查询考勤明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinmingxi:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduXingzhengKaoqinmingxi cmfEduXingzhengKaoqinmingxi)
    {
        startPage();
        List<CmfEduXingzhengKaoqinmingxi> list = cmfEduXingzhengKaoqinmingxiService.selectCmfEduXingzhengKaoqinmingxiList(cmfEduXingzhengKaoqinmingxi);
        return getDataTable(list);
    }

    /**
     * 导出考勤明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinmingxi:export')")
    @Log(title = "考勤明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduXingzhengKaoqinmingxi cmfEduXingzhengKaoqinmingxi)
    {
        List<CmfEduXingzhengKaoqinmingxi> list = cmfEduXingzhengKaoqinmingxiService.selectCmfEduXingzhengKaoqinmingxiList(cmfEduXingzhengKaoqinmingxi);
        ExcelUtil<CmfEduXingzhengKaoqinmingxi> util = new ExcelUtil<CmfEduXingzhengKaoqinmingxi>(CmfEduXingzhengKaoqinmingxi.class);
        util.exportExcel(response, list, "考勤明细数据");
    }

    /**
     * 获取考勤明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinmingxi:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduXingzhengKaoqinmingxiService.selectCmfEduXingzhengKaoqinmingxiById(id));
    }

    /**
     * 新增考勤明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinmingxi:add')")
    @Log(title = "考勤明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduXingzhengKaoqinmingxi cmfEduXingzhengKaoqinmingxi)
    {
        return toAjax(cmfEduXingzhengKaoqinmingxiService.insertCmfEduXingzhengKaoqinmingxi(cmfEduXingzhengKaoqinmingxi));
    }

    /**
     * 修改考勤明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinmingxi:edit')")
    @Log(title = "考勤明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduXingzhengKaoqinmingxi cmfEduXingzhengKaoqinmingxi)
    {
        return toAjax(cmfEduXingzhengKaoqinmingxiService.updateCmfEduXingzhengKaoqinmingxi(cmfEduXingzhengKaoqinmingxi));
    }

    /**
     * 删除考勤明细
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:kaoqinmingxi:remove')")
    @Log(title = "考勤明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduXingzhengKaoqinmingxiService.deleteCmfEduXingzhengKaoqinmingxiByIds(ids));
    }
}
