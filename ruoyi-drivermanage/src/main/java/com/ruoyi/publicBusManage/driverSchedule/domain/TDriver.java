package com.ruoyi.publicBusManage.driverSchedule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 司机排班对象 t_driver
 *
 * @author YM
 * @date 2021-07-28
 */
public class TDriver extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 司机工作主键
     */
    private Long driverworkid;

    /**
     * 工作班次
     */
    @Excel(name = "工作班次")
    private String workshift;

    /**
     * 上班地点
     */
    @Excel(name = "上班地点")
    private String workstation;

    /**
     * 公交车x路
     */
    @Excel(name = "公交车x路")
    private String busno;

    /**
     * sys_user的司机id
     */
    @Excel(name = "sys_user的司机id")
    private Long userId;

    /**
     * $column.columnComment
     */
    @Excel(name = "发车时刻表的Id")
    private int busSchedudleId;

    /**
     * $column.columnComment
     */
    @Excel(name = "司机的名字")
    private String driverName;

    /**
     * $column.columnComment
     */
    @Excel(name = "sys_user的司机id")
    private int stationInfoId;

    /**
     * 自己添加的值
     */
    @Excel(name = "value")
    private String value;

    private String departureTime;

    private String licensePlate;

    private String stationName;

    public void setDriverworkid(Long driverworkid) {
        this.driverworkid = driverworkid;
    }

    public Long getDriverworkid() {
        return driverworkid;
    }

    public void setWorkshift(String workshift) {
        this.workshift = workshift;
    }

    public String getWorkshift() {
        return workshift;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public String getWorkstation() {
        return workstation;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public String getBusno() {
        return busno;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public int getBusSchedudleId() {
        return busSchedudleId;
    }

    public void setBusSchedudleId(int busSchedudleId) {
        this.busSchedudleId = busSchedudleId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getStationInfoId() {
        return stationInfoId;
    }

    public void setStationInfoId(int stationInfoId) {
        this.stationInfoId = stationInfoId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("driverworkid", getDriverworkid())
                .append("workshift", getWorkshift())
                .append("workstation", getWorkstation())
                .append("busno", getBusno())
                .append("userId", getUserId())
                .append("busSchedudleId", getBusSchedudleId())
                .append("driverName", getDriverName())
                .append("stationInfoId", getStationInfoId())
                .append("value", getValue())
                .append("departureTime", getDepartureTime())
                .append("licensePlate", getLicensePlate())
                .append("stationName", getStationName())
                .toString();
    }
}

