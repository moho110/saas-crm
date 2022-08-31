package com.vrmlstudio.store.controller;

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
import com.vrmlstudio.store.domain.CmfStore;
import com.vrmlstudio.store.service.ICmfStoreService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 库存Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-store/store")
public class CmfStoreController extends BaseController
{
    @Autowired
    private ICmfStoreService cmfStoreService;

    /**
     * 查询库存列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:store:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfStore cmfStore)
    {
        startPage();
        List<CmfStore> list = cmfStoreService.selectCmfStoreList(cmfStore);
        return getDataTable(list);
    }

    /**
     * 导出库存列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:store:export')")
    @Log(title = "库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfStore cmfStore)
    {
        List<CmfStore> list = cmfStoreService.selectCmfStoreList(cmfStore);
        ExcelUtil<CmfStore> util = new ExcelUtil<CmfStore>(CmfStore.class);
        util.exportExcel(response, list, "库存数据");
    }

    /**
     * 获取库存详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-store:store:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfStoreService.selectCmfStoreById(id));
    }

    /**
     * 新增库存
     */
    @PreAuthorize("@ss.hasPermi('crm-store:store:add')")
    @Log(title = "库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfStore cmfStore)
    {
        return toAjax(cmfStoreService.insertCmfStore(cmfStore));
    }

    /**
     * 修改库存
     */
    @PreAuthorize("@ss.hasPermi('crm-store:store:edit')")
    @Log(title = "库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfStore cmfStore)
    {
        return toAjax(cmfStoreService.updateCmfStore(cmfStore));
    }

    /**
     * 删除库存
     */
    @PreAuthorize("@ss.hasPermi('crm-store:store:remove')")
    @Log(title = "库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfStoreService.deleteCmfStoreByIds(ids));
    }
}
