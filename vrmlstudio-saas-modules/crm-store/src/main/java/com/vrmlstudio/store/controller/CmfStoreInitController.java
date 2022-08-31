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
import com.vrmlstudio.store.domain.CmfStoreInit;
import com.vrmlstudio.store.service.ICmfStoreInitService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 库存初始化Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-store/storeinit")
public class CmfStoreInitController extends BaseController
{
    @Autowired
    private ICmfStoreInitService cmfStoreInitService;

    /**
     * 查询库存初始化列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:storeinit:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfStoreInit cmfStoreInit)
    {
        startPage();
        List<CmfStoreInit> list = cmfStoreInitService.selectCmfStoreInitList(cmfStoreInit);
        return getDataTable(list);
    }

    /**
     * 导出库存初始化列表
     */
    @PreAuthorize("@ss.hasPermi('crm-store:storeinit:export')")
    @Log(title = "库存初始化", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfStoreInit cmfStoreInit)
    {
        List<CmfStoreInit> list = cmfStoreInitService.selectCmfStoreInitList(cmfStoreInit);
        ExcelUtil<CmfStoreInit> util = new ExcelUtil<CmfStoreInit>(CmfStoreInit.class);
        util.exportExcel(response, list, "库存初始化数据");
    }

    /**
     * 获取库存初始化详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-store:storeinit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfStoreInitService.selectCmfStoreInitById(id));
    }

    /**
     * 新增库存初始化
     */
    @PreAuthorize("@ss.hasPermi('crm-store:storeinit:add')")
    @Log(title = "库存初始化", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfStoreInit cmfStoreInit)
    {
        return toAjax(cmfStoreInitService.insertCmfStoreInit(cmfStoreInit));
    }

    /**
     * 修改库存初始化
     */
    @PreAuthorize("@ss.hasPermi('crm-store:storeinit:edit')")
    @Log(title = "库存初始化", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfStoreInit cmfStoreInit)
    {
        return toAjax(cmfStoreInitService.updateCmfStoreInit(cmfStoreInit));
    }

    /**
     * 删除库存初始化
     */
    @PreAuthorize("@ss.hasPermi('crm-store:storeinit:remove')")
    @Log(title = "库存初始化", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfStoreInitService.deleteCmfStoreInitByIds(ids));
    }
}
