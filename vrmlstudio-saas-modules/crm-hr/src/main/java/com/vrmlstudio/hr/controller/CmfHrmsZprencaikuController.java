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
import com.vrmlstudio.hr.domain.CmfHrmsZprencaiku;
import com.vrmlstudio.hr.service.ICmfHrmsZprencaikuService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 招聘人才库Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-hr/zprencaiku")
public class CmfHrmsZprencaikuController extends BaseController
{
    @Autowired
    private ICmfHrmsZprencaikuService cmfHrmsZprencaikuService;

    /**
     * 查询招聘人才库列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zprencaiku:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfHrmsZprencaiku cmfHrmsZprencaiku)
    {
        startPage();
        List<CmfHrmsZprencaiku> list = cmfHrmsZprencaikuService.selectCmfHrmsZprencaikuList(cmfHrmsZprencaiku);
        return getDataTable(list);
    }

    /**
     * 导出招聘人才库列表
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zprencaiku:export')")
    @Log(title = "招聘人才库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfHrmsZprencaiku cmfHrmsZprencaiku)
    {
        List<CmfHrmsZprencaiku> list = cmfHrmsZprencaikuService.selectCmfHrmsZprencaikuList(cmfHrmsZprencaiku);
        ExcelUtil<CmfHrmsZprencaiku> util = new ExcelUtil<CmfHrmsZprencaiku>(CmfHrmsZprencaiku.class);
        util.exportExcel(response, list, "招聘人才库数据");
    }

    /**
     * 获取招聘人才库详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zprencaiku:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfHrmsZprencaikuService.selectCmfHrmsZprencaikuById(id));
    }

    /**
     * 新增招聘人才库
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zprencaiku:add')")
    @Log(title = "招聘人才库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfHrmsZprencaiku cmfHrmsZprencaiku)
    {
        return toAjax(cmfHrmsZprencaikuService.insertCmfHrmsZprencaiku(cmfHrmsZprencaiku));
    }

    /**
     * 修改招聘人才库
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zprencaiku:edit')")
    @Log(title = "招聘人才库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfHrmsZprencaiku cmfHrmsZprencaiku)
    {
        return toAjax(cmfHrmsZprencaikuService.updateCmfHrmsZprencaiku(cmfHrmsZprencaiku));
    }

    /**
     * 删除招聘人才库
     */
    @PreAuthorize("@ss.hasPermi('crm-hr:zprencaiku:remove')")
    @Log(title = "招聘人才库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfHrmsZprencaikuService.deleteCmfHrmsZprencaikuByIds(ids));
    }
}
