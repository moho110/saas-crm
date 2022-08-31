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
import com.vrmlstudio.hr.domain.CmfDictZhengjianleixing;
import com.vrmlstudio.hr.service.ICmfDictZhengjianleixingService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 证件类型Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-hr/zhengjianleixing")
public class CmfDictZhengjianleixingController extends BaseController
{
    @Autowired
    private ICmfDictZhengjianleixingService cmfDictZhengjianleixingService;

    /**
     * 查询证件类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhengjianleixing:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfDictZhengjianleixing cmfDictZhengjianleixing)
    {
        startPage();
        List<CmfDictZhengjianleixing> list = cmfDictZhengjianleixingService.selectCmfDictZhengjianleixingList(cmfDictZhengjianleixing);
        return getDataTable(list);
    }

    /**
     * 导出证件类型列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhengjianleixing:export')")
    @Log(title = "证件类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfDictZhengjianleixing cmfDictZhengjianleixing)
    {
        List<CmfDictZhengjianleixing> list = cmfDictZhengjianleixingService.selectCmfDictZhengjianleixingList(cmfDictZhengjianleixing);
        ExcelUtil<CmfDictZhengjianleixing> util = new ExcelUtil<CmfDictZhengjianleixing>(CmfDictZhengjianleixing.class);
        util.exportExcel(response, list, "证件类型数据");
    }

    /**
     * 获取证件类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhengjianleixing:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfDictZhengjianleixingService.selectCmfDictZhengjianleixingById(id));
    }

    /**
     * 新增证件类型
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhengjianleixing:add')")
    @Log(title = "证件类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfDictZhengjianleixing cmfDictZhengjianleixing)
    {
        return toAjax(cmfDictZhengjianleixingService.insertCmfDictZhengjianleixing(cmfDictZhengjianleixing));
    }

    /**
     * 修改证件类型
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhengjianleixing:edit')")
    @Log(title = "证件类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfDictZhengjianleixing cmfDictZhengjianleixing)
    {
        return toAjax(cmfDictZhengjianleixingService.updateCmfDictZhengjianleixing(cmfDictZhengjianleixing));
    }

    /**
     * 删除证件类型
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zhengjianleixing:remove')")
    @Log(title = "证件类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfDictZhengjianleixingService.deleteCmfDictZhengjianleixingByIds(ids));
    }
}
