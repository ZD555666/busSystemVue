package com.ruoyi.road.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 站点信息对象 t_stationinfo
 * 
 * @author ruoyi
 * @date 2021-07-25
 */
public class TStationinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 站点主键 */
    private Integer stationid;

    /** 城市id */
    @Excel(name = "城市id")
    private Integer cityid;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String stationname;

    /** 经度 */
    @Excel(name = "经度")
    private double xpoint;

    /** 纬度 */
    @Excel(name = "纬度")
    private double ypoint;

    /** 经纬度 */
    @Excel(name = "经纬度")
    private String point;

    /**
     * 导出城市单个属性
     */
    @Excel(name = "城市名称", targetAttr = "cityname", type = Excel.Type.EXPORT)
    private TCity city;

    public void setStationid(Integer stationid) 
    {
        this.stationid = stationid;
    }
    public Integer getStationid() 
    {
        return stationid;
    }

    public void setCityid(Integer cityid) 
    {
        this.cityid = cityid;
    }
    public Integer getCityid() 
    {
        return cityid;
    }

    public void setStationname(String stationname) 
    {
        this.stationname = stationname;
    }
    public String getStationname() 
    {
        return stationname;
    }

    public double getXpoint() {
        return xpoint;
    }

    public void setXpoint(double xpoint) {
        this.xpoint = xpoint;
    }

    public double getYpoint() {
        return ypoint;
    }

    public void setYpoint(double ypoint) {
        this.ypoint = ypoint;
    }

    public void setPoint(String point)
    {
        this.point = point;
    }
    public String getPoint(){return point;}


    public TCity getCity() {return city;}
    public void setCity(TCity city) {this.city = city;}

    @Override
    public String toString() {
        return "TStationinfo{" +
                "stationid=" + stationid +
                ", cityid=" + cityid +
                ", stationname='" + stationname + '\'' +
                ", xpoint=" + xpoint +
                ", ypoint=" + ypoint +
                ", point='" + point + '\'' +
                ", city=" + city +
                '}';
    }
}
