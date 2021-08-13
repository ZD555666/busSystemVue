package com.ruoyi.publicBusManage.driverSalary.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * driverSalary对象 t_busdriversalary
 *
 * @author ruoyi
 * @date 2021-08-09
 */
public class TBusdriversalary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 薪资id */
    private Long salaryid;

    /** 司机id */
    @Excel(name = "司机id")
    private Long busdriverid;

    /** 司机工资 */
    @Excel(name = "司机工资")
    private Long driversalary;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String attr1;

    /** $column.columnComment */
    @Excel(name = "备用字段")
    private String attr2;

    public void setSalaryid(Long salaryid)
    {
        this.salaryid = salaryid;
    }

    public Long getSalaryid()
    {
        return salaryid;
    }
    public void setBusdriverid(Long busdriverid)
    {
        this.busdriverid = busdriverid;
    }

    public Long getBusdriverid()
    {
        return busdriverid;
    }
    public void setDriversalary(Long driversalary)
    {
        this.driversalary = driversalary;
    }

    public Long getDriversalary()
    {
        return driversalary;
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
            .append("salaryid", getSalaryid())
            .append("busdriverid", getBusdriverid())
            .append("driversalary", getDriversalary())
            .append("attr1", getAttr1())
            .append("attr2", getAttr2())
            .toString();
    }
}
