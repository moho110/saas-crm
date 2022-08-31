package com.vrmlstudio.customer.mapper;

import java.util.List;
import com.vrmlstudio.customer.domain.CmfCustomerarea;

/**
 * 客户区域Mapper接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface CmfCustomerareaMapper 
{
    /**
     * 查询客户区域
     * 
     * @param id 客户区域主键
     * @return 客户区域
     */
    public CmfCustomerarea selectCmfCustomerareaById(Integer id);

    /**
     * 查询客户区域列表
     * 
     * @param cmfCustomerarea 客户区域
     * @return 客户区域集合
     */
    public List<CmfCustomerarea> selectCmfCustomerareaList(CmfCustomerarea cmfCustomerarea);

    /**
     * 新增客户区域
     * 
     * @param cmfCustomerarea 客户区域
     * @return 结果
     */
    public int insertCmfCustomerarea(CmfCustomerarea cmfCustomerarea);

    /**
     * 修改客户区域
     * 
     * @param cmfCustomerarea 客户区域
     * @return 结果
     */
    public int updateCmfCustomerarea(CmfCustomerarea cmfCustomerarea);

    /**
     * 删除客户区域
     * 
     * @param id 客户区域主键
     * @return 结果
     */
    public int deleteCmfCustomerareaById(Integer id);

    /**
     * 批量删除客户区域
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfCustomerareaByIds(Integer[] ids);
}
