package com.vrmlstudio.store.service;

import java.util.List;
import com.vrmlstudio.store.domain.CmfStockoutmain;

/**
 * 仓库出库Service接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface ICmfStockoutmainService 
{
    /**
     * 查询仓库出库
     * 
     * @param id 仓库出库主键
     * @return 仓库出库
     */
    public CmfStockoutmain selectCmfStockoutmainById(Integer id);

    /**
     * 查询仓库出库列表
     * 
     * @param cmfStockoutmain 仓库出库
     * @return 仓库出库集合
     */
    public List<CmfStockoutmain> selectCmfStockoutmainList(CmfStockoutmain cmfStockoutmain);

    /**
     * 新增仓库出库
     * 
     * @param cmfStockoutmain 仓库出库
     * @return 结果
     */
    public int insertCmfStockoutmain(CmfStockoutmain cmfStockoutmain);

    /**
     * 修改仓库出库
     * 
     * @param cmfStockoutmain 仓库出库
     * @return 结果
     */
    public int updateCmfStockoutmain(CmfStockoutmain cmfStockoutmain);

    /**
     * 批量删除仓库出库
     * 
     * @param ids 需要删除的仓库出库主键集合
     * @return 结果
     */
    public int deleteCmfStockoutmainByIds(Integer[] ids);

    /**
     * 删除仓库出库信息
     * 
     * @param id 仓库出库主键
     * @return 结果
     */
    public int deleteCmfStockoutmainById(Integer id);
}
