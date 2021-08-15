package com.ruoyi.wx.wxuser.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消费记录对象 t_wx_record
 * 
 * @author gjw
 * @date 2021-08-15
 */
public class TWxRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 余额记录表主键 */
    private Long rid;

    /** openid */
    @Excel(name = "openid")
    private String openid;

    /** 收支 0收入1支出 */
    @Excel(name = "收支 0收入1支出")
    private Long isadd;

    /** 记录金额 */
    @Excel(name = "记录金额")
    private Long record;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordtime;

    /** 余额 */
    @Excel(name = "余额")
    private Long restmoney;

    public void setRid(Long rid) 
    {
        this.rid = rid;
    }

    public Long getRid() 
    {
        return rid;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setIsadd(Long isadd) 
    {
        this.isadd = isadd;
    }

    public Long getIsadd() 
    {
        return isadd;
    }
    public void setRecord(Long record) 
    {
        this.record = record;
    }

    public Long getRecord() 
    {
        return record;
    }
    public void setRecordtime(Date recordtime) 
    {
        this.recordtime = recordtime;
    }

    public Date getRecordtime() 
    {
        return recordtime;
    }
    public void setRestmoney(Long restmoney) 
    {
        this.restmoney = restmoney;
    }

    public Long getRestmoney() 
    {
        return restmoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rid", getRid())
            .append("openid", getOpenid())
            .append("isadd", getIsadd())
            .append("record", getRecord())
            .append("recordtime", getRecordtime())
            .append("restmoney", getRestmoney())
            .toString();
    }
}
