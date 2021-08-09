package com.ruoyi.wx.wxBus.service.impl;

import com.ruoyi.wx.wxBus.domain.Station;
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
    public List<Station> queryStation(String value) {
        List<Station> stations = stationMapper.selectStation(value);
        return stations;
    }

    @Override
    public Station queryStationName(String value) {
        return stationMapper.selectStationName(value);
    }

    @Override
    public List<Station> queryStations(String value) {
        List<Station> stations = stationMapper.selectStations(value);
        return stations;
    }

    @Override
    public List<Station> queryAllStation() {
        return stationMapper.selectAllStation();
    }
}
