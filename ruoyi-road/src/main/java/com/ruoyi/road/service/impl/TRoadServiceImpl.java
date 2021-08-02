package com.ruoyi.road.service.impl;

import java.util.List;

import com.ruoyi.road.domain.TBus;
import com.ruoyi.road.domain.TSchedule;
import com.ruoyi.road.domain.domains.RoadInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.road.mapper.TRoadMapper;
import com.ruoyi.road.service.ITRoadService;

/**
 * 线路配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-01
 */
@Service
public class TRoadServiceImpl implements ITRoadService 
{
    @Autowired
    private TRoadMapper tRoadMapper;

    /**
     * 查询公交线路信息列表
     */
    @Override
    public List<RoadInfo> selectAllRoad(RoadInfo roadInfo) {
        List<RoadInfo> roadInfoList= tRoadMapper.selectAllRoad(roadInfo);
        roadInfoList.forEach(r -> {
            //查询公交
            TBus bus = TBus.builder().busNo(r.getBusNo()).build();
            List<TBus> busList = selectBusByRoad(bus);
            r.setBusListSize(busList.size());
            if (busList.size() != 0) {
                r.setBusList(busList);
            }
            //查询时刻表
            TSchedule schedule=TSchedule.builder().busNo(r.getBusNo()).build();
            List<TSchedule> scheduleList=selectScheduleByRoad(schedule);
            if (scheduleList.size() != 0) {
                int scTimes=0;
                for (TSchedule s:scheduleList
                     ) {
                    int startTime = Integer.parseInt(s.getStarTime());
                    int endTime = Integer.parseInt(s.getEndTime());
                    int timeInterval = s.getTimeInterval();
                    scTimes += (endTime - startTime)*60 / timeInterval;
                }
                r.setScheduleListSize(scTimes);
                r.setScheduleList(scheduleList);
            }
            //查询站点

        });
        return roadInfoList;
    }

    /**
     * 查询x线路的车所用的车辆
     */
    @Override
    public List<TBus> selectBusByRoad(TBus bus) {
        List<TBus> busList = tRoadMapper.selectBusByRoad(bus);
        System.out.println(busList);
        return busList;
    }

    /**
     * 查询x线路发车时刻表
     */
    @Override
    public List<TSchedule> selectScheduleByRoad(TSchedule schedule) {
        List<TSchedule> scheduleList= tRoadMapper.selectScheduleByRoad(schedule);
        return scheduleList;
    }
}
