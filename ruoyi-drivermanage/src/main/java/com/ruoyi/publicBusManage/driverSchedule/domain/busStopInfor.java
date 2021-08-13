package com.ruoyi.publicBusManage.driverSchedule.domain;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/5 1:25
 * @desc:before SET
 */
public class busStopInfor {
    private int stationId;
    private int cityId;
    private String stationName;
    private String value;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StationInformation{" +
                "stationId=" + stationId +
                ", cityId=" + cityId +
                ", stationName='" + stationName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
