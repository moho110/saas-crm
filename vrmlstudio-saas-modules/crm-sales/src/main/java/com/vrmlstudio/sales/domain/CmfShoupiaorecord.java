package com.vrmlstudio.sales.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.vrmlstudio.common.annotation.Excel;
import com.vrmlstudio.common.core.domain.BaseEntity;

/**
 * 收票记录对象 cmf_shoupiaorecord
 * 
 * @author VRer
 * @date 2022-04-18
 */
public class CmfShoupiaorecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long supplyid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long caigoubillid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String kaipiaoneirong;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long piaojutype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long piaojujine;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fapiaono;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date kaipiaodate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String qici;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String kaipiaoren;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ifhuikuan;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String huikuanid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String beizhu;
    private Date createtime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setSupplyid(Long supplyid) 
    {
        this.supplyid = supplyid;
    }

    public Long getSupplyid() 
    {
        return supplyid;
    }
    public void setCaigoubillid(Long caigoubillid) 
    {
        this.caigoubillid = caigoubillid;
    }

    public Long getCaigoubillid() 
    {
        return caigoubillid;
    }
    public void setKaipiaoneirong(String kaipiaoneirong) 
    {
        this.kaipiaoneirong = kaipiaoneirong;
    }

    public String getKaipiaoneirong() 
    {
        return kaipiaoneirong;
    }
    public void setPiaojutype(Long piaojutype) 
    {
        this.piaojutype = piaojutype;
    }

    public Long getPiaojutype() 
    {
        return piaojutype;
    }
    public void setPiaojujine(Long piaojujine) 
    {
        this.piaojujine = piaojujine;
    }

    public Long getPiaojujine() 
    {
        return piaojujine;
    }
    public void setFapiaono(String fapiaono) 
    {
        this.fapiaono = fapiaono;
    }

    public String getFapiaono() 
    {
        return fapiaono;
    }
    public void setKaipiaodate(Date kaipiaodate) 
    {
        this.kaipiaodate = kaipiaodate;
    }

    public Date getKaipiaodate() 
    {
        return kaipiaodate;
    }
    public void setQici(String qici) 
    {
        this.qici = qici;
    }

    public String getQici() 
    {
        return qici;
    }
    public void setKaipiaoren(String kaipiaoren) 
    {
        this.kaipiaoren = kaipiaoren;
    }

    public String getKaipiaoren() 
    {
        return kaipiaoren;
    }
    public void setIfhuikuan(String ifhuikuan) 
    {
        this.ifhuikuan = ifhuikuan;
    }

    public String getIfhuikuan() 
    {
        return ifhuikuan;
    }
    public void setHuikuanid(String huikuanid) 
    {
        this.huikuanid = huikuanid;
    }

    public String getHuikuanid() 
    {
        return huikuanid;
    }
    public void setBeizhu(String beizhu) 
    {
        this.beizhu = beizhu;
    }

    public String getBeizhu() 
    {
        return beizhu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("supplyid", getSupplyid())
            .append("caigoubillid", getCaigoubillid())
            .append("kaipiaoneirong", getKaipiaoneirong())
            .append("piaojutype", getPiaojutype())
            .append("piaojujine", getPiaojujine())
            .append("fapiaono", getFapiaono())
            .append("kaipiaodate", getKaipiaodate())
            .append("qici", getQici())
            .append("kaipiaoren", getKaipiaoren())
            .append("ifhuikuan", getIfhuikuan())
            .append("huikuanid", getHuikuanid())
            .append("beizhu", getBeizhu())
            .append("createtime", getCreatetime())
            .toString();
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
