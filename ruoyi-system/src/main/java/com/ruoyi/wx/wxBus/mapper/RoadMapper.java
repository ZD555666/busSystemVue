package com.ruoyi.wx.wxBus.mapper;

import com.ruoyi.wx.wxBus.domain.Road;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/30 18:01
 * @desc:
 */
@Mapper
public interface RoadMapper {
    //查询线路是否存在
    List<Road> selectRoad(String value);

    //查询线路及对应的方向、站点
    List<Road> selectRoads(String busNo);

    //查询站点对应的线路信息
    List<Road> selectPassRoad(int stationId);

    //查询站点对应的线路信息
    List<Road> selectPassRoads(@Param("stationId")int stationId,
                               @Param("busNo") String busNo);

    //查询站点对应的线路信息
    Road selectTravelSort(@Param("stationId")int stationId,
                          @Param("busNo") String busNo,
                          @Param("direction") int dir);

    //查询线路方向
    Integer selectDirection(@Param("stationId")int stationId,
                            @Param("busNo") String busNo);



//    //查询线路对应的站点
//    List<Road> selectStations(String busNo);
}
