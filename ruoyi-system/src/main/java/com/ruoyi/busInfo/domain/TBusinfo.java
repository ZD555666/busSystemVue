package com.ruoyi.busInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆信息对象 t_businfo
 * 
 * @author lvy
 * @date 2021-07-23
 */
public class TBusinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公交车id主键 */
    private Long busid;

    /** 公交车x路 */
    @Excel(name = "公交车x路")
    private String busno;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licenseplate;

    /** 公交车参数 */
    @Excel(name = "公交车参数")
    private Long busparam;

    /** $column.columnComment */
    @Excel(name = "公交车参数")
    private String attr1;

    /** $column.columnComment */
    @Excel(name = "公交车参数")
    private String attr2;

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
    public void setBusparam(Long busparam) 
    {
        this.busparam = busparam;
    }

    public Long getBusparam() 
    {
        return busparam;
    }
    public void setAttr1(String attr1) 
    {
        this.attr1 = attr1;
    }

    public String getAttr1() 
    {
        return attr1;
    }
    public void setAttr2(String attr2) 
    {
        this.attr2 = attr2;
    }

    public String getAttr2() 
    {
        return attr2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("busid", getBusid())
            .append("busno", getBusno())
            .append("licenseplate", getLicenseplate())
            .append("busparam", getBusparam())
            .append("attr1", getAttr1())
            .append("attr2", getAttr2())
            .toString();
    }
}
