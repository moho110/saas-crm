package com.vrmlstudio.political.service;

import java.util.List;
import com.vrmlstudio.political.domain.CmfOfficeproductcangku;

/**
 * 办公用品仓库Service接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface ICmfOfficeproductcangkuService 
{
    /**
     * 查询办公用品仓库
     * 
     * @param id 办公用品仓库主键
     * @return 办公用品仓库
     */
    public CmfOfficeproductcangku selectCmfOfficeproductcangkuById(Integer id);

    /**
     * 查询办公用品仓库列表
     * 
     * @param cmfOfficeproductcangku 办公用品仓库
     * @return 办公用品仓库集合
     */
    public List<CmfOfficeproductcangku> selectCmfOfficeproductcangkuList(CmfOfficeproductcangku cmfOfficeproductcangku);

    /**
     * 新增办公用品仓库
     * 
     * @param cmfOfficeproductcangku 办公用品仓库
     * @return 结果
     */
    public int insertCmfOfficeproductcangku(CmfOfficeproductcangku cmfOfficeproductcangku);

    /**
     * 修改办公用品仓库
     * 
     * @param cmfOfficeproductcangku 办公用品仓库
     * @return 结果
     */
    public int updateCmfOfficeproductcangku(CmfOfficeproductcangku cmfOfficeproductcangku);

    /**
     * 批量删除办公用品仓库
     * 
     * @param ids 需要删除的办公用品仓库主键集合
     * @return 结果
     */
    public int deleteCmfOfficeproductcangkuByIds(Integer[] ids);

    /**
     * 删除办公用品仓库信息
     * 
     * @param id 办公用品仓库主键
     * @return 结果
     */
    public int deleteCmfOfficeproductcangkuById(Integer id);
}
