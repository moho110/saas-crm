package com.vrmlstudio.customer.service;

import java.util.List;
import com.vrmlstudio.customer.domain.CmfCrmShenhezhuangtai;

/**
 * 审核状态Service接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface ICmfCrmShenhezhuangtaiService 
{
    /**
     * 查询审核状态
     * 
     * @param id 审核状态主键
     * @return 审核状态
     */
    public CmfCrmShenhezhuangtai selectCmfCrmShenhezhuangtaiById(Integer id);

    /**
     * 查询审核状态列表
     * 
     * @param cmfCrmShenhezhuangtai 审核状态
     * @return 审核状态集合
     */
    public List<CmfCrmShenhezhuangtai> selectCmfCrmShenhezhuangtaiList(CmfCrmShenhezhuangtai cmfCrmShenhezhuangtai);

    /**
     * 新增审核状态
     * 
     * @param cmfCrmShenhezhuangtai 审核状态
     * @return 结果
     */
    public int insertCmfCrmShenhezhuangtai(CmfCrmShenhezhuangtai cmfCrmShenhezhuangtai);

    /**
     * 修改审核状态
     * 
     * @param cmfCrmShenhezhuangtai 审核状态
     * @return 结果
     */
    public int updateCmfCrmShenhezhuangtai(CmfCrmShenhezhuangtai cmfCrmShenhezhuangtai);

    /**
     * 批量删除审核状态
     * 
     * @param ids 需要删除的审核状态主键集合
     * @return 结果
     */
    public int deleteCmfCrmShenhezhuangtaiByIds(Integer[] ids);

    /**
     * 删除审核状态信息
     * 
     * @param id 审核状态主键
     * @return 结果
     */
    public int deleteCmfCrmShenhezhuangtaiById(Integer id);
}
