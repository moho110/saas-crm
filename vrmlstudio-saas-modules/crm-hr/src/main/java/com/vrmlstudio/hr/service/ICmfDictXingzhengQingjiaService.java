package com.vrmlstudio.hr.service;

import java.util.List;
import com.vrmlstudio.hr.domain.CmfDictXingzhengQingjia;

/**
 * 行政请假Service接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface ICmfDictXingzhengQingjiaService 
{
    /**
     * 查询行政请假
     * 
     * @param id 行政请假主键
     * @return 行政请假
     */
    public CmfDictXingzhengQingjia selectCmfDictXingzhengQingjiaById(Integer id);

    /**
     * 查询行政请假列表
     * 
     * @param cmfDictXingzhengQingjia 行政请假
     * @return 行政请假集合
     */
    public List<CmfDictXingzhengQingjia> selectCmfDictXingzhengQingjiaList(CmfDictXingzhengQingjia cmfDictXingzhengQingjia);

    /**
     * 新增行政请假
     * 
     * @param cmfDictXingzhengQingjia 行政请假
     * @return 结果
     */
    public int insertCmfDictXingzhengQingjia(CmfDictXingzhengQingjia cmfDictXingzhengQingjia);

    /**
     * 修改行政请假
     * 
     * @param cmfDictXingzhengQingjia 行政请假
     * @return 结果
     */
    public int updateCmfDictXingzhengQingjia(CmfDictXingzhengQingjia cmfDictXingzhengQingjia);

    /**
     * 批量删除行政请假
     * 
     * @param ids 需要删除的行政请假主键集合
     * @return 结果
     */
    public int deleteCmfDictXingzhengQingjiaByIds(Integer[] ids);

    /**
     * 删除行政请假信息
     * 
     * @param id 行政请假主键
     * @return 结果
     */
    public int deleteCmfDictXingzhengQingjiaById(Integer id);
}
