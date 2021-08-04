package com.ruoyi.wx.wxuser.service.impl;

import com.ruoyi.wx.wxuser.domain.*;
import com.ruoyi.wx.wxuser.mapper.AddressMapper;
import com.ruoyi.wx.wxuser.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:51
 * @desc:
 */
@Service
public class AddressMapperImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;


    @Override
    public List<WxStationInfo> queryNearStation(double minLng, double maxLng, double minLat, double maxLat, String cityName) {
        return addressMapper.queryNearStation(minLng, maxLng, minLat, maxLat, cityName);
    }

    @Override
    public List<WxBusRealRun> queryBusNoById(int stationId, String cityName, int direction) {
        return addressMapper.queryBusNoById(stationId, cityName, direction);
    }

    @Override
    public List<StationRoadVo> queryBusTo(String busNo, String cityName, int direction) {
        return addressMapper.queryBusTo(busNo, cityName, direction);
    }

    @Override
    public StationRoadVo queryDistanceAndSpeed(String licensePlate, String cityName, int direction) {
        return addressMapper.queryDistanceAndSpeed(licensePlate, cityName, direction);
    }

    @Override
    public int querySurplusStation(String stationName, String BusNo, String cityName, int direction) {
        return addressMapper.querySurplusStation(stationName, BusNo, cityName, direction);
    }

    @Override
    public List<WxCollect> queryCollectByOpId(int stationId, String stationName, String cityName, String openId) {
        return addressMapper.queryCollectByOpId(stationId, stationName, cityName, openId);
    }

    @Override
    public int insertCollect(int stationId, String stationName, String cityName, String openId, double xPoint, double yPoint) {
        return addressMapper.insertCollect(stationId, stationName, cityName, openId, xPoint, yPoint);
    }

    @Override
    public List<String> queryCollectCityName(String opId) {
        return addressMapper.queryCollectCityName(opId);
    }

    @Override
    public List<WxCollect> queryCollectByOpIdAndCity(String opId, String cityName) {
        return addressMapper.queryCollectByOpIdAndCity(opId, cityName);
    }
}

