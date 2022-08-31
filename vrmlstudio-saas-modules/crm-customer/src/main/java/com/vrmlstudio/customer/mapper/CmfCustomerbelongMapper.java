package com.vrmlstudio.customer.mapper;

import java.util.List;
import com.vrmlstudio.customer.domain.CmfCustomerbelong;

/**
 * 客户所属Mapper接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface CmfCustomerbelongMapper 
{
    /**
     * 查询客户所属
     * 
     * @param id 客户所属主键
     * @return 客户所属
     */
    public CmfCustomerbelong selectCmfCustomerbelongById(Integer id);

    /**
     * 查询客户所属列表
     * 
     * @param cmfCustomerbelong 客户所属
     * @return 客户所属集合
     */
    public List<CmfCustomerbelong> selectCmfCustomerbelongList(CmfCustomerbelong cmfCustomerbelong);

    /**
     * 新增客户所属
     * 
     * @param cmfCustomerbelong 客户所属
     * @return 结果
     */
    public int insertCmfCustomerbelong(CmfCustomerbelong cmfCustomerbelong);

    /**
     * 修改客户所属
     * 
     * @param cmfCustomerbelong 客户所属
     * @return 结果
     */
    public int updateCmfCustomerbelong(CmfCustomerbelong cmfCustomerbelong);

    /**
     * 删除客户所属
     * 
     * @param id 客户所属主键
     * @return 结果
     */
    public int deleteCmfCustomerbelongById(Integer id);

    /**
     * 批量删除客户所属
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfCustomerbelongByIds(Integer[] ids);
}
