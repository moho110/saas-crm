package com.vrmlstudio.political.mapper;

import java.util.List;
import com.vrmlstudio.political.domain.CmfDormArea;

/**
 * 宿舍区域Mapper接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface CmfDormAreaMapper 
{
    /**
     * 查询宿舍区域
     * 
     * @param id 宿舍区域主键
     * @return 宿舍区域
     */
    public CmfDormArea selectCmfDormAreaById(Integer id);

    /**
     * 查询宿舍区域列表
     * 
     * @param cmfDormArea 宿舍区域
     * @return 宿舍区域集合
     */
    public List<CmfDormArea> selectCmfDormAreaList(CmfDormArea cmfDormArea);

    /**
     * 新增宿舍区域
     * 
     * @param cmfDormArea 宿舍区域
     * @return 结果
     */
    public int insertCmfDormArea(CmfDormArea cmfDormArea);

    /**
     * 修改宿舍区域
     * 
     * @param cmfDormArea 宿舍区域
     * @return 结果
     */
    public int updateCmfDormArea(CmfDormArea cmfDormArea);

    /**
     * 删除宿舍区域
     * 
     * @param id 宿舍区域主键
     * @return 结果
     */
    public int deleteCmfDormAreaById(Integer id);

    /**
     * 批量删除宿舍区域
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfDormAreaByIds(Integer[] ids);
}
