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
import com.vrmlstudio.basicdata.domain.CmfDictDanyuanyongtu;
import com.vrmlstudio.basicdata.service.ICmfDictDanyuanyongtuService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 单元用途Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-basicdata/danyuanyongtu")
public class CmfDictDanyuanyongtuController extends BaseController
{
    @Autowired
    private ICmfDictDanyuanyongtuService cmfDictDanyuanyongtuService;

    /**
     * 查询单元用途列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanyongtu:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfDictDanyuanyongtu cmfDictDanyuanyongtu)
    {
        startPage();
        List<CmfDictDanyuanyongtu> list = cmfDictDanyuanyongtuService.selectCmfDictDanyuanyongtuList(cmfDictDanyuanyongtu);
        return getDataTable(list);
    }

    /**
     * 导出单元用途列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanyongtu:export')")
    @Log(title = "单元用途", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfDictDanyuanyongtu cmfDictDanyuanyongtu)
    {
        List<CmfDictDanyuanyongtu> list = cmfDictDanyuanyongtuService.selectCmfDictDanyuanyongtuList(cmfDictDanyuanyongtu);
        ExcelUtil<CmfDictDanyuanyongtu> util = new ExcelUtil<CmfDictDanyuanyongtu>(CmfDictDanyuanyongtu.class);
        util.exportExcel(response, list, "单元用途数据");
    }

    /**
     * 获取单元用途详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanyongtu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfDictDanyuanyongtuService.selectCmfDictDanyuanyongtuById(id));
    }

    /**
     * 新增单元用途
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanyongtu:add')")
    @Log(title = "单元用途", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfDictDanyuanyongtu cmfDictDanyuanyongtu)
    {
        return toAjax(cmfDictDanyuanyongtuService.insertCmfDictDanyuanyongtu(cmfDictDanyuanyongtu));
    }

    /**
     * 修改单元用途
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanyongtu:edit')")
    @Log(title = "单元用途", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfDictDanyuanyongtu cmfDictDanyuanyongtu)
    {
        return toAjax(cmfDictDanyuanyongtuService.updateCmfDictDanyuanyongtu(cmfDictDanyuanyongtu));
    }

    /**
     * 删除单元用途
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanyongtu:remove')")
    @Log(title = "单元用途", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfDictDanyuanyongtuService.deleteCmfDictDanyuanyongtuByIds(ids));
    }
}
