package com.ruoyi.wx.wxuser.service.impl;

import com.ruoyi.wx.wxuser.domain.WxSchedule;
import com.ruoyi.wx.wxuser.mapper.WxScheduleMapper;
import com.ruoyi.wx.wxuser.service.WxScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/10 13:30
 */
@Service
public class WxScheduleImpl implements WxScheduleService {

    @Autowired
    private WxScheduleMapper scheduleMapper;

    @Override
    public List<WxSchedule> queryScheduled() {
        return scheduleMapper.queryScheduled();
    }
}
