package com.ruoyi.publicBusManage.quantifies.domain;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/12 19:15
 * @desc:before SET
 */
public class TDriverTime {
    private String driverWorkTime;
    private String atWorkPunch;
    private String offDutyPunch;
    private String goBusPunch;

    public String getDriverWorkTime() {
        return driverWorkTime;
    }

    public void setDriverWorkTime(String driverWorkTime) {
        this.driverWorkTime = driverWorkTime;
    }

    public String getAtWorkPunch() {
        return atWorkPunch;
    }

    public void setAtWorkPunch(String atWorkPunch) {
        this.atWorkPunch = atWorkPunch;
    }

    public String getOffDutyPunch() {
        return offDutyPunch;
    }

    public void setOffDutyPunch(String offDutyPunch) {
        this.offDutyPunch = offDutyPunch;
    }

    public String getGoBusPunch() {
        return goBusPunch;
    }

    public void setGoBusPunch(String goBusPunch) {
        this.goBusPunch = goBusPunch;
    }

    @Override
    public String toString() {
        return "TDriverTime{" +
                "driverWorkTime='" + driverWorkTime + '\'' +
                ", atWorkPunch='" + atWorkPunch + '\'' +
                ", offDutyPunch='" + offDutyPunch + '\'' +
                ", goBusPunch='" + goBusPunch + '\'' +
                '}';
    }
}
