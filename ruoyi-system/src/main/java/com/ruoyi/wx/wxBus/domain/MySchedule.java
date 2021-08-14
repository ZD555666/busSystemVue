package com.ruoyi.wx.wxBus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/10 17:54
 * @desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MySchedule implements Serializable{
    private int scheduleId;
    private String busNo;
    private int timeInterval;
    private int starTime;
    private int endTime;
    private String busState;

}
