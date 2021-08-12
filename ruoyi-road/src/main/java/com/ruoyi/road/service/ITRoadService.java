package com.ruoyi.road.service;

import java.util.List;

import com.ruoyi.road.domain.TBus;
import com.ruoyi.road.domain.TRoad;
import com.ruoyi.road.domain.Schedule;
import com.ruoyi.road.domain.domains.RoadInfo;
import com.ruoyi.road.domain.domains.RoadStationInfo;

/**
 * 线路配置Service接口
 *
 * @author ruoyi
 * @date 2021-08-01
 */
public interface ITRoadService
{
    //查询公交线路信息列表
    public List<RoadInfo> selectAllRoad(RoadInfo roadInfo);

    //查询x线路的车所用的车辆
    public List<TBus> selectBusByRoad(TBus bus);

    //查询x线路发车时刻表
    public List<Schedule> selectScheduleByRoad(Schedule schedule);

    //根据线路查站点
    public List<RoadStationInfo> selectRoadByNo(RoadStationInfo roadStation);

    //新增线路
    public int insertRoad(List<TRoad> roadList);

    //删除路线
    public int deleteRoad(TRoad road);
}
