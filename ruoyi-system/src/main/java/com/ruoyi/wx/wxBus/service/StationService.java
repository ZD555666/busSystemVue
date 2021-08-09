package com.ruoyi.wx.wxBus.service;

import com.ruoyi.wx.wxBus.domain.Station;

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

    //查询站点信息
    Station queryStationName(String value);

    //查询站点及对应的线路车辆
    List<Station> queryStations(String value);

    //查询所有站点
    List<Station> queryAllStation();
}
