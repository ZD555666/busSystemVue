package com.ruoyi.publicBusManage.quantifies.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 司机工作时间对象 t_driver_worktime
 *
 * @author ym
 * @date 2021-08-09
 */
public class TDriverWorktime extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 仅主键，无意义
     */
    private Long id;

    /**
     * sys_user的司机id
     */
    @Excel(name = "sys_user的司机id")
    private Long userId;

    /**
     * 当天日期, t_driverId 记录每个司机的工作量
     */
    @Excel(name = "当天日期, t_driverId 记录每个司机的工作量")
    private Long busdriverid;

    /**
     * 司机工作时长
     */
    @Excel(name = "司机工作时长")
    private String driverworktime;

    /**
     * 上班打卡
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上班打卡", width = 30, dateFormat = "yyyy-MM-dd")
    private String atworkpunch;

    /**
     * 下班打卡
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下班打卡", width = 30, dateFormat = "yyyy-MM-dd")
    private String offdutypunch;

    /**
     * 发车打卡
     */
    @Excel(name = "发车打卡")
    private String gobuspunch;

    /**
     * 备用字段
     */
    @Excel(name = "备用字段")
    private int sysUserId;

    /**
     * $column.columnComment
     */
    @Excel(name = "备用字段")
    private int tDriverId;

    private String userName;

    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setBusdriverid(Long busdriverid) {
        this.busdriverid = busdriverid;
    }

    public Long getBusdriverid() {
        return busdriverid;
    }

    public void setDriverworktime(String driverworktime) {
        this.driverworktime = driverworktime;
    }

    public String getDriverworktime() {
        return driverworktime;
    }

    public String getAtworkpunch() {
        return atworkpunch;
    }

    public void setAtworkpunch(String atworkpunch) {
        this.atworkpunch = atworkpunch;
    }

    public String getOffdutypunch() {
        return offdutypunch;
    }

    public void setOffdutypunch(String offdutypunch) {
        this.offdutypunch = offdutypunch;
    }

    public void setGobuspunch(String gobuspunch) {
        this.gobuspunch = gobuspunch;
    }

    public String getGobuspunch() {
        return gobuspunch;
    }

    public int getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(int sysUserId) {
        this.sysUserId = sysUserId;
    }

    public int gettDriverId() {
        return tDriverId;
    }

    public void settDriverId(int tDriverId) {
        this.tDriverId = tDriverId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("busdriverid", getBusdriverid())
                .append("driverworktime", getDriverworktime())
                .append("atworkpunch", getAtworkpunch())
                .append("offdutypunch", getOffdutypunch())
                .append("gobuspunch", getGobuspunch())
                .append("sysUserId", getSysUserId())
                .append("tDriverId", gettDriverId())
                .append("userName", getUserName())
                .append("password", getPassword())
                .toString();
    }
}
