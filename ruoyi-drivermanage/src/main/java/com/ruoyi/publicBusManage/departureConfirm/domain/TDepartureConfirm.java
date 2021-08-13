package com.ruoyi.publicBusManage.departureConfirm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公交车信息对象 t_businfo
 *
 * @author YM
 * @date 2021-08-10
 */
public class TDepartureConfirm extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 公交车id主键
     */
    private Long busid;

    /**
     * 公交车x路
     */
    @Excel(name = "公交车x路")
    private String busno;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String licenseplate;

    /**
     * 公交车参数
     */
    @Excel(name = "公交车参数")
    private Long busparam;

    /**
     * $column.columnComment
     */
    @Excel(name = "公交车参数")
    private Long roadid;

    /**
     * $column.columnComment
     */
    @Excel(name = "公交车参数")
    private String attr2;

    private String value;

    private String driverName;

    private String goBusTime;

    public void setBusid(Long busid) {
        this.busid = busid;
    }

    public Long getBusid() {
        return busid;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public String getBusno() {
        return busno;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setBusparam(Long busparam) {
        this.busparam = busparam;
    }

    public Long getBusparam() {
        return busparam;
    }

    public void setRoadid(Long roadid) {
        this.roadid = roadid;
    }

    public Long getRoadid() {
        return roadid;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr2() {
        return attr2;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getGoBusTime() {
        return goBusTime;
    }

    public void setGoBusTime(String goBusTime) {
        this.goBusTime = goBusTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("busid", getBusid())
                .append("busno", getBusno())
                .append("licenseplate", getLicenseplate())
                .append("busparam", getBusparam())
                .append("roadid", getRoadid())
                .append("attr2", getAttr2())
                .append("value", getValue())
                .append("driverName", getDriverName())
                .append("goBusTime", getGoBusTime())
                .toString();
    }
}
