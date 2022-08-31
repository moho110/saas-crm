package com.vrmlstudio.basicdata.mapper;

import java.util.List;
import com.vrmlstudio.basicdata.domain.CmfSupplylever;

/**
 * 供应商等级Mapper接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface CmfSupplyleverMapper 
{
    /**
     * 查询供应商等级
     * 
     * @param id 供应商等级主键
     * @return 供应商等级
     */
    public CmfSupplylever selectCmfSupplyleverById(Integer id);

    /**
     * 查询供应商等级列表
     * 
     * @param cmfSupplylever 供应商等级
     * @return 供应商等级集合
     */
    public List<CmfSupplylever> selectCmfSupplyleverList(CmfSupplylever cmfSupplylever);

    /**
     * 新增供应商等级
     * 
     * @param cmfSupplylever 供应商等级
     * @return 结果
     */
    public int insertCmfSupplylever(CmfSupplylever cmfSupplylever);

    /**
     * 修改供应商等级
     * 
     * @param cmfSupplylever 供应商等级
     * @return 结果
     */
    public int updateCmfSupplylever(CmfSupplylever cmfSupplylever);

    /**
     * 删除供应商等级
     * 
     * @param id 供应商等级主键
     * @return 结果
     */
    public int deleteCmfSupplyleverById(Integer id);

    /**
     * 批量删除供应商等级
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfSupplyleverByIds(Integer[] ids);
}
