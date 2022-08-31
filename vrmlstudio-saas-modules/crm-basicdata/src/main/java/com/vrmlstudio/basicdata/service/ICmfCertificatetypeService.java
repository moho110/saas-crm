package com.vrmlstudio.basicdata.service;

import java.util.List;
import com.vrmlstudio.basicdata.domain.CmfCertificatetype;

/**
 * 认证类型Service接口
 * 
 * @author VRer
 * @date 2022-04-14
 */
public interface ICmfCertificatetypeService 
{
    /**
     * 查询认证类型
     * 
     * @param id 认证类型主键
     * @return 认证类型
     */
    public CmfCertificatetype selectCmfCertificatetypeById(Integer id);

    /**
     * 查询认证类型列表
     * 
     * @param cmfCertificatetype 认证类型
     * @return 认证类型集合
     */
    public List<CmfCertificatetype> selectCmfCertificatetypeList(CmfCertificatetype cmfCertificatetype);

    /**
     * 新增认证类型
     * 
     * @param cmfCertificatetype 认证类型
     * @return 结果
     */
    public int insertCmfCertificatetype(CmfCertificatetype cmfCertificatetype);

    /**
     * 修改认证类型
     * 
     * @param cmfCertificatetype 认证类型
     * @return 结果
     */
    public int updateCmfCertificatetype(CmfCertificatetype cmfCertificatetype);

    /**
     * 批量删除认证类型
     * 
     * @param ids 需要删除的认证类型主键集合
     * @return 结果
     */
    public int deleteCmfCertificatetypeByIds(Integer[] ids);

    /**
     * 删除认证类型信息
     * 
     * @param id 认证类型主键
     * @return 结果
     */
    public int deleteCmfCertificatetypeById(Integer id);
}
