package com.ruoyi.road.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.road.domain.TBus;
import com.ruoyi.road.domain.TRoad;
import com.ruoyi.road.domain.Schedule;
import com.ruoyi.road.domain.domains.RoadInfo;
import com.ruoyi.road.domain.domains.RoadStationInfo;
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
            Schedule schedule= Schedule.builder().busNo(r.getBusNo()).build();
            List<Schedule> scheduleList=selectScheduleByRoad(schedule);
            if (scheduleList.size() != 0) {
                int scTimes=0;
                for (Schedule s:scheduleList) {
                    int startTime = s.getStarTime();
                    int endTime = s.getEndTime();
                    int timeInterval = s.getTimeInterval();
                    scTimes += (endTime - startTime)*60 / timeInterval;
                }
                r.setScheduleListSize(scTimes);
                r.setScheduleList(scheduleList);
            }
            //查询站点
            RoadStationInfo roadStationInfo=RoadStationInfo.builder().busNo(r.getBusNo()).build();
            List<RoadStationInfo> roadStationList=selectRoadByNo(roadStationInfo);
            if (roadStationList.size() !=0){
                List<RoadStationInfo> RoadStationList=new ArrayList<RoadStationInfo>();
                List<RoadStationInfo> RoadStationReList= new ArrayList<RoadStationInfo>();
                for (RoadStationInfo rs: roadStationList) {
                    if (rs.getDirection()==0){
                        RoadStationList.add(rs);
                    }else if (rs.getDirection() == 1){
                        RoadStationReList.add(rs);
                    }
                }
                r.setRsList(RoadStationList);
                r.setRsReList(RoadStationReList);
                r.setRsListSize(RoadStationList.size());
                r.setRsReListSize(RoadStationReList.size());
            }
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
    public List<Schedule> selectScheduleByRoad(Schedule schedule) {
        List<Schedule> scheduleList= tRoadMapper.selectScheduleByRoad(schedule);
        return scheduleList;
    }

    /**
     * 查询x线路对应的站点
     */
    @Override
    public List<RoadStationInfo> selectRoadByNo(RoadStationInfo roadStation) {
        List<RoadStationInfo> roadStationList=tRoadMapper.selectRoadByNo(roadStation);
        return roadStationList;
    }

    /**
     * 新增线路
     */
    @Override
    public int insertRoad(List<TRoad> roadList) {
        int flag= tRoadMapper.insertRoad(roadList);
        return flag;
    }

    @Override
    public int deleteRoad(TRoad road) {
        int flag=tRoadMapper.deleteRoad(road);
        return 0;
    }
}
