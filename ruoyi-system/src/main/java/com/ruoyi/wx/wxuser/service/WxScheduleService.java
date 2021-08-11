package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.WxSchedule;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/10 13:29
 */
public interface WxScheduleService {

    List<WxSchedule> queryScheduled(int busState1, int busState2);


}
