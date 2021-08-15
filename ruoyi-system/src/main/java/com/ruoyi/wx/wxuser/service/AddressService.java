package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<WxHistory> queryHistoryByOpId(String openId, String cityName);

    int insertHistory(WxHistory history);

    int insertHistory1(WxHistory history);

    int delAllHistory(String openId, String cityName);

    List<StationRoadVo> TaskQueryBusTo(String busNo, int direction);

    Map<String, Double> queryClickStationXy(String stationName, String cityName);

    List<BaiDuMarkers> queryRoadMap(String busNo,int direction);



}
