package com.ruoyi.publicBusManage.busFaultReport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆维修上报对象 t_repair
 *
 * @author ym
 * @date 2021-08-09
 */
public class TbusFaultReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维修id主键 */
    private Long repairid;

    /** 车牌 */
    @Excel(name = "车牌")
    private String licenseplate;

    /** 维修记录 */
    @Excel(name = "维修记录")
    private String repairrecord;

    /** $column.columnComment */
    @Excel(name = "维修记录")
    private String attr1;

    /** $column.columnComment */
    @Excel(name = "维修记录")
    private String attr2;

    public void setRepairid(Long repairid)
    {
        this.repairid = repairid;
    }

    public Long getRepairid()
    {
        return repairid;
    }
    public void setLicenseplate(String licenseplate)
    {
        this.licenseplate = licenseplate;
    }

    public String getLicenseplate()
    {
        return licenseplate;
    }
    public void setRepairrecord(String repairrecord)
    {
        this.repairrecord = repairrecord;
    }

    public String getRepairrecord()
    {
        return repairrecord;
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
            .append("repairid", getRepairid())
            .append("licenseplate", getLicenseplate())
            .append("repairrecord", getRepairrecord())
            .append("attr1", getAttr1())
            .append("attr2", getAttr2())
            .toString();
    }
}
