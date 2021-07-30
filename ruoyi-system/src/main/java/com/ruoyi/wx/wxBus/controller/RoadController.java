package com.ruoyi.wx.wxBus.controller;

import com.ruoyi.wx.wxBus.domain.Road;
import com.ruoyi.wx.wxBus.domain.Station;
import com.ruoyi.wx.wxBus.service.impl.RoadServiceImpl;
import com.ruoyi.wx.wxBus.service.impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/30 14:25
 * @desc:
 */
@RestController
@RequestMapping(value = "/wx")
public class RoadController {

    @Autowired
    private RoadServiceImpl roadService;
    @Autowired
    private StationServiceImpl stationService;

    //查询站点和线路
    @RequestMapping(value = "/searchRoad")
    public List searchRoad(String value){
        if(!value.equals("")){
            List<String> result = new ArrayList<>();
            List<Road> roads = roadService.queryRoad(value);
            if(roads.size() == 0){
                List<Station> stations = stationService.queryStation(value);
                if(stations.size() == 0){
                    return null;
                }else{
                    for(int i = 0;i<stations.size();i++){
                        result.add(stations.get(i).getStationName());
                    }
                    return result;
                }
            }else{
                for(int i = 0;i<roads.size();i++){
                    String busNo = roads.get(i).getBusNo();
                    List<Road> roads1 = roadService.queryRoads(busNo);
                    String startStationName = roads1.get(0).getStation().getStationName();
                    String endStationName = roads1.get(roads1.size()-1).getStation().getStationName();
                    result.add(roads1.get(0).getBusNo()+" 开往 "+startStationName);
                    result.add(roads1.get(0).getBusNo()+" 开往 "+endStationName);
                }
                return result;
            }
        }else{
            return null;
        }
    }

}
