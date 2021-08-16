package com.ruoyi.wx.complaint.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投诉建议管理对象 t_wx_complaint
 * 
 * @author ruoyi
 * @date 2021-08-16
 */
public class TWxComplaint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long complaintid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String phonenumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String problem;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String imagepath;

    public void setComplaintid(Long complaintid) 
    {
        this.complaintid = complaintid;
    }

    public Long getComplaintid() 
    {
        return complaintid;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setProblem(String problem) 
    {
        this.problem = problem;
    }

    public String getProblem() 
    {
        return problem;
    }
    public void setImagepath(String imagepath) 
    {
        this.imagepath = imagepath;
    }

    public String getImagepath() 
    {
        return imagepath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("complaintid", getComplaintid())
            .append("phonenumber", getPhonenumber())
            .append("problem", getProblem())
            .append("imagepath", getImagepath())
            .toString();
    }
}
