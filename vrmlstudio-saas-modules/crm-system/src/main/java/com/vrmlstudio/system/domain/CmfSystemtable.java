package com.vrmlstudio.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.vrmlstudio.common.annotation.Excel;
import com.vrmlstudio.common.core.domain.BaseEntity;

/**
 * 系统表对象 cmf_systemtable
 * 
 * @author VRer
 * @date 2022-04-18
 */
public class CmfSystemtable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String systemtablename;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setSystemtablename(String systemtablename) 
    {
        this.systemtablename = systemtablename;
    }

    public String getSystemtablename() 
    {
        return systemtablename;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("systemtablename", getSystemtablename())
            .toString();
    }
}
