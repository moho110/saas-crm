package com.vrmlstudio.basicdata.service;

import java.util.List;
import com.vrmlstudio.basicdata.domain.CmfDictDanyuanyongtu;

/**
 * 单元用途Service接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface ICmfDictDanyuanyongtuService 
{
    /**
     * 查询单元用途
     * 
     * @param id 单元用途主键
     * @return 单元用途
     */
    public CmfDictDanyuanyongtu selectCmfDictDanyuanyongtuById(Integer id);

    /**
     * 查询单元用途列表
     * 
     * @param cmfDictDanyuanyongtu 单元用途
     * @return 单元用途集合
     */
    public List<CmfDictDanyuanyongtu> selectCmfDictDanyuanyongtuList(CmfDictDanyuanyongtu cmfDictDanyuanyongtu);

    /**
     * 新增单元用途
     * 
     * @param cmfDictDanyuanyongtu 单元用途
     * @return 结果
     */
    public int insertCmfDictDanyuanyongtu(CmfDictDanyuanyongtu cmfDictDanyuanyongtu);

    /**
     * 修改单元用途
     * 
     * @param cmfDictDanyuanyongtu 单元用途
     * @return 结果
     */
    public int updateCmfDictDanyuanyongtu(CmfDictDanyuanyongtu cmfDictDanyuanyongtu);

    /**
     * 批量删除单元用途
     * 
     * @param ids 需要删除的单元用途主键集合
     * @return 结果
     */
    public int deleteCmfDictDanyuanyongtuByIds(Integer[] ids);

    /**
     * 删除单元用途信息
     * 
     * @param id 单元用途主键
     * @return 结果
     */
    public int deleteCmfDictDanyuanyongtuById(Integer id);
}
