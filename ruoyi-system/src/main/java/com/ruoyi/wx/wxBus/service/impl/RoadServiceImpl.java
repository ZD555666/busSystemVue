package com.ruoyi.wx.wxBus.service.impl;

import com.ruoyi.wx.wxBus.domain.Road;
import com.ruoyi.wx.wxBus.mapper.RoadMapper;
import com.ruoyi.wx.wxBus.service.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/30 18:18
 * @desc:
 */
@Service
public class RoadServiceImpl implements RoadService {
    @Autowired
    private RoadMapper roadMapper;


    @Override
    public List<Road> queryRoad(String value) {
        List<Road> roads = roadMapper.selectRoad(value);
        return roads;
    }

    @Override
    public List<Road> queryRoads(String busNo) {
        List<Road> roads = roadMapper.selectRoads(busNo);
        return roads;
    }

    @Override
    public List<Road> queryPassRoad(int stationId) {
        return roadMapper.selectPassRoad(stationId);
    }
}
