package com.vrmlstudio.mytable.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.vrmlstudio.common.annotation.Excel;
import com.vrmlstudio.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 显示属性对象 cmf_crm_mytable_xssx
 * 
 * @author VRer
 * @date 2022-04-15
 */
public class CmfCrmMytableXssx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String displayattr;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String memo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String creator;
    private Date createtime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setDisplayattr(String displayattr) 
    {
        this.displayattr = displayattr;
    }

    public String getDisplayattr() 
    {
        return displayattr;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("displayattr", getDisplayattr())
            .append("memo", getMemo())
            .append("creator", getCreator())
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
