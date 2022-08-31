package com.vrmlstudio.basicdata.mapper;

import java.util.List;
import com.vrmlstudio.basicdata.domain.CmfCertificate;

/**
 * 认证Mapper接口
 * 
 * @author VRer
 * @date 2022-04-14
 */
public interface CmfCertificateMapper 
{
    /**
     * 查询认证
     * 
     * @param id 认证主键
     * @return 认证
     */
    public CmfCertificate selectCmfCertificateById(Integer id);

    /**
     * 查询认证列表
     * 
     * @param cmfCertificate 认证
     * @return 认证集合
     */
    public List<CmfCertificate> selectCmfCertificateList(CmfCertificate cmfCertificate);

    /**
     * 新增认证
     * 
     * @param cmfCertificate 认证
     * @return 结果
     */
    public int insertCmfCertificate(CmfCertificate cmfCertificate);

    /**
     * 修改认证
     * 
     * @param cmfCertificate 认证
     * @return 结果
     */
    public int updateCmfCertificate(CmfCertificate cmfCertificate);

    /**
     * 删除认证
     * 
     * @param id 认证主键
     * @return 结果
     */
    public int deleteCmfCertificateById(Integer id);

    /**
     * 批量删除认证
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmfCertificateByIds(Integer[] ids);
}
