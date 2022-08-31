package com.vrmlstudio.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.vrmlstudio.common.annotation.Excel;
import com.vrmlstudio.common.core.domain.BaseEntity;

/**
 * 系统帮助对象 cmf_systemhelp
 * 
 * @author VRer
 * @date 2022-04-18
 */
public class CmfSystemhelp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String systemhelpname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String text;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String integer;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setSystemhelpname(String systemhelpname) 
    {
        this.systemhelpname = systemhelpname;
    }

    public String getSystemhelpname() 
    {
        return systemhelpname;
    }
    public void setText(String text) 
    {
        this.text = text;
    }

    public String getText() 
    {
        return text;
    }
    public void setInteger(String integer) 
    {
        this.integer = integer;
    }

    public String getInteger() 
    {
        return integer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("systemhelpname", getSystemhelpname())
            .append("text", getText())
            .append("integer", getInteger())
            .toString();
    }
}
