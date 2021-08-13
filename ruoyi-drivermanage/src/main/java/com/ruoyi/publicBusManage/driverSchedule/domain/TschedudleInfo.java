package com.ruoyi.publicBusManage.driverSchedule.domain;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/2 17:04
 * @desc:before SET
 */
public class TschedudleInfo {
    private int scheduleId;
    private String busNo;
    private int timeInterval;
    private int startTime;
    private int endTime;
    private String busState;
    private String value;

    public int getScheduleId() {
        return scheduleId;
    }

    public String getBusNo() {
        return busNo;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBusState() {
        return busState;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setBusState(String busState) {
        this.busState = busState;
    }

    @Override
    public String toString() {
        return "TschedudleInfo{" +
                "scheduleId=" + scheduleId +
                ", busNo='" + busNo + '\'' +
                ", timeInterval='" + timeInterval + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", busState='" + busState + '\'' +
                '}';
    }
}
