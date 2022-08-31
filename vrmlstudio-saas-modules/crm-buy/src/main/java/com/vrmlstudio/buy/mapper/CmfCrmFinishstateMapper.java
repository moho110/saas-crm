package com.vrmlstudio.buy.mapper;

import java.util.List;
import com.vrmlstudio.buy.domain.CmfCrmFinishstate;

/**
 * 完成状态Mapper接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface CmfCrmFinishstateMapper 
{
    /**
     * 查询完成状态
     * 
     * @param id 完成状态主键
     * @return 完成状态
     */
    public CmfCrmFinishstate selectCmfCrmFinishstateById(Integer id);

    /**
     * 查询完成状态列表
     * 
     * @param cmfCrmFinishstate 完成状态
     * @return 完成状态集合
     */
    public List<CmfCrmFinishstate> selectCmfCrmFinishstateList(CmfCrmFinishstate cmfCrmFinishstate);

    /**
     * 新增完成状态
     * 
     * @param cmfCrmFinishstate 完成状态
     * @return 结果
     */
    public int insertCmfCrmFinishstate(CmfCrmFinishstate cmfCrmFinishstate);

    /**
     * 修改完成状态
     * 
     * @param cmfCrmFinishstate 完成状态
     * @return 结果
     */
    public int updateCmfCrmFinishstate(CmfCrmFinishstate cmfCrmFinishstate);

    /**
     * 删除完成状态
     * 
     * @param id 完成状态主键
     * @return 结果
     */
    public int deleteCmfCrmFinishstateById(Integer id);

    /**
     * 批量删除完成状态
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfCrmFinishstateByIds(Integer[] ids);
}
