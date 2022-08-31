package com.vrmlstudio.sales.mapper;

import java.util.List;
import com.vrmlstudio.sales.domain.CmfShoupiaorecord;

/**
 * 收票记录Mapper接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface CmfShoupiaorecordMapper 
{
    /**
     * 查询收票记录
     * 
     * @param id 收票记录主键
     * @return 收票记录
     */
    public CmfShoupiaorecord selectCmfShoupiaorecordById(Integer id);

    /**
     * 查询收票记录列表
     * 
     * @param cmfShoupiaorecord 收票记录
     * @return 收票记录集合
     */
    public List<CmfShoupiaorecord> selectCmfShoupiaorecordList(CmfShoupiaorecord cmfShoupiaorecord);

    /**
     * 新增收票记录
     * 
     * @param cmfShoupiaorecord 收票记录
     * @return 结果
     */
    public int insertCmfShoupiaorecord(CmfShoupiaorecord cmfShoupiaorecord);

    /**
     * 修改收票记录
     * 
     * @param cmfShoupiaorecord 收票记录
     * @return 结果
     */
    public int updateCmfShoupiaorecord(CmfShoupiaorecord cmfShoupiaorecord);

    /**
     * 删除收票记录
     * 
     * @param id 收票记录主键
     * @return 结果
     */
    public int deleteCmfShoupiaorecordById(Integer id);

    /**
     * 批量删除收票记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfShoupiaorecordByIds(Integer[] ids);
}
