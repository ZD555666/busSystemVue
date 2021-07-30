package com.ruoyi.wx.wxBus.service;

import com.ruoyi.wx.wxBus.domain.Station;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/30 18:18
 * @desc:
 */
public interface StationService {
    //查询站点
    List<Station> queryStation(String value);

    //查询站点及对应的线路车辆
    List<Station> queryStations(String value);
}
