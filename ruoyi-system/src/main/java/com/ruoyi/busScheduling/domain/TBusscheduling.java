package com.ruoyi.busScheduling.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * busscheduling对象 t_busscheduling
 * 
 * @author 林
 * @date 2021-08-15
 */
public class TBusscheduling extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long busid;

    /** x路公交 */
    @Excel(name = "x路公交")
    private String busno;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licenseplate;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startime;

    public String getBusparam() {
        return busparam;
    }

    public void setBusparam(String busparam) {
        this.busparam = busparam;
    }

    private String busparam;
    public void setBusid(Long busid) 
    {
        this.busid = busid;
    }

    public Long getBusid() 
    {
        return busid;
    }
    public void setBusno(String busno) 
    {
        this.busno = busno;
    }

    public String getBusno() 
    {
        return busno;
    }
    public void setLicenseplate(String licenseplate) 
    {
        this.licenseplate = licenseplate;
    }

    public String getLicenseplate() 
    {
        return licenseplate;
    }
    public void setStartime(String startime) 
    {
        this.startime = startime;
    }

    public String getStartime() 
    {
        return startime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("busid", getBusid())
            .append("busno", getBusno())
            .append("licenseplate", getLicenseplate())
            .append("startime", getStartime())
                .append("busparam", getBusparam())
            .toString();
    }
}
