package com.ruoyi.wx.wxBus.service;

import com.ruoyi.wx.wxBus.domain.Road;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/30 18:17
 * @desc:
 */

public interface RoadService {

    //查询线路
    List<Road> queryRoad(String value);

    //查询线路及对应的方向
    List<Road> queryRoads(String busNo);

    //查询站点对应的线路信息
    List<Road> queryPassRoad(int stationId);

    //查询站点对应的线路信息
    List<Road> queryPassRoads(int stationId,String busNo);
}