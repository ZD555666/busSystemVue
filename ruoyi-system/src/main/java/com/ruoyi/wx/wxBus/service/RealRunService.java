package com.ruoyi.wx.wxBus.service;

import com.ruoyi.wx.wxBus.domain.MyRealRun;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/13 0:03
 * @desc:
 */
public interface RealRunService {

    //查询实时数据
    List<MyRealRun> queryRealRun(int direction,String busNo);
}
