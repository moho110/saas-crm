package com.vrmlstudio.mytable.service;

import java.util.List;
import com.vrmlstudio.mytable.domain.CmfDictNotify;

/**
 * 紧急程度Service接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface ICmfDictNotifyService 
{
    /**
     * 查询紧急程度
     * 
     * @param id 紧急程度主键
     * @return 紧急程度
     */
    public CmfDictNotify selectCmfDictNotifyById(Integer id);

    /**
     * 查询紧急程度列表
     * 
     * @param cmfDictNotify 紧急程度
     * @return 紧急程度集合
     */
    public List<CmfDictNotify> selectCmfDictNotifyList(CmfDictNotify cmfDictNotify);

    /**
     * 新增紧急程度
     * 
     * @param cmfDictNotify 紧急程度
     * @return 结果
     */
    public int insertCmfDictNotify(CmfDictNotify cmfDictNotify);

    /**
     * 修改紧急程度
     * 
     * @param cmfDictNotify 紧急程度
     * @return 结果
     */
    public int updateCmfDictNotify(CmfDictNotify cmfDictNotify);

    /**
     * 批量删除紧急程度
     * 
     * @param ids 需要删除的紧急程度主键集合
     * @return 结果
     */
    public int deleteCmfDictNotifyByIds(Integer[] ids);

    /**
     * 删除紧急程度信息
     * 
     * @param id 紧急程度主键
     * @return 结果
     */
    public int deleteCmfDictNotifyById(Integer id);
}
