package com.vrmlstudio.hr.mapper;

import java.util.List;
import com.vrmlstudio.hr.domain.CmfHrmsWorkexperience;

/**
 * 工作人员工作经验Mapper接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface CmfHrmsWorkexperienceMapper 
{
    /**
     * 查询工作人员工作经验
     * 
     * @param id 工作人员工作经验主键
     * @return 工作人员工作经验
     */
    public CmfHrmsWorkexperience selectCmfHrmsWorkexperienceById(Integer id);

    /**
     * 查询工作人员工作经验列表
     * 
     * @param cmfHrmsWorkexperience 工作人员工作经验
     * @return 工作人员工作经验集合
     */
    public List<CmfHrmsWorkexperience> selectCmfHrmsWorkexperienceList(CmfHrmsWorkexperience cmfHrmsWorkexperience);

    /**
     * 新增工作人员工作经验
     * 
     * @param cmfHrmsWorkexperience 工作人员工作经验
     * @return 结果
     */
    public int insertCmfHrmsWorkexperience(CmfHrmsWorkexperience cmfHrmsWorkexperience);

    /**
     * 修改工作人员工作经验
     * 
     * @param cmfHrmsWorkexperience 工作人员工作经验
     * @return 结果
     */
    public int updateCmfHrmsWorkexperience(CmfHrmsWorkexperience cmfHrmsWorkexperience);

    /**
     * 删除工作人员工作经验
     * 
     * @param id 工作人员工作经验主键
     * @return 结果
     */
    public int deleteCmfHrmsWorkexperienceById(Integer id);

    /**
     * 批量删除工作人员工作经验
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfHrmsWorkexperienceByIds(Integer[] ids);
}
