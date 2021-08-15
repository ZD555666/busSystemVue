package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:50
 * @desc:
 */
@Mapper
public interface AddressMapper {

    List<WxStationInfo> queryNearStation(@Param("minLng") double minLng, @Param("maxLng") double maxLng, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("cityName") String cityName);

    List<WxBusRealRun> queryBusNoById(@Param("stationId") int stationId, @Param("cityName") String cityName, @Param("direction") int direction);

    List<StationRoadVo> queryBusTo(@Param("busNo") String busNo, @Param("cityName") String cityName, @Param("direction") int direction);

    StationRoadVo queryDistanceAndSpeed(@Param("licensePlate") String licensePlate, @Param("cityName") String cityName, @Param("direction") int direction);

    int querySurplusStation(@Param("stationName") String stationName, @Param("busNo") String BusNo, @Param("cityName") String cityName, @Param("direction") int direction);

    List<WxCollect> queryCollectByOpId(@Param("stationId") int stationId, @Param("stationName") String stationName, @Param("cityName") String cityName, @Param("openId") String openId);

    int insertCollect(@Param("stationId") int stationId, @Param("stationName") String stationName, @Param("cityName") String cityName, @Param("openId") String openId,
                      @Param("xPoint") double xPoint, @Param("yPoint") double yPoint);

    List<String> queryCollectCityName(String opId);

    List<WxCollect> queryCollectByOpIdAndCity(@Param("openId") String opId, @Param("cityName") String cityName);

    List<WxHistory> queryHistoryByOpId(@Param("openId") String openId, @Param("cityName") String cityName);

    int insertHistory(WxHistory history);

    int insertHistory1(WxHistory history);

    int delAllHistory(@Param("openId") String openId, @Param("cityName") String cityName);

    List<StationRoadVo> TaskQueryBusTo(@Param("busNo") String busNo, @Param("direction") int direction);

    Map<String, Double> queryClickStationXy(@Param("stationName") String stationName, @Param("cityName") String cityName);

    List<BaiDuMarkers> queryRoadMap(@Param("busNo") String busNo, @Param("direction") int direction);


}
