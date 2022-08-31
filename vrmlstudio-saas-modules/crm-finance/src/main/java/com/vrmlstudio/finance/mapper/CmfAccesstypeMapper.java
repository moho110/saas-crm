package com.vrmlstudio.finance.mapper;

import java.util.List;
import com.vrmlstudio.finance.domain.CmfAccesstype;

/**
 * 帐户操作类型Mapper接口
 * 
 * @author VRer
 * @date 2022-04-13
 */
public interface CmfAccesstypeMapper 
{
    /**
     * 查询帐户操作类型
     * 
     * @param id 帐户操作类型主键
     * @return 帐户操作类型
     */
    public CmfAccesstype selectCmfAccesstypeById(Integer id);

    /**
     * 查询帐户操作类型列表
     * 
     * @param cmfAccesstype 帐户操作类型
     * @return 帐户操作类型集合
     */
    public List<CmfAccesstype> selectCmfAccesstypeList(CmfAccesstype cmfAccesstype);

    /**
     * 新增帐户操作类型
     * 
     * @param cmfAccesstype 帐户操作类型
     * @return 结果
     */
    public int insertCmfAccesstype(CmfAccesstype cmfAccesstype);

    /**
     * 修改帐户操作类型
     * 
     * @param cmfAccesstype 帐户操作类型
     * @return 结果
     */
    public int updateCmfAccesstype(CmfAccesstype cmfAccesstype);

    /**
     * 删除帐户操作类型
     * 
     * @param id 帐户操作类型主键
     * @return 结果
     */
    public int deleteCmfAccesstypeById(Integer id);

    /**
     * 批量删除帐户操作类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfAccesstypeByIds(Integer[] ids);
}
