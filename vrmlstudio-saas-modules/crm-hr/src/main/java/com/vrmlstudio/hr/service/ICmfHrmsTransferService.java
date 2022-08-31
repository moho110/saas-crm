package com.vrmlstudio.hr.service;

import java.util.List;
import com.vrmlstudio.hr.domain.CmfHrmsTransfer;

/**
 * 转职明细Service接口
 * 
 * @author VRer
 * @date 2022-04-18
 */
public interface ICmfHrmsTransferService 
{
    /**
     * 查询转职明细
     * 
     * @param id 转职明细主键
     * @return 转职明细
     */
    public CmfHrmsTransfer selectCmfHrmsTransferById(Integer id);

    /**
     * 查询转职明细列表
     * 
     * @param cmfHrmsTransfer 转职明细
     * @return 转职明细集合
     */
    public List<CmfHrmsTransfer> selectCmfHrmsTransferList(CmfHrmsTransfer cmfHrmsTransfer);

    /**
     * 新增转职明细
     * 
     * @param cmfHrmsTransfer 转职明细
     * @return 结果
     */
    public int insertCmfHrmsTransfer(CmfHrmsTransfer cmfHrmsTransfer);

    /**
     * 修改转职明细
     * 
     * @param cmfHrmsTransfer 转职明细
     * @return 结果
     */
    public int updateCmfHrmsTransfer(CmfHrmsTransfer cmfHrmsTransfer);

    /**
     * 批量删除转职明细
     * 
     * @param ids 需要删除的转职明细主键集合
     * @return 结果
     */
    public int deleteCmfHrmsTransferByIds(Integer[] ids);

    /**
     * 删除转职明细信息
     * 
     * @param id 转职明细主键
     * @return 结果
     */
    public int deleteCmfHrmsTransferById(Integer id);
}
