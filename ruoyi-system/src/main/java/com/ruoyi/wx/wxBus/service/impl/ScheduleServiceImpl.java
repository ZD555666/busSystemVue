package com.ruoyi.wx.wxBus.service.impl;

import com.ruoyi.wx.wxBus.domain.MySchedule;
import com.ruoyi.wx.wxBus.mapper.ScheduleMapper;
import com.ruoyi.wx.wxBus.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/10 17:58
 * @desc:
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public List<MySchedule> querySchedule(String busNo) {
        return scheduleMapper.selectSchedule(busNo);
    }
}
