package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.WxSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/10 13:29
 */
@Mapper
public interface WxScheduleMapper {

    List<WxSchedule> queryScheduled();




}
