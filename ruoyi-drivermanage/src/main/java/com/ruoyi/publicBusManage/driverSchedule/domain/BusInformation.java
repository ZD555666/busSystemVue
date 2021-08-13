package com.ruoyi.publicBusManage.driverSchedule.domain;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/13 13:58
 * @desc:before SET
 */
public class BusInformation {
    private int busId;
    private String busNo;
    private String licensePlate;
    private int busParam;
    private int roadId;
    private String value;

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getBusParam() {
        return busParam;
    }

    public void setBusParam(int busParam) {
        this.busParam = busParam;
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BusInformation{" +
                "busId=" + busId +
                ", busNo='" + busNo + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", busParam=" + busParam +
                ", roadId=" + roadId +
                ", value='" + value + '\'' +
                '}';
    }
}
