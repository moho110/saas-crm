package com.vrmlstudio.basicdata.mapper;

import java.util.List;
import com.vrmlstudio.basicdata.domain.CmfCrmJieduan;

/**
 * 阶段Mapper接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface CmfCrmJieduanMapper 
{
    /**
     * 查询阶段
     * 
     * @param id 阶段主键
     * @return 阶段
     */
    public CmfCrmJieduan selectCmfCrmJieduanById(Integer id);

    /**
     * 查询阶段列表
     * 
     * @param cmfCrmJieduan 阶段
     * @return 阶段集合
     */
    public List<CmfCrmJieduan> selectCmfCrmJieduanList(CmfCrmJieduan cmfCrmJieduan);

    /**
     * 新增阶段
     * 
     * @param cmfCrmJieduan 阶段
     * @return 结果
     */
    public int insertCmfCrmJieduan(CmfCrmJieduan cmfCrmJieduan);

    /**
     * 修改阶段
     * 
     * @param cmfCrmJieduan 阶段
     * @return 结果
     */
    public int updateCmfCrmJieduan(CmfCrmJieduan cmfCrmJieduan);

    /**
     * 删除阶段
     * 
     * @param id 阶段主键
     * @return 结果
     */
    public int deleteCmfCrmJieduanById(Integer id);

    /**
     * 批量删除阶段
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfCrmJieduanByIds(Integer[] ids);
}
