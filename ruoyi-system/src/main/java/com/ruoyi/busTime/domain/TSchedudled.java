package com.ruoyi.busTime.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公交车时刻对象 t_schedudle
 * 
 * @author ruoyi
 * @date 2021-07-26
 */
public class TSchedudled extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 时刻id */
    private Long scheduleid;

    /** 公交车x路 */
    @Excel(name = "公交车x路")
    private String busno;

    /** 时间间隔 */
    @Excel(name = "时间间隔")
    private String timeinterval;

    /** 发车时间 */
    @Excel(name = "发车时间")
    private String startime;

    public String getBusstate() {
        return busstate;
    }

    public void setBusstate(String busstate) {
        this.busstate = busstate;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    /** 到站时间 */
    @Excel(name = "到站时间")
    private String endtime;

    /** $column.columnComment */
    @Excel(name = "到站时间")
    private String busstate;

    /** $column.columnComment */
    @Excel(name = "到站时间")
    private String licenseplate;

    public void setScheduleid(Long scheduleid) 
    {
        this.scheduleid = scheduleid;
    }

    public Long getScheduleid() 
    {
        return scheduleid;
    }
    public void setBusno(String busno) 
    {
        this.busno = busno;
    }

    public String getBusno() 
    {
        return busno;
    }
    public void setTimeinterval(String timeinterval) 
    {
        this.timeinterval = timeinterval;
    }

    public String getTimeinterval() 
    {
        return timeinterval;
    }
    public void setStartime(String startime) 
    {
        this.startime = startime;
    }

    public String getStartime() 
    {
        return startime;
    }
    public void setEndtime(String endtime) 
    {
        this.endtime = endtime;
    }

    public String getEndtime() 
    {
        return endtime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleid", getScheduleid())
            .append("busno", getBusno())
            .append("timeinterval", getTimeinterval())
            .append("startime", getStartime())
            .append("endtime", getEndtime())
            .append("busstate", getBusstate())
            .append("licenseplate", getLicenseplate())
            .toString();
    }
}
