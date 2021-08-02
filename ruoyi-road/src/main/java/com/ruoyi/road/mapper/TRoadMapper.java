package com.ruoyi.road.mapper;

import java.util.List;

import com.ruoyi.road.domain.TBus;
import com.ruoyi.road.domain.TSchedule;
import com.ruoyi.road.domain.domains.RoadInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 线路配置Mapper接口
 * 
 * @author ruoyi
 * @date 2021-08-01
 */
@Mapper
public interface TRoadMapper 
{
    //查询公交线路信息列表
    public List<RoadInfo> selectAllRoad(RoadInfo roadInfo);
    //查询x线路的车所用的车辆
    public List<TBus> selectBusByRoad(TBus bus);
    //查询x线路发车时刻表
    public List<TSchedule> selectScheduleByRoad(TSchedule schedule);
    //根据线路查站点

}
