package com.ruoyi.publicBusManage.departureConfirm.domain;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/11 15:52
 * @desc:before SET
 */
public class TScheduleym {
    private String busNo;
    private String startTime;
    private String busState;
    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getBusState() {
        return busState;
    }

    public void setBusState(String busState) {
        this.busState = busState;
    }

    @Override
    public String toString() {
        return "TScheduleym{" +
                "busNo='" + busNo + '\'' +
                ", startTime='" + startTime + '\'' +
                ", busState='" + busState + '\'' +
                '}';
    }
}
