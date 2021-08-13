package com.ruoyi.publicBusManage.departureConfirm.domain;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/11 17:42
 * @desc:before SET
 */
public class TDriverym {
    private String driverName;
    private String busNo;

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
        return "TDriverym{" +
                "driverName='" + driverName + '\'' +
                ", busNo='" + busNo + '\'' +
                '}';
    }
}
