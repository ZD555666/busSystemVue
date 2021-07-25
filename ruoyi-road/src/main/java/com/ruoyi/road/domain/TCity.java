package com.ruoyi.road.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 城市配置对象 t_city
 * 
 * @author ruoyi
 * @date 2021-07-24
 */
public class TCity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer cityid;

    /** 城市名称 */
    @Excel(name = "城市名称")
    private String cityname;

    public void setCityid(Integer cityid) 
    {
        this.cityid = cityid;
    }

    public Integer getCityid() 
    {
        return cityid;
    }
    public void setCityname(String cityname) 
    {
        this.cityname = cityname;
    }

    public String getCityname() 
    {
        return cityname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cityid", getCityid())
            .append("cityname", getCityname())
            .toString();
    }
}
