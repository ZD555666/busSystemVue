package com.ruoyi.wx.wxBus.service.impl;

import com.ruoyi.wx.wxBus.domain.MyStation;
import com.ruoyi.wx.wxBus.mapper.StationMapper;
import com.ruoyi.wx.wxBus.service.StationService;
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
public class StationServiceImpl implements StationService {

    @Autowired
    private StationMapper stationMapper;

    @Override
    public List<MyStation> queryStation(String value) {
        List<MyStation> stations = stationMapper.selectStation(value);
        return stations;
    }

    @Override
    public MyStation queryStationName(String value) {
        return stationMapper.selectStationName(value);
    }

    @Override
    public List<MyStation> queryStations(String value) {
        List<MyStation> stations = stationMapper.selectStations(value);
        return stations;
    }

    @Override
    public List<MyStation> queryAllStation() {
        return stationMapper.selectAllStation();
    }
}
