package com.vrmlstudio.hr.service;

import java.util.List;
import com.vrmlstudio.hr.domain.CmfEduXingzhengTiaobanxianghu;

/**
 * 行政调班项目Service接口
 * 
 * @author VRer
 * @date 2022-04-15
 */
public interface ICmfEduXingzhengTiaobanxianghuService 
{
    /**
     * 查询行政调班项目
     * 
     * @param id 行政调班项目主键
     * @return 行政调班项目
     */
    public CmfEduXingzhengTiaobanxianghu selectCmfEduXingzhengTiaobanxianghuById(Integer id);

    /**
     * 查询行政调班项目列表
     * 
     * @param cmfEduXingzhengTiaobanxianghu 行政调班项目
     * @return 行政调班项目集合
     */
    public List<CmfEduXingzhengTiaobanxianghu> selectCmfEduXingzhengTiaobanxianghuList(CmfEduXingzhengTiaobanxianghu cmfEduXingzhengTiaobanxianghu);

    /**
     * 新增行政调班项目
     * 
     * @param cmfEduXingzhengTiaobanxianghu 行政调班项目
     * @return 结果
     */
    public int insertCmfEduXingzhengTiaobanxianghu(CmfEduXingzhengTiaobanxianghu cmfEduXingzhengTiaobanxianghu);

    /**
     * 修改行政调班项目
     * 
     * @param cmfEduXingzhengTiaobanxianghu 行政调班项目
     * @return 结果
     */
    public int updateCmfEduXingzhengTiaobanxianghu(CmfEduXingzhengTiaobanxianghu cmfEduXingzhengTiaobanxianghu);

    /**
     * 批量删除行政调班项目
     * 
     * @param ids 需要删除的行政调班项目主键集合
     * @return 结果
     */
    public int deleteCmfEduXingzhengTiaobanxianghuByIds(Integer[] ids);

    /**
     * 删除行政调班项目信息
     * 
     * @param id 行政调班项目主键
     * @return 结果
     */
    public int deleteCmfEduXingzhengTiaobanxianghuById(Integer id);
}
