package com.vrmlstudio.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.vrmlstudio.common.annotation.Excel;
import com.vrmlstudio.common.core.domain.BaseEntity;

/**
 * 客户移除对象 cmf_crm_customer_move
 * 
 * @author VRer
 * @date 2022-04-14
 */
public class CmfCrmCustomerMove extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long clientid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String preuser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String nextuser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String operator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date operatetime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setClientid(Long clientid) 
    {
        this.clientid = clientid;
    }

    public Long getClientid() 
    {
        return clientid;
    }
    public void setPreuser(String preuser) 
    {
        this.preuser = preuser;
    }

    public String getPreuser() 
    {
        return preuser;
    }
    public void setNextuser(String nextuser) 
    {
        this.nextuser = nextuser;
    }

    public String getNextuser() 
    {
        return nextuser;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setOperatetime(Date operatetime) 
    {
        this.operatetime = operatetime;
    }

    public Date getOperatetime() 
    {
        return operatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("clientid", getClientid())
            .append("preuser", getPreuser())
            .append("nextuser", getNextuser())
            .append("operator", getOperator())
            .append("operatetime", getOperatetime())
            .toString();
    }
}
