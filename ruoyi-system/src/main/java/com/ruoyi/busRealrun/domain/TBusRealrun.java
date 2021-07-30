package com.ruoyi.busRealrun.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * busRealrun对象 t_bus_realrun
 * 
 * @author
 * @date 2021-07-28
 */
public class TBusRealrun extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long realrunid;

    /** x路公交 */
    @Excel(name = "x路公交")
    private String busno;

    /** 车牌 */
    @Excel(name = "车牌")
    private String licenseplate;

    /** X坐标 */
    @Excel(name = "X坐标")
    private String realxpoint;

    /** Y坐标 */
    @Excel(name = "Y坐标")
    private String realypoint;

    /** 坐标 */
    @Excel(name = "坐标")
    private String realpoint;

    /** 当前站 */
    @Excel(name = "当前站")
    private String nowstation;

    /** 下一站 */
    @Excel(name = "下一站")
    private String nextstation;

    /** 路线状况 */
    @Excel(name = "路线状况")
    private String roadstate;

    /** 行驶速度 */
    @Excel(name = "行驶速度")
    private String speed;

    /** 车内人数 */
    @Excel(name = "车内人数")
    private String personnum;

    /** $column.columnComment */
    @Excel(name = "车内人数")
    private String demo1;

    /** $column.columnComment */
    @Excel(name = "车内人数")
    private String demo2;

    /** $column.columnComment */
    @Excel(name = "车内人数")
    private String demo3;

    public void setRealrunid(Long realrunid) 
    {
        this.realrunid = realrunid;
    }

    public Long getRealrunid() 
    {
        return realrunid;
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
    public void setRealxpoint(String realxpoint) 
    {
        this.realxpoint = realxpoint;
    }

    public String getRealxpoint() 
    {
        return realxpoint;
    }
    public void setRealypoint(String realypoint) 
    {
        this.realypoint = realypoint;
    }

    public String getRealypoint() 
    {
        return realypoint;
    }
    public void setRealpoint(String realpoint) 
    {
        this.realpoint = realpoint;
    }

    public String getRealpoint() 
    {
        return realpoint;
    }
    public void setNowstation(String nowstation) 
    {
        this.nowstation = nowstation;
    }

    public String getNowstation() 
    {
        return nowstation;
    }
    public void setNextstation(String nextstation) 
    {
        this.nextstation = nextstation;
    }

    public String getNextstation() 
    {
        return nextstation;
    }
    public void setRoadstate(String roadstate) 
    {
        this.roadstate = roadstate;
    }

    public String getRoadstate() 
    {
        return roadstate;
    }
    public void setSpeed(String speed) 
    {
        this.speed = speed;
    }

    public String getSpeed() 
    {
        return speed;
    }
    public void setPersonnum(String personnum) 
    {
        this.personnum = personnum;
    }

    public String getPersonnum() 
    {
        return personnum;
    }
    public void setDemo1(String demo1) 
    {
        this.demo1 = demo1;
    }

    public String getDemo1() 
    {
        return demo1;
    }
    public void setDemo2(String demo2) 
    {
        this.demo2 = demo2;
    }

    public String getDemo2() 
    {
        return demo2;
    }
    public void setDemo3(String demo3) 
    {
        this.demo3 = demo3;
    }

    public String getDemo3() 
    {
        return demo3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("realrunid", getRealrunid())
            .append("busno", getBusno())
            .append("licenseplate", getLicenseplate())
            .append("realxpoint", getRealxpoint())
            .append("realypoint", getRealypoint())
            .append("realpoint", getRealpoint())
            .append("nowstation", getNowstation())
            .append("nextstation", getNextstation())
            .append("roadstate", getRoadstate())
            .append("speed", getSpeed())
            .append("personnum", getPersonnum())
            .append("demo1", getDemo1())
            .append("demo2", getDemo2())
            .append("demo3", getDemo3())
            .toString();
    }
}
