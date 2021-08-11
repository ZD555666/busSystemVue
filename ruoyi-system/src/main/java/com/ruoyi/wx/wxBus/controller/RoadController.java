package com.ruoyi.wx.wxBus.controller;

import com.ruoyi.wx.wxBus.domain.Road;
import com.ruoyi.wx.wxBus.domain.RoadPlan;
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

    //获取对应站点的站点信息
    @RequestMapping(value = "/getStationData")
    public Station getStationData(String stationName){
        Station station = stationService.queryStationName(stationName);
        return station;
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
    public List<RoadPlan> getRoadPlan(String locationValue,
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
        List<Double> meters = new ArrayList<>();
        Map<Double,Station> metSta = new HashMap<>();
        List<Double> meters1 = new ArrayList<>();
        Map<Double,Station> metSta1 = new HashMap<>();
        for(Station station : stations){
            //拿到起点附近站点
            GlobalCoordinates target1 =new GlobalCoordinates(Double.parseDouble(locationLatitude), Double.parseDouble(locationLongitude));
            GlobalCoordinates source1 =new GlobalCoordinates(Double.parseDouble(station.getYPoint()), Double.parseDouble(station.getXPoint()));
            double meter1 = getDistanceMeter(source1, target1, Ellipsoid.WGS84);
            //拿到终点的附近站点
            GlobalCoordinates target =new GlobalCoordinates(Double.parseDouble(toWhereLatitude), Double.parseDouble(toWhereLongitude));
            GlobalCoordinates source =new GlobalCoordinates(Double.parseDouble(station.getYPoint()), Double.parseDouble(station.getXPoint()));
            double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);
            if(meter1 <= 1000){
                System.out.println(meter1);
                meters.add(meter1);
                metSta.put(meter1,station);
            }
            if(meter2 <= 1000){
                System.out.println("......"+meter2);
                meters1.add(meter2);
                metSta1.put(meter2,station);
            }
        }
        //获取开始和结束的三个附近站点
        int check0 = 0;
        int check1 = 0;
        if(startNearStation != null){
            Collections.sort(meters);
            for(Double dos : meters){
                check0++;
                if(check0>3){
                    break;
                }
                startNearStation.add(metSta.get(dos));
            }
        }
        if(endNearStation != null){
            Collections.sort(meters1);
            for(Double dos : meters1){
                check1++;
                if(check1>3){
                    break;
                }
                endNearStation.add(metSta1.get(dos));
            }
        }

        List<String> roads = new ArrayList<>();
        List<Road> startRoads = new ArrayList<>();
        List<Road> endRoads = new ArrayList<>();
        for(Station startStation : startNearStation){
            List<Road> startRoad = roadService.queryPassRoad(startStation.getStationId());
            startRoads.addAll(startRoad);
        }
        for(Station endStation : endNearStation){
            List<Road> endRoad = roadService.queryPassRoad(endStation.getStationId());
            endRoads.addAll(endRoad);
        }
        for(Road road0 : startRoads){
          for(Road road1 : endRoads){
              if(road0.getBusNo().equals(road1.getBusNo())){
                  roads.add(road0.getBusNo());
              }
          }
        }
        //线路去重
        HashSet set = new HashSet(roads);
        roads.clear();
        roads.addAll(set);
        List<RoadPlan> roadPlans = new ArrayList<>();
        List<Road> startPassRoad = new ArrayList<>();
        List<Road> endPassRoad = new ArrayList<>();
        if(roads != null){    //直达判断
            for(String road :roads){
                RoadPlan roadPlan = new RoadPlan();
                Double mix = 0.0;
                for(Double dos : meters){
                    if(dos != 0.0){
                        mix = dos;
                        break;
                    }
                }
                Double mix1 = 0.0;
                for(Double dos : meters1){
                    if(dos != 0.0){
                        mix1 = dos;
                        break;
                    }
                }
                Station near = new Station();
                Station near1 = new Station();
                for(Station station :startNearStation){
                    List<Road> roads2 = roadService.queryPassRoad(station.getStationId());
                    for(Road road1 : roads2){
                        if(road1.getBusNo().equals(road)){
                            for (Map.Entry<Double,Station> entry : metSta.entrySet()) {
                                if(entry.getValue() == station){
                                    if(mix > entry.getKey()){
                                        mix = entry.getKey();
                                    }
                                }
                            }
                        }
                    }
                }
                for(Station station :endNearStation){
                    List<Road> roads2 = roadService.queryPassRoad(station.getStationId());
                    for(Road road1 : roads2){
                        if(road1.getBusNo().equals(road)){
                            for (Map.Entry<Double,Station> entry : metSta1.entrySet()) {
                                if(entry.getValue() == station){
                                    if(mix1 > entry.getKey()){
                                        mix1 = entry.getKey();
                                    }
                                }
                            }
                        }
                    }
                }

                for (Map.Entry<Double,Station> entry : metSta.entrySet()) {
                    if(entry.getKey().equals(mix)){
                       near = entry.getValue();
                    }
                }
                for (Map.Entry<Double,Station> entry : metSta1.entrySet()) {
                    if(entry.getKey().equals(mix1)){
                        near1 = entry.getValue();
                    }
                }
                //获取公交车运行的方向
                List<Road> roads1 = roadService.queryRoads(road);
                List<Road> roads2 = roadService.queryPassRoads(near.getStationId(),road);
                List<Road> roads3 = roadService.queryPassRoads(near1.getStationId(),road);
                if(roads2.get(0).getTravelSort() > roads3.get(0).getTravelSort()){
                    roadPlan.setDirection(roads1.get(0).getStation().getStationName());
                    roadPlan.setMomeyStation(roads2.get(0).getTravelSort() - roads3.get(0).getTravelSort()-1);
                }else{
                    roadPlan.setDirection(roads1.get(roads1.size()-1).getStation().getStationName());
                    roadPlan.setMomeyStation(roads3.get(0).getTravelSort() - roads2.get(0).getTravelSort());
                }
                roadPlan.setAllWalk(String.valueOf(mix+mix1).substring(0,String.valueOf(mix+mix1).lastIndexOf(".")));
                roadPlan.setStartWalk(String.valueOf(mix).substring(0,String.valueOf(mix).lastIndexOf(".")));
                roadPlan.setStartStation(near.getStationName());
                roadPlan.setEndWalk(String.valueOf(mix1).substring(0,String.valueOf(mix1).lastIndexOf(".")));
                roadPlan.setEndStation(near1.getStationName());
                roadPlan.setBusNo(road);
                roadPlan.setCheck("1");
                roadPlans.add(roadPlan);
            }

        }
        //先移除直达的线路在进行同站换乘线路的查询
        for(Station station :startNearStation) {
            List<Road> roads2 = roadService.queryPassRoad(station.getStationId());
            startPassRoad.addAll(roads2);
        }
        for(Station station :endNearStation) {
            List<Road> roads2 = roadService.queryPassRoad(station.getStationId());
            endPassRoad.addAll(roads2);
        }
        if(roads != null){
            for(String str : roads){
                if(startPassRoad != null){
                    Iterator<Road> iterator = startPassRoad.iterator();
                    while(iterator.hasNext()){
                        Road ro = iterator.next();
                        if(ro.getBusNo().equals(str))
                            iterator.remove();
                    }
                }
                if(endPassRoad != null){
                    Iterator<Road> iterator = endPassRoad.iterator();
                    while(iterator.hasNext()){
                        Road ro = iterator.next();
                        if(ro.getBusNo().equals(str))
                            iterator.remove();
                    }
                }
            }
        }
        //同站换乘
        b:for(Road road : startPassRoad){
            List<Road> roads1 = roadService.queryRoads(road.getBusNo());
            start:for(Road road1 : endPassRoad){
                List<Road> roads2 = roadService.queryRoads(road1.getBusNo());
               for(Road road2 :roads1){
                  end:for(Road road3 :roads2){
                      //算出两两站点之间的距离
                        if(road2.getStation().getStationName().equals(road3.getStation().getStationName())){
                            RoadPlan roadPlan = new RoadPlan();
                            Double mix = 0.0;
                            for(Double dos : meters){
                                if(dos != 0.0){
                                    mix = dos;
                                    break;
                                }
                            }
                            Double mix1 = 0.0;
                            for(Double dos : meters1){
                                if(dos != 0.0){
                                    mix1 = dos;
                                    break;
                                }
                            }
                            Station near = new Station();
                            Station near1 = new Station();
                            for(Station station :startNearStation) {
                                List<Road> roads3 = roadService.queryPassRoad(station.getStationId());
                                for(Road road4 : roads3){
                                    if(road4.getBusNo().equals(road.getBusNo())){
                                        for (Map.Entry<Double,Station> entry : metSta.entrySet()) {
                                            if(entry.getValue() == station){
                                                if(mix > entry.getKey()){
                                                    mix = entry.getKey();
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                            for(Station station :endNearStation) {
                                List<Road> roads3 = roadService.queryPassRoad(station.getStationId());
                                for(Road road4 : roads3){
                                    if(road4.getBusNo().equals(road1.getBusNo())){
                                        for (Map.Entry<Double,Station> entry : metSta1.entrySet()) {
                                            if(entry.getValue() == station){
                                                if(mix1 > entry.getKey()){
                                                    mix1 = entry.getKey();
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                            for (Map.Entry<Double,Station> entry : metSta.entrySet()) {
                                if(entry.getKey().equals(mix)){
                                    near = entry.getValue();
                                }
                            }
                            for (Map.Entry<Double,Station> entry : metSta1.entrySet()) {
                                if(entry.getKey().equals(mix1)){
                                    near1 = entry.getValue();
                                }
                            }
                            List<Road> rad  = roadService.queryRoads(road.getBusNo());
                            List<Road> rad1 = roadService.queryRoads(road1.getBusNo());
                            List<Road> rd1 = roadService.queryPassRoads(near.getStationId(),road.getBusNo());
                            List<Road> rd2 = roadService.queryPassRoads(near1.getStationId(),road1.getBusNo());
                            List<Road> rd3 = roadService.queryPassRoad(road2.getStation().getStationId());
                            List<Road> rd4 = roadService.queryPassRoad(road3.getStation().getStationId());
                            if(rd1.get(0).getTravelSort() > rd3.get(0).getTravelSort()){
                                roadPlan.setStartDirection(rad.get(0).getStation().getStationName());
                            }else{
                                roadPlan.setStartDirection(rad.get(rad.size()-1).getStation().getStationName());
                            }
                            if(rd2.get(0).getTravelSort() > rd4.get(0).getTravelSort()){
                                roadPlan.setEndDirection(rad1.get(0).getStation().getStationName());
                            }else{
                                roadPlan.setEndDirection(rad1.get(rad1.size()-1).getStation().getStationName());

                            }
                            //总的站点数量
                            int startMeter = rd1.get(0).getTravelSort() - rd3.get(0).getTravelSort();
                            int endMeter = rd2.get(0).getTravelSort() - rd4.get(0).getTravelSort();
                            int resultMeter = Math.abs(startMeter)+Math.abs(endMeter)-1;
                            roadPlan.setMomeyStation(resultMeter);
                            roadPlan.setAllWalk(String.valueOf(mix+mix1).substring(0,String.valueOf(mix+mix1).lastIndexOf(".")));
                            roadPlan.setStartWalk(String.valueOf(mix).substring(0,String.valueOf(mix).lastIndexOf(".")));
                            roadPlan.setStartStation(near.getStationName());
                            roadPlan.setEndWalk(String.valueOf(mix1).substring(0,String.valueOf(mix1).lastIndexOf(".")));
                            roadPlan.setEndStation(near1.getStationName());
                            roadPlan.setStartBus(road.getBusNo());
                            roadPlan.setEndBus(road1.getBusNo());
                            roadPlan.setBusNo(road.getBusNo());
                            roadPlan.setCheck("2");
                            roadPlan.setSameStation(road2.getStation().getStationName());
                            roadPlans.add(roadPlan);
                            break b;
                        }
                    }
                }
            }
        }
        //非同站换乘
        for(Road road : startPassRoad) {
            List<Road> roads1 = roadService.queryRoads(road.getBusNo());
            start:
            for (Road road1 : endPassRoad) {
                int check = 0;
                double mi = 1000;
                String startTurn = "";
                String endTurn = "";
                Road ro2 = null;
                Road ro3 = null;
                List<Road> roads2 = roadService.queryRoads(road1.getBusNo());
                for (Road road2 : roads1) {
                    GlobalCoordinates source = new GlobalCoordinates(Double.parseDouble(road2.getStation().getYPoint()), Double.parseDouble(road2.getStation().getXPoint()));
                    end:
                    for (Road road3 : roads2) {
                        //算出两两站点之间的距离
                        GlobalCoordinates target = new GlobalCoordinates(Double.parseDouble(road3.getStation().getYPoint()), Double.parseDouble(road3.getStation().getXPoint()));
                        double meter = getDistanceMeter(source, target, Ellipsoid.WGS84);

                        if(road2.getStation().getStationName().equals(road3.getStation().getStationName())){
                            check++;
                            continue start;
                        }
                        if(mi > meter){
                            mi = meter;
                            startTurn = road2.getStation().getStationName();
                            endTurn = road3.getStation().getStationName();
                            ro2 = road2;
                            ro3 = road3;
                        }
                    }
                }
                if(check == 0 && mi < 1000){
                    RoadPlan roadPlan = new RoadPlan();
                    Double mix = 0.0;
                    for(Double dos : meters){
                        if(dos != 0.0){
                            mix = dos;
                            break;
                        }
                    }
                    Double mix1 = 0.0;
                    for(Double dos : meters1){
                        if(dos != 0.0){
                            mix1 = dos;
                            break;
                        }
                    }
                    Station near = new Station();
                    Station near1 = new Station();
                    for(Station station :startNearStation) {
                        List<Road> roads3 = roadService.queryPassRoad(station.getStationId());
                        for(Road road4 : roads3){
                            if(road4.getBusNo().equals(road.getBusNo())){
                                for (Map.Entry<Double,Station> entry : metSta.entrySet()) {
                                    if(entry.getValue() == station){
                                        if(mix > entry.getKey()){
                                            mix = entry.getKey();
                                        }
                                    }
                                }
                            }
                        }

                    }
                    for(Station station :endNearStation) {
                        List<Road> roads3 = roadService.queryPassRoad(station.getStationId());
                        for(Road road4 : roads3){
                            if(road4.getBusNo().equals(road1.getBusNo())){
                                for (Map.Entry<Double,Station> entry : metSta1.entrySet()) {
                                    if(entry.getValue() == station){
                                        if(mix1 > entry.getKey()){
                                            mix1 = entry.getKey();
                                        }
                                    }
                                }
                            }
                        }

                    }
                    for (Map.Entry<Double,Station> entry : metSta.entrySet()) {
                        if(entry.getKey().equals(mix)){
                            near = entry.getValue();
                        }
                    }
                    for (Map.Entry<Double,Station> entry : metSta1.entrySet()) {
                        if(entry.getKey().equals(mix1)){
                            near1 = entry.getValue();
                        }
                    }
                    List<Road> rad  = roadService.queryRoads(road.getBusNo());
                    List<Road> rad1 = roadService.queryRoads(road1.getBusNo());
                    List<Road> rd1 = roadService.queryPassRoads(near.getStationId(),road.getBusNo());
                    List<Road> rd2 = roadService.queryPassRoads(near1.getStationId(),road1.getBusNo());
                    List<Road> rd3 = roadService.queryPassRoad(ro2.getStation().getStationId());
                    List<Road> rd4 = roadService.queryPassRoad(ro3.getStation().getStationId());
                    if(rd1.get(0).getTravelSort() > rd3.get(0).getTravelSort()){
                        roadPlan.setStartDirection(rad.get(0).getStation().getStationName());
                    }else{
                        roadPlan.setStartDirection(rad.get(rad.size()-1).getStation().getStationName());
                    }
                    if(rd2.get(0).getTravelSort() > rd4.get(0).getTravelSort()){
                        roadPlan.setEndDirection(rad1.get(0).getStation().getStationName());
                    }else{
                        roadPlan.setEndDirection(rad1.get(rad1.size()-1).getStation().getStationName());

                    }
                    //总的站点数量
                    System.out.println(rd1.get(0).getTravelSort() +"123" );
                    System.out.println(rd2.get(0).getTravelSort() +"123");
                    System.out.println(rd3.get(0).getTravelSort() +"123");
                    System.out.println(rd4.get(0).getTravelSort() +"123");
                    int startMeter = rd1.get(0).getTravelSort() - rd3.get(0).getTravelSort();
                    int endMeter = rd2.get(0).getTravelSort() - rd4.get(0).getTravelSort();
                    int resultMeter = Math.abs(startMeter)+Math.abs(endMeter);
                    roadPlan.setMomeyStation(resultMeter-1);
                    roadPlan.setAllWalk(String.valueOf(mix+mix1+mi).substring(0,String.valueOf(mix+mix1+mi).lastIndexOf(".")));
                    roadPlan.setStartWalk(String.valueOf(mix).substring(0,String.valueOf(mix).lastIndexOf(".")));
                    roadPlan.setStartStation(near.getStationName());
                    roadPlan.setEndWalk(String.valueOf(mix1).substring(0,String.valueOf(mix1).lastIndexOf(".")));
                    roadPlan.setEndStation(near1.getStationName());
                    roadPlan.setStartBus(road.getBusNo());
                    roadPlan.setSameStation("1");
                    roadPlan.setCheck("2");
                    roadPlan.setEndBus(road1.getBusNo());
                    roadPlan.setBusNo(road.getBusNo());
                    roadPlan.setStartTurn(startTurn);
                    roadPlan.setEndTurn(endTurn);
                    roadPlan.setMiddleWalk(String.valueOf(mi).substring(0,String.valueOf(mi).lastIndexOf(".")));
                    roadPlans.add(roadPlan);

                    break start;
                }
            }
        }
        HashSet set0 = new HashSet(roadPlans);
        roadPlans.clear();
        roadPlans.addAll(set0);
        System.out.println(startNearStation);
        System.out.println(endNearStation);
        System.out.println(roads);
        System.out.println(roadPlans);

        int meter = 3000;
        int check = 0;
        for(RoadPlan roadPlan :roadPlans){
            if(Integer.parseInt(roadPlan.getAllWalk()) < meter){
                meter = Integer.parseInt(roadPlan.getAllWalk());
            }
            if(roadPlan.getCheck().equals("1")){
                check++;
                roadPlan.setLessTurn("lessTurn");
            }
        }

        for(RoadPlan roadPlan :roadPlans){
            if(Integer.parseInt(roadPlan.getAllWalk()) == meter){
                roadPlan.setLessWalk("lessWalk");
            }
            if(check == 0 ){
                roadPlan.setLessTurn("lessTurn");
            }
        }
        return roadPlans;
    }


    //经纬度计算距离
    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid) {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve =new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }

}
