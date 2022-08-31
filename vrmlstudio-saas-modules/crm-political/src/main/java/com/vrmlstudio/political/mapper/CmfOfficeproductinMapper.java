package com.vrmlstudio.political.mapper;

import java.util.List;
import com.vrmlstudio.political.domain.CmfOfficeproductin;

/**
 * 办公用品入库Mapper接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface CmfOfficeproductinMapper 
{
    /**
     * 查询办公用品入库
     * 
     * @param id 办公用品入库主键
     * @return 办公用品入库
     */
    public CmfOfficeproductin selectCmfOfficeproductinById(Integer id);

    /**
     * 查询办公用品入库列表
     * 
     * @param cmfOfficeproductin 办公用品入库
     * @return 办公用品入库集合
     */
    public List<CmfOfficeproductin> selectCmfOfficeproductinList(CmfOfficeproductin cmfOfficeproductin);

    /**
     * 新增办公用品入库
     * 
     * @param cmfOfficeproductin 办公用品入库
     * @return 结果
     */
    public int insertCmfOfficeproductin(CmfOfficeproductin cmfOfficeproductin);

    /**
     * 修改办公用品入库
     * 
     * @param cmfOfficeproductin 办公用品入库
     * @return 结果
     */
    public int updateCmfOfficeproductin(CmfOfficeproductin cmfOfficeproductin);

    /**
     * 删除办公用品入库
     * 
     * @param id 办公用品入库主键
     * @return 结果
     */
    public int deleteCmfOfficeproductinById(Integer id);

    /**
     * 批量删除办公用品入库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfOfficeproductinByIds(Integer[] ids);
}
