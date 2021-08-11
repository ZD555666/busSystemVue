package com.ruoyi.wx.wxBus.mapper;

import com.ruoyi.wx.wxBus.domain.MySchedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/10 17:56
 * @desc:
 */
@Mapper
public interface ScheduleMapper {
    //查询某个线路的发车时刻表
    List<MySchedule> selectSchedule(String busNo);
}
