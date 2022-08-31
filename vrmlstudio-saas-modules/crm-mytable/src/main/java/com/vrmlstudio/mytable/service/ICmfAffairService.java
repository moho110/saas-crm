package com.vrmlstudio.mytable.service;

import java.util.List;
import com.vrmlstudio.mytable.domain.CmfAffair;

/**
 * 事务Service接口
 * 
 * @author VRer
 * @date 2022-04-14
 */
public interface ICmfAffairService 
{
    /**
     * 查询事务
     * 
     * @param id 事务主键
     * @return 事务
     */
    public CmfAffair selectCmfAffairById(Integer id);

    /**
     * 查询事务列表
     * 
     * @param cmfAffair 事务
     * @return 事务集合
     */
    public List<CmfAffair> selectCmfAffairList(CmfAffair cmfAffair);

    /**
     * 新增事务
     * 
     * @param cmfAffair 事务
     * @return 结果
     */
    public int insertCmfAffair(CmfAffair cmfAffair);

    /**
     * 修改事务
     * 
     * @param cmfAffair 事务
     * @return 结果
     */
    public int updateCmfAffair(CmfAffair cmfAffair);

    /**
     * 批量删除事务
     * 
     * @param ids 需要删除的事务主键集合
     * @return 结果
     */
    public int deleteCmfAffairByIds(Integer[] ids);

    /**
     * 删除事务信息
     * 
     * @param id 事务主键
     * @return 结果
     */
    public int deleteCmfAffairById(Integer id);
}
