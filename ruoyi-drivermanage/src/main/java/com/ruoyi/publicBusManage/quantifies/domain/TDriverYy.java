package com.ruoyi.publicBusManage.quantifies.domain;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/12 15:45
 * @desc:before SET
 */
public class TDriverYy {
    private int driverWorkId ;
    private int userid;
    private String  driverName;
    private String busNo;

    public int getDriverWorkId() {
        return driverWorkId;
    }

    public void setDriverWorkId(int driverWorkId) {
        this.driverWorkId = driverWorkId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    @Override
    public String toString() {
        return "TDriverYy{" +
                "driverWorkId=" + driverWorkId +
                ", userid=" + userid +
                ", driverName='" + driverName + '\'' +
                ", busNo='" + busNo + '\'' +
                '}';
    }
}
