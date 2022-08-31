package com.vrmlstudio.sales.mapper;

import java.util.List;
import com.vrmlstudio.sales.domain.CmfSellplanmain;

/**
 * 销售计划Mapper接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface CmfSellplanmainMapper 
{
    /**
     * 查询销售计划
     * 
     * @param id 销售计划主键
     * @return 销售计划
     */
    public CmfSellplanmain selectCmfSellplanmainById(Integer id);

    /**
     * 查询销售计划列表
     * 
     * @param cmfSellplanmain 销售计划
     * @return 销售计划集合
     */
    public List<CmfSellplanmain> selectCmfSellplanmainList(CmfSellplanmain cmfSellplanmain);

    /**
     * 新增销售计划
     * 
     * @param cmfSellplanmain 销售计划
     * @return 结果
     */
    public int insertCmfSellplanmain(CmfSellplanmain cmfSellplanmain);

    /**
     * 修改销售计划
     * 
     * @param cmfSellplanmain 销售计划
     * @return 结果
     */
    public int updateCmfSellplanmain(CmfSellplanmain cmfSellplanmain);

    /**
     * 删除销售计划
     * 
     * @param id 销售计划主键
     * @return 结果
     */
    public int deleteCmfSellplanmainById(Integer id);

    /**
     * 批量删除销售计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfSellplanmainByIds(Integer[] ids);
}
