package com.ruoyi.wx.wxBus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class Schedule {
    private int scheduleId;
    private String busNo;
    private int timeInterval;
    private int starTime;
    private int endTime;
    private String busState;
}
