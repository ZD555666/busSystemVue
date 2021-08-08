package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:50
 * @desc:
 */
public interface AddressService {

    List<WxStationInfo> queryNearStation(double minLng, double maxLng, double minLat, double maxLat, String cityName);

    List<WxBusRealRun> queryBusNoById(int stationId, String cityName, int direction);

    List<StationRoadVo> queryBusTo(String busNo, String cityName, int direction);

    StationRoadVo queryDistanceAndSpeed(String licensePlate, String cityName, int direction);

    int querySurplusStation(String stationName, String BusNo, String cityName, int direction);

    List<WxCollect> queryCollectByOpId(int stationId, String stationName, String cityName, String openId);

    int insertCollect(int stationId, String stationName, String cityName, String openId, double xPoint, double yPoint);

    List<String> queryCollectCityName(String opId);

    List<WxCollect> queryCollectByOpIdAndCity(String opId, String cityName);

    List<WxHistory> queryHistoryByOpId(String openId);

    int insertHistory(WxHistory history);


}
