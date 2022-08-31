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
import com.vrmlstudio.hr.domain.CmfEduXingzhengQingjia;
import com.vrmlstudio.hr.service.ICmfEduXingzhengQingjiaService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 行政请假Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/xingzhengqingjia")
public class CmfEduXingzhengQingjiaController extends BaseController
{
    @Autowired
    private ICmfEduXingzhengQingjiaService cmfEduXingzhengQingjiaService;

    /**
     * 查询行政请假列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengqingjia:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduXingzhengQingjia cmfEduXingzhengQingjia)
    {
        startPage();
        List<CmfEduXingzhengQingjia> list = cmfEduXingzhengQingjiaService.selectCmfEduXingzhengQingjiaList(cmfEduXingzhengQingjia);
        return getDataTable(list);
    }

    /**
     * 导出行政请假列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengqingjia:export')")
    @Log(title = "行政请假", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduXingzhengQingjia cmfEduXingzhengQingjia)
    {
        List<CmfEduXingzhengQingjia> list = cmfEduXingzhengQingjiaService.selectCmfEduXingzhengQingjiaList(cmfEduXingzhengQingjia);
        ExcelUtil<CmfEduXingzhengQingjia> util = new ExcelUtil<CmfEduXingzhengQingjia>(CmfEduXingzhengQingjia.class);
        util.exportExcel(response, list, "行政请假数据");
    }

    /**
     * 获取行政请假详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengqingjia:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduXingzhengQingjiaService.selectCmfEduXingzhengQingjiaById(id));
    }

    /**
     * 新增行政请假
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengqingjia:add')")
    @Log(title = "行政请假", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduXingzhengQingjia cmfEduXingzhengQingjia)
    {
        return toAjax(cmfEduXingzhengQingjiaService.insertCmfEduXingzhengQingjia(cmfEduXingzhengQingjia));
    }

    /**
     * 修改行政请假
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengqingjia:edit')")
    @Log(title = "行政请假", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduXingzhengQingjia cmfEduXingzhengQingjia)
    {
        return toAjax(cmfEduXingzhengQingjiaService.updateCmfEduXingzhengQingjia(cmfEduXingzhengQingjia));
    }

    /**
     * 删除行政请假
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:xingzhengqingjia:remove')")
    @Log(title = "行政请假", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduXingzhengQingjiaService.deleteCmfEduXingzhengQingjiaByIds(ids));
    }
}
