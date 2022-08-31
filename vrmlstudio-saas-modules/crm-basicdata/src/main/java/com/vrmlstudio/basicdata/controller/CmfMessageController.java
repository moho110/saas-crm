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
import com.vrmlstudio.basicdata.domain.CmfMessage;
import com.vrmlstudio.basicdata.service.ICmfMessageService;
import com.vrmlstudio.common.utils.poi.ExcelUtil;
import com.vrmlstudio.common.core.page.TableDataInfo;

/**
 * 消息明细Controller
 * 
 * @author VRer
 * @date 2022-04-18
 */
@RestController
@RequestMapping("/crm-basicdata/message")
public class CmfMessageController extends BaseController
{
    @Autowired
    private ICmfMessageService cmfMessageService;

    /**
     * 查询消息明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmfMessage cmfMessage)
    {
        startPage();
        List<CmfMessage> list = cmfMessageService.selectCmfMessageList(cmfMessage);
        return getDataTable(list);
    }

    /**
     * 导出消息明细列表
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:message:export')")
    @Log(title = "消息明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmfMessage cmfMessage)
    {
        List<CmfMessage> list = cmfMessageService.selectCmfMessageList(cmfMessage);
        ExcelUtil<CmfMessage> util = new ExcelUtil<CmfMessage>(CmfMessage.class);
        util.exportExcel(response, list, "消息明细数据");
    }

    /**
     * 获取消息明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:message:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cmfMessageService.selectCmfMessageById(id));
    }

    /**
     * 新增消息明细
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:message:add')")
    @Log(title = "消息明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmfMessage cmfMessage)
    {
        return toAjax(cmfMessageService.insertCmfMessage(cmfMessage));
    }

    /**
     * 修改消息明细
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:message:edit')")
    @Log(title = "消息明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmfMessage cmfMessage)
    {
        return toAjax(cmfMessageService.updateCmfMessage(cmfMessage));
    }

    /**
     * 删除消息明细
     */
    @PreAuthorize("@ss.hasPermi('crm-basicdata:message:remove')")
    @Log(title = "消息明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cmfMessageService.deleteCmfMessageByIds(ids));
    }
}
