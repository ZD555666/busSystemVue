package com.ruoyi.road.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 线路发车时刻配置对象 t_schedule
 * 
 * @author ruoyi
 * @date 2021-08-10
 */
public class TSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 时刻id */
    private Integer scheduleId;

    /** 公交车x路 */
    @Excel(name = "公交车x路")
    private String busNo;

    /** 各个时间段发车时间 */
    @Excel(name = "各个时间段发车时间")
    private Integer timeInterval;

    /** 发车时间段(前) */
    @Excel(name = "发车时间段(前)")
    private Integer starTime;

    /** 发车时间段(后) */
    @Excel(name = "发车时间段(后)")
    private Integer endTime;

    /** 车辆状态 */
    private String busState;

    public void setScheduleId(Integer scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Integer getScheduleId() 
    {
        return scheduleId;
    }
    public void setBusNo(String busNo) 
    {
        this.busNo = busNo;
    }

    public String getBusNo() 
    {
        return busNo;
    }
    public void setTimeInterval(Integer timeInterval) 
    {
        this.timeInterval = timeInterval;
    }

    public Integer getTimeInterval() 
    {
        return timeInterval;
    }
    public void setStarTime(Integer starTime) 
    {
        this.starTime = starTime;
    }

    public Integer getStarTime() 
    {
        return starTime;
    }
    public void setEndTime(Integer endTime) 
    {
        this.endTime = endTime;
    }

    public Integer getEndTime() 
    {
        return endTime;
    }
    public void setBusState(String busState) 
    {
        this.busState = busState;
    }

    public String getBusState() 
    {
        return busState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleId", getScheduleId())
            .append("busNo", getBusNo())
            .append("timeInterval", getTimeInterval())
            .append("starTime", getStarTime())
            .append("endTime", getEndTime())
            .append("busState", getBusState())
            .toString();
    }
}
