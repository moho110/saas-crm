package com.vrmlstudio.mytable.mapper;

import java.util.List;
import com.vrmlstudio.mytable.domain.CmfWorkplanmain;

/**
 * 工作计划Mapper接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface CmfWorkplanmainMapper 
{
    /**
     * 查询工作计划
     * 
     * @param id 工作计划主键
     * @return 工作计划
     */
    public CmfWorkplanmain selectCmfWorkplanmainById(Integer id);

    /**
     * 查询工作计划列表
     * 
     * @param cmfWorkplanmain 工作计划
     * @return 工作计划集合
     */
    public List<CmfWorkplanmain> selectCmfWorkplanmainList(CmfWorkplanmain cmfWorkplanmain);

    /**
     * 新增工作计划
     * 
     * @param cmfWorkplanmain 工作计划
     * @return 结果
     */
    public int insertCmfWorkplanmain(CmfWorkplanmain cmfWorkplanmain);

    /**
     * 修改工作计划
     * 
     * @param cmfWorkplanmain 工作计划
     * @return 结果
     */
    public int updateCmfWorkplanmain(CmfWorkplanmain cmfWorkplanmain);

    /**
     * 删除工作计划
     * 
     * @param id 工作计划主键
     * @return 结果
     */
    public int deleteCmfWorkplanmainById(Integer id);

    /**
     * 批量删除工作计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfWorkplanmainByIds(Integer[] ids);
}
