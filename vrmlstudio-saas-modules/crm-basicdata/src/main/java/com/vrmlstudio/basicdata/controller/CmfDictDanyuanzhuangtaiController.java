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
import com.vrmlstudio.basicdata.domain.CmfDictDanyuanzhuangtai;
import com.vrmlstudio.basicdata.service.ICmfDictDanyuanzhuangtaiService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 单元状态Controller
 * 
 * @author VRer
 * @date 2022-04-15
 */
@RestController
@RequestMapping("/crm-basicdata/danyuanzhuangtai")
public class CmfDictDanyuanzhuangtaiController extends BaseController
{
    @Autowired
    private ICmfDictDanyuanzhuangtaiService cmfDictDanyuanzhuangtaiService;

    /**
     * 查询单元状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanzhuangtai:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfDictDanyuanzhuangtai cmfDictDanyuanzhuangtai)
    {
        startPage();
        List<CmfDictDanyuanzhuangtai> list = cmfDictDanyuanzhuangtaiService.selectCmfDictDanyuanzhuangtaiList(cmfDictDanyuanzhuangtai);
        return getDataTable(list);
    }

    /**
     * 导出单元状态列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanzhuangtai:export')")
    @Log(title = "单元状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfDictDanyuanzhuangtai cmfDictDanyuanzhuangtai)
    {
        List<CmfDictDanyuanzhuangtai> list = cmfDictDanyuanzhuangtaiService.selectCmfDictDanyuanzhuangtaiList(cmfDictDanyuanzhuangtai);
        ExcelUtil<CmfDictDanyuanzhuangtai> util = new ExcelUtil<CmfDictDanyuanzhuangtai>(CmfDictDanyuanzhuangtai.class);
        util.exportExcel(response, list, "单元状态数据");
    }

    /**
     * 获取单元状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanzhuangtai:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfDictDanyuanzhuangtaiService.selectCmfDictDanyuanzhuangtaiById(id));
    }

    /**
     * 新增单元状态
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanzhuangtai:add')")
    @Log(title = "单元状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfDictDanyuanzhuangtai cmfDictDanyuanzhuangtai)
    {
        return toAjax(cmfDictDanyuanzhuangtaiService.insertCmfDictDanyuanzhuangtai(cmfDictDanyuanzhuangtai));
    }

    /**
     * 修改单元状态
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanzhuangtai:edit')")
    @Log(title = "单元状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfDictDanyuanzhuangtai cmfDictDanyuanzhuangtai)
    {
        return toAjax(cmfDictDanyuanzhuangtaiService.updateCmfDictDanyuanzhuangtai(cmfDictDanyuanzhuangtai));
    }

    /**
     * 删除单元状态
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:danyuanzhuangtai:remove')")
    @Log(title = "单元状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfDictDanyuanzhuangtaiService.deleteCmfDictDanyuanzhuangtaiByIds(ids));
    }
}
