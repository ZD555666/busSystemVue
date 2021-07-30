package com.ruoyi.wx.wxBus.mapper;

import com.ruoyi.wx.wxBus.domain.Road;
import com.ruoyi.wx.wxBus.domain.Station;
import org.apache.ibatis.annotations.Mapper;

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
    //查询线路及对应的方向
    List<Road> selectRoads(String busNo);

}
