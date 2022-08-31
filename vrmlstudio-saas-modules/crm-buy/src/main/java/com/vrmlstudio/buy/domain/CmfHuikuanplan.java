package com.vrmlstudio.buy.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.vrmlstudio.common.annotation.Excel;
import com.vrmlstudio.common.core.domain.BaseEntity;

/**
 * 汇款计划对象 cmf_huikuanplan
 * 
 * @author VRer
 * @date 2022-04-18
 */
public class CmfHuikuanplan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long customerid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long dingdanbillid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date plandate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long qici;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long jine;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createman;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ifpay;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long billtype;
    private Date createtime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCustomerid(Long customerid) 
    {
        this.customerid = customerid;
    }

    public Long getCustomerid() 
    {
        return customerid;
    }
    public void setDingdanbillid(Long dingdanbillid) 
    {
        this.dingdanbillid = dingdanbillid;
    }

    public Long getDingdanbillid() 
    {
        return dingdanbillid;
    }
    public void setPlandate(Date plandate) 
    {
        this.plandate = plandate;
    }

    public Date getPlandate() 
    {
        return plandate;
    }
    public void setQici(Long qici) 
    {
        this.qici = qici;
    }

    public Long getQici() 
    {
        return qici;
    }
    public void setJine(Long jine) 
    {
        this.jine = jine;
    }

    public Long getJine() 
    {
        return jine;
    }
    public void setCreateman(String createman) 
    {
        this.createman = createman;
    }

    public String getCreateman() 
    {
        return createman;
    }
    public void setIfpay(String ifpay) 
    {
        this.ifpay = ifpay;
    }

    public String getIfpay() 
    {
        return ifpay;
    }
    public void setBilltype(Long billtype) 
    {
        this.billtype = billtype;
    }

    public Long getBilltype() 
    {
        return billtype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerid", getCustomerid())
            .append("dingdanbillid", getDingdanbillid())
            .append("plandate", getPlandate())
            .append("qici", getQici())
            .append("jine", getJine())
            .append("createman", getCreateman())
            .append("createtime", getCreatetime())
            .append("ifpay", getIfpay())
            .append("billtype", getBilltype())
            .toString();
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
