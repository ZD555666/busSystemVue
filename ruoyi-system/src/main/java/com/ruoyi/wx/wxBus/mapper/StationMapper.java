package com.ruoyi.wx.wxBus.mapper;

import com.ruoyi.wx.wxBus.domain.MyStation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/30 18:02
 * @desc:
 */
@Mapper
public interface StationMapper {

    //查询站点
    List<MyStation> selectStation(String value);

    //查询站点信息
    MyStation selectStationName(String value);

    //查询站点及对应的线路车辆
    List<MyStation> selectStations(String value);

    //查询所有站点
    List<MyStation> selectAllStation();
}
