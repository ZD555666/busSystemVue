package com.ruoyi.wx.wxBus.controller;

import com.ruoyi.wx.wxBus.domain.Road;
import com.ruoyi.wx.wxBus.domain.Station;
import com.ruoyi.wx.wxBus.service.impl.RoadServiceImpl;
import com.ruoyi.wx.wxBus.service.impl.StationServiceImpl;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
    public Map<String,String> searchRoad(String value){
        if(!value.equals("")){
            Map<String,String> result = new HashMap<>();
            List<Road> roads = roadService.queryRoad(value);
            if(roads.size() == 0){
                List<Station> stations = stationService.queryStation(value);
                if(stations.size() == 0){
                    return null;
                }else{
                    for(int i = 0;i<stations.size();i++){
                        result.put(stations.get(i).getStationName(),null);
                    }
                    return result;
                }
            }else{
                for(int i = 0;i<roads.size();i++){
                    String busNo = roads.get(i).getBusNo();
                    List<Road> roads1 = roadService.queryRoads(busNo);
                    String startStationName = roads1.get(0).getStation().getStationName();
                    String endStationName = roads1.get(roads1.size()-1).getStation().getStationName();
                    result.put(startStationName,roads1.get(0).getBusNo());
                    result.put(endStationName,roads1.get(0).getBusNo());
                }
                return result;
            }
        }else{
            return null;
        }
    }

    //获取线路对应的站点
    @RequestMapping(value = "/getStation")
    public List getStation(String road,String station){
        List<Road> roads = roadService.queryRoads(road);
        if(roads.get(0).getStation().getStationName().equals(station)){
            Collections.reverse(roads);
            for(Road road1 : roads){
                System.out.println(road1.getStation().getStationName());
            }
        }
        return roads;
    }

    //获取站点
    @RequestMapping(value = "searchStation")
    public List<Station> getPlace(String value){
        if(!value.equals("")){
            List<Station> stations = stationService.queryStation(value);
            System.out.println(stations);
            return stations;
        }
        return null;
    }

    //获取线路方案
    @RequestMapping(value = "getRoadPlan")
    public void getRoadPlan(String locationValue,
                            String toWhereValue,
                            String locationLatitude,
                            String locationLongitude,
                            String toWhereLatitude,
                            String toWhereLongitude){
        System.out.println(locationValue);
        System.out.println(toWhereValue);
        System.out.println(locationLatitude);
        System.out.println(locationLongitude);
        System.out.println(toWhereLatitude);
        System.out.println(toWhereLongitude);

        //查询所有站点
        List<Station> stations = stationService.queryAllStation();
        List<Station> startNearStation = new ArrayList<>();
        List<Station> endNearStation = new ArrayList<>();
        for(Station station : stations){
            //拿到起点附近站点
            GlobalCoordinates target1 =new GlobalCoordinates(Double.parseDouble(locationLatitude), Double.parseDouble(locationLongitude));
            GlobalCoordinates source1 =new GlobalCoordinates(Double.parseDouble(station.getYPoint()), Double.parseDouble(station.getXPoint()));
            double meter1 = getDistanceMeter(source1, target1, Ellipsoid.WGS84);
            //拿到终点的附近站点
            GlobalCoordinates target =new GlobalCoordinates(Double.parseDouble(toWhereLatitude), Double.parseDouble(toWhereLongitude));
            GlobalCoordinates source =new GlobalCoordinates(Double.parseDouble(station.getYPoint()), Double.parseDouble(station.getXPoint()));
            double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);
            if(meter1 <= 5000){
                startNearStation.add(station);

            }
            if(meter2 <= 1000){
                endNearStation.add(station);
            }
        }

        //直达
        List<String> roads = new ArrayList<>();
        Map<String,String> mateStation = new HashMap<>();
        List<Road> startRoad = null;
        List<Road> endRoad = null;
        for(Station startStation : startNearStation){
            for(Station endStation : endNearStation){
                startRoad = roadService.queryPassRoad(startStation.getStationId());
                endRoad = roadService.queryPassRoad(endStation.getStationId());
                for(Road road0 : startRoad) {
                    for (Road road1 : endRoad) {
                        if(road0.getBusNo().equals(road1.getBusNo())){
                            roads.add(road0.getBusNo());
                            mateStation.put(startStation.getStationName(),endStation.getStationName());
                        }
                    }
                }
            }
        }
        System.out.println(startNearStation.size());
        System.out.println(endNearStation.size());
//        System.out.println(roads);
//        System.out.println(mateStation);

        //同站换乘


    }


    //经纬度计算距离
    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid)
    {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve =new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

        return geoCurve.getEllipsoidalDistance();
    }
}
