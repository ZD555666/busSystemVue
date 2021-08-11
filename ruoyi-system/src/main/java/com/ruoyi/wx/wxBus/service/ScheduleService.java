package com.ruoyi.wx.wxBus.service;

import com.ruoyi.wx.wxBus.domain.MySchedule;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/10 17:58
 * @desc:
 */
public interface ScheduleService {

    //查询某个线路的发车时刻表
    List<MySchedule> querySchedule(String busNo);
}
