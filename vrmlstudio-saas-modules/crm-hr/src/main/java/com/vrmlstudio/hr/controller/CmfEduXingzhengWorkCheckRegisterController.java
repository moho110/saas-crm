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
import com.vrmlstudio.hr.domain.CmfEduXingzhengWorkCheckRegister;
import com.vrmlstudio.hr.service.ICmfEduXingzhengWorkCheckRegisterService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 行政人员工作考核登记表Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/workcheckregister")
public class CmfEduXingzhengWorkCheckRegisterController extends BaseController
{
    @Autowired
    private ICmfEduXingzhengWorkCheckRegisterService cmfEduXingzhengWorkCheckRegisterService;

    /**
     * 查询行政人员工作考核登记表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workcheckregister:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfEduXingzhengWorkCheckRegister cmfEduXingzhengWorkCheckRegister)
    {
        startPage();
        List<CmfEduXingzhengWorkCheckRegister> list = cmfEduXingzhengWorkCheckRegisterService.selectCmfEduXingzhengWorkCheckRegisterList(cmfEduXingzhengWorkCheckRegister);
        return getDataTable(list);
    }

    /**
     * 导出行政人员工作考核登记表列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workcheckregister:export')")
    @Log(title = "行政人员工作考核登记表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfEduXingzhengWorkCheckRegister cmfEduXingzhengWorkCheckRegister)
    {
        List<CmfEduXingzhengWorkCheckRegister> list = cmfEduXingzhengWorkCheckRegisterService.selectCmfEduXingzhengWorkCheckRegisterList(cmfEduXingzhengWorkCheckRegister);
        ExcelUtil<CmfEduXingzhengWorkCheckRegister> util = new ExcelUtil<CmfEduXingzhengWorkCheckRegister>(CmfEduXingzhengWorkCheckRegister.class);
        util.exportExcel(response, list, "行政人员工作考核登记表数据");
    }

    /**
     * 获取行政人员工作考核登记表详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workcheckregister:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfEduXingzhengWorkCheckRegisterService.selectCmfEduXingzhengWorkCheckRegisterById(id));
    }

    /**
     * 新增行政人员工作考核登记表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workcheckregister:add')")
    @Log(title = "行政人员工作考核登记表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfEduXingzhengWorkCheckRegister cmfEduXingzhengWorkCheckRegister)
    {
        return toAjax(cmfEduXingzhengWorkCheckRegisterService.insertCmfEduXingzhengWorkCheckRegister(cmfEduXingzhengWorkCheckRegister));
    }

    /**
     * 修改行政人员工作考核登记表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workcheckregister:edit')")
    @Log(title = "行政人员工作考核登记表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfEduXingzhengWorkCheckRegister cmfEduXingzhengWorkCheckRegister)
    {
        return toAjax(cmfEduXingzhengWorkCheckRegisterService.updateCmfEduXingzhengWorkCheckRegister(cmfEduXingzhengWorkCheckRegister));
    }

    /**
     * 删除行政人员工作考核登记表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:workcheckregister:remove')")
    @Log(title = "行政人员工作考核登记表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfEduXingzhengWorkCheckRegisterService.deleteCmfEduXingzhengWorkCheckRegisterByIds(ids));
    }
}
