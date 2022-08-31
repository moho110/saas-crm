package com.vrmlstudio.political.service;

import java.util.List;
import com.vrmlstudio.political.domain.CmfOfficeproduct;

/**
 * 办公用品列表Service接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface ICmfOfficeproductService 
{
    /**
     * 查询办公用品列表
     * 
     * @param id 办公用品列表主键
     * @return 办公用品列表
     */
    public CmfOfficeproduct selectCmfOfficeproductById(Integer id);

    /**
     * 查询办公用品列表列表
     * 
     * @param cmfOfficeproduct 办公用品列表
     * @return 办公用品列表集合
     */
    public List<CmfOfficeproduct> selectCmfOfficeproductList(CmfOfficeproduct cmfOfficeproduct);

    /**
     * 新增办公用品列表
     * 
     * @param cmfOfficeproduct 办公用品列表
     * @return 结果
     */
    public int insertCmfOfficeproduct(CmfOfficeproduct cmfOfficeproduct);

    /**
     * 修改办公用品列表
     * 
     * @param cmfOfficeproduct 办公用品列表
     * @return 结果
     */
    public int updateCmfOfficeproduct(CmfOfficeproduct cmfOfficeproduct);

    /**
     * 批量删除办公用品列表
     * 
     * @param ids 需要删除的办公用品列表主键集合
     * @return 结果
     */
    public int deleteCmfOfficeproductByIds(Integer[] ids);

    /**
     * 删除办公用品列表信息
     * 
     * @param id 办公用品列表主键
     * @return 结果
     */
    public int deleteCmfOfficeproductById(Integer id);
}
