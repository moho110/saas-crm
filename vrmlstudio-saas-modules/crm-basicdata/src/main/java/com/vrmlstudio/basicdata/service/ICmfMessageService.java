package com.vrmlstudio.basicdata.service;

import java.util.List;
import com.vrmlstudio.basicdata.domain.CmfMessage;

/**
 * 消息明细Service接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface ICmfMessageService 
{
    /**
     * 查询消息明细
     * 
     * @param id 消息明细主键
     * @return 消息明细
     */
    public CmfMessage selectCmfMessageById(Integer id);

    /**
     * 查询消息明细列表
     * 
     * @param cmfMessage 消息明细
     * @return 消息明细集合
     */
    public List<CmfMessage> selectCmfMessageList(CmfMessage cmfMessage);

    /**
     * 新增消息明细
     * 
     * @param cmfMessage 消息明细
     * @return 结果
     */
    public int insertCmfMessage(CmfMessage cmfMessage);

    /**
     * 修改消息明细
     * 
     * @param cmfMessage 消息明细
     * @return 结果
     */
    public int updateCmfMessage(CmfMessage cmfMessage);

    /**
     * 批量删除消息明细
     * 
     * @param ids 需要删除的消息明细主键集合
     * @return 结果
     */
    public int deleteCmfMessageByIds(Integer[] ids);

    /**
     * 删除消息明细信息
     * 
     * @param id 消息明细主键
     * @return 结果
     */
    public int deleteCmfMessageById(Integer id);
}
