package com.ruoyi.wx.wxBus.controller;

import com.ruoyi.wx.wxBus.domain.*;
import com.ruoyi.wx.wxBus.service.impl.RealRunServiceImpl;
import com.ruoyi.wx.wxBus.service.impl.RoadServiceImpl;
import com.ruoyi.wx.wxBus.service.impl.StationServiceImpl;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.NestingKind;
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
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    private RealRunServiceImpl realRunService;


    //查询站点和线路
    @RequestMapping(value = "/searchRoad")
    public Map<String,String> searchRoad(String value){
        if(!value.equals("")){
            Map<String,String> result = new HashMap<>();
            //使用redis缓存
            String redisRoad = "Road"+value;
            List<Road> roads = (List<Road>)redisTemplate.opsForValue().get(redisRoad);
            if(roads == null){
                synchronized (this){
                    if(roads == null){
                        //从数据库查询
                        roads = roadService.queryRoad(value);
                        redisTemplate.opsForValue().set(redisRoad,roads);
                    }
                }
            }
            if(roads.size() == 0){
                String redisStation = "Station"+value;
                List<MyStation> stations = (List<MyStation>)redisTemplate.opsForValue().get(redisStation);
                if(stations == null){
                    synchronized (this){
                        if(stations == null){
                            //从数据库查询
                            stations = stationService.queryStation(value);
                            redisTemplate.opsForValue().set(redisStation,stations);
                        }
                    }
                }
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
                    //使用redis缓存
                    String redisRoads = "Roads"+busNo;
                    List<Road> roads1 = (List<Road>)redisTemplate.opsForValue().get(redisRoads);
                    if(roads1 == null){
                        synchronized (this){
                            if(roads1 == null){
                                //从数据库查询
                                roads1 = roadService.queryRoads(busNo);
                                redisTemplate.opsForValue().set(redisRoads,roads1);
                            }
                        }
                    }
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
    public MyStation getStationData(String stationName){
        //使用redis缓存
        String redisStationName = "StationName"+stationName;
        MyStation station = (MyStation)redisTemplate.opsForValue().get(redisStationName);
        if(station == null){
            synchronized (this){
                if(station == null){
                    //从数据库查询
                    station = stationService.queryStationName(stationName);
                    redisTemplate.opsForValue().set(redisStationName,station);
                }
            }
        }
        return station;
    }


    //获取线路对应的站点
    @RequestMapping(value = "/getStation")
    public List getStation(String road,String station){
        //使用redis缓存
        String redisRoads = "Roads"+road;
        List<Road> roads = (List<Road>)redisTemplate.opsForValue().get(redisRoads);
        if(roads == null){
            synchronized (this){
                if(roads == null){
                    //从数据库查询
                    roads = roadService.queryRoads(road);
                    redisTemplate.opsForValue().set(redisRoads,roads);
                }
            }
        }
        if(roads.get(0).getStation().getStationName().equals(station)){
            Collections.reverse(roads);
        }
        return roads;
    }

    //获取站点
    @RequestMapping(value = "searchStation")
    public List<MyStation> getPlace(String value){
        if(!value.equals("")){
            String redisStation = "Station"+value;
            List<MyStation> stations = (List<MyStation>)redisTemplate.opsForValue().get(redisStation);
            if(stations == null){
                synchronized (this){
                    if(stations == null){
                        //从数据库查询
                        stations = stationService.queryStation(value);
                        redisTemplate.opsForValue().set(redisStation,stations);
                    }
                }
            }
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
        String redisAllStation = "AllStation";
        List<MyStation> stations = (List<MyStation>)redisTemplate.opsForValue().get(redisAllStation);
        if(stations == null){
            synchronized (this){
                if(stations == null){
                    //从数据库查询
                    stations = stationService.queryAllStation();;
                    redisTemplate.opsForValue().set(redisAllStation,stations);
                }
            }
        }
        List<MyStation> startNearStation = new ArrayList<>();
        List<MyStation> endNearStation = new ArrayList<>();
        List<Double> meters = new ArrayList<>();
        Map<Double, MyStation> metSta = new HashMap<>();
        List<Double> meters1 = new ArrayList<>();
        Map<Double, MyStation> metSta1 = new HashMap<>();
        for(MyStation station : stations){
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
        for(MyStation startStation : startNearStation){
            //存入redis
            String redisPassRoad = "PassRoad"+startStation.getStationId();
            List<Road> startRoad = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
            if(startRoad == null){
                synchronized (this){
                    if(startRoad == null){
                        //从数据库查询
                        startRoad = roadService.queryPassRoad(startStation.getStationId());
                        redisTemplate.opsForValue().set(redisPassRoad,startRoad);
                    }
                }
            }
            startRoads.addAll(startRoad);
        }
        for(MyStation endStation : endNearStation){
            //存入redis
            String redisPassRoad = "PassRoad"+endStation.getStationId();
            List<Road> endRoad = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
            if(endRoad == null){
                synchronized (this){
                    if(endRoad == null){
                        //从数据库查询
                        endRoad = roadService.queryPassRoad(endStation.getStationId());
                        redisTemplate.opsForValue().set(redisPassRoad,endRoad);
                    }
                }
            }
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
                MyStation near = new MyStation();
                MyStation near1 = new MyStation();
                for(MyStation station :startNearStation){
                    //存入redis
                    String redisPassRoad = "PassRoad"+station.getStationId();
                    List<Road> roads2 = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
                    if(roads2 == null){
                        synchronized (this){
                            if(roads2 == null){
                                //从数据库查询
                                roads2 = roadService.queryPassRoad(station.getStationId());
                                redisTemplate.opsForValue().set(redisPassRoad,roads2);
                            }
                        }
                    }
                    for(Road road1 : roads2){
                        if(road1.getBusNo().equals(road)){
                            for (Map.Entry<Double, MyStation> entry : metSta.entrySet()) {
                                if(entry.getValue() == station){
                                    if(mix > entry.getKey()){
                                        mix = entry.getKey();
                                    }
                                }
                            }
                        }
                    }
                }
                for(MyStation station :endNearStation){
                    //存入redis
                    String redisPassRoad = "PassRoad"+station.getStationId();
                    List<Road> roads2 = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
                    if(roads2 == null){
                        synchronized (this){
                            if(roads2 == null){
                                //从数据库查询
                                roads2 = roadService.queryPassRoad(station.getStationId());
                                redisTemplate.opsForValue().set(redisPassRoad,roads2);
                            }
                        }
                    }
                    for(Road road1 : roads2){
                        if(road1.getBusNo().equals(road)){
                            for (Map.Entry<Double, MyStation> entry : metSta1.entrySet()) {
                                if(entry.getValue() == station){
                                    if(mix1 > entry.getKey()){
                                        mix1 = entry.getKey();
                                    }
                                }
                            }
                        }
                    }
                }

                for (Map.Entry<Double, MyStation> entry : metSta.entrySet()) {
                    if(entry.getKey().equals(mix)){
                       near = entry.getValue();
                    }
                }
                for (Map.Entry<Double, MyStation> entry : metSta1.entrySet()) {
                    if(entry.getKey().equals(mix1)){
                        near1 = entry.getValue();
                    }
                }
                //获取公交车运行的方向
                List<Road> roads1 = roadService.queryRoads(road);
                List<Road> roads2 = roadService.queryPassRoads(near.getStationId(),road);
                List<Road> roads3 = roadService.queryPassRoads(near1.getStationId(),road);
                String dir = "";
                if(roads2.get(0).getTravelSort() > roads3.get(0).getTravelSort()){
                    roadPlan.setDirection(roads1.get(0).getStation().getStationName());
                    dir = roads1.get(0).getStation().getStationName();
                    roadPlan.setMomeyStation(roads2.get(0).getTravelSort() - roads3.get(0).getTravelSort()-1);
                }else{
                    roadPlan.setDirection(roads1.get(roads1.size()-1).getStation().getStationName());
                    dir = roads1.get(roads1.size()-1).getStation().getStationName();
                    roadPlan.setMomeyStation(roads3.get(0).getTravelSort() - roads2.get(0).getTravelSort());
                }
                roadPlan.setAllWalk(String.valueOf(mix+mix1).substring(0,String.valueOf(mix+mix1).lastIndexOf(".")));
                roadPlan.setStartWalk(String.valueOf(mix).substring(0,String.valueOf(mix).lastIndexOf(".")));
                roadPlan.setStartStation(near.getStationName());
                roadPlan.setEndWalk(String.valueOf(mix1).substring(0,String.valueOf(mix1).lastIndexOf(".")));
                roadPlan.setEndStation(near1.getStationName());
                roadPlan.setBusNo(road);
                roadPlan.setCheck("1");

                //总时间获取
                GlobalCoordinates target =new GlobalCoordinates(Double.parseDouble(near.getYPoint()), Double.parseDouble(near.getXPoint()));
                GlobalCoordinates source =new GlobalCoordinates(Double.parseDouble(near1.getYPoint()), Double.parseDouble(near1.getXPoint()));
                double meter = getDistanceMeter(source,target, Ellipsoid.WGS84);
                double time = ((meter)/(11/3.6))/60 + (mix+mix1)/1.1/60;
                roadPlan.setAllDate(String.valueOf(time).substring(0,String.valueOf(time).lastIndexOf(".")));
                //实时数据获取
                RealData realData = getRealData(road, dir, near.getStationName());
                if(realData != null){
                    roadPlan.setPlanDate(realData.getTime());
                    roadPlan.setPlanStations(realData.getMoneyStation());
                }
                roadPlans.add(roadPlan);
            }

        }
        //先移除直达的线路在进行同站换乘线路的查询
        for(MyStation station :startNearStation) {
            //存入redis
            String redisPassRoad = "PassRoad"+station.getStationId();
            List<Road> roads2 = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
            if(roads2 == null){
                synchronized (this){
                    if(roads2 == null){
                        //从数据库查询
                        roads2 = roadService.queryPassRoad(station.getStationId());
                        redisTemplate.opsForValue().set(redisPassRoad,roads2);
                    }
                }
            }
            startPassRoad.addAll(roads2);
        }
        for(MyStation station :endNearStation) {
            //存入redis
            String redisPassRoad = "PassRoad"+station.getStationId();
            List<Road> roads2 = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
            if(roads2 == null){
                synchronized (this){
                    if(roads2 == null){
                        //从数据库查询
                        roads2 = roadService.queryPassRoad(station.getStationId());
                        redisTemplate.opsForValue().set(redisPassRoad,roads2);
                    }
                }
            }
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
            //使用redis缓存
            String redisRoads = "Roads"+road.getBusNo();
            List<Road> roads1 = (List<Road>)redisTemplate.opsForValue().get(redisRoads);
            if(roads1 == null){
                synchronized (this){
                    if(roads1 == null){
                        //从数据库查询
                        roads1 = roadService.queryRoads(road.getBusNo());
                        redisTemplate.opsForValue().set(redisRoads,roads1);
                    }
                }
            }
            start:for(Road road1 : endPassRoad){
                //使用redis缓存
                String redisRoads1 = "Roads"+road1.getBusNo();
                List<Road> roads2 = (List<Road>)redisTemplate.opsForValue().get(redisRoads1);
                if(roads2 == null){
                    synchronized (this){
                        if(roads2 == null){
                            //从数据库查询
                            roads2 = roadService.queryRoads(road1.getBusNo());
                            redisTemplate.opsForValue().set(redisRoads1,roads2);
                        }
                    }
                }
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
                            MyStation near = new MyStation();
                            MyStation near1 = new MyStation();
                            for(MyStation station :startNearStation) {
                                //存入redis
                                String redisPassRoad = "PassRoad"+station.getStationId();
                                List<Road> roads3 = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
                                if(roads3 == null){
                                    synchronized (this){
                                        if(roads3 == null){
                                            //从数据库查询
                                            roads3 = roadService.queryPassRoad(station.getStationId());
                                            redisTemplate.opsForValue().set(redisPassRoad,roads3);
                                        }
                                    }
                                }
                                for(Road road4 : roads3){
                                    if(road4.getBusNo().equals(road.getBusNo())){
                                        for (Map.Entry<Double, MyStation> entry : metSta.entrySet()) {
                                            if(entry.getValue() == station){
                                                if(mix > entry.getKey()){
                                                    mix = entry.getKey();
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                            for(MyStation station :endNearStation) {
                                //存入redis
                                String redisPassRoad = "PassRoad"+station.getStationId();
                                List<Road> roads3 = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
                                if(roads3 == null){
                                    synchronized (this){
                                        if(roads3 == null){
                                            //从数据库查询
                                            roads3 = roadService.queryPassRoad(station.getStationId());
                                            redisTemplate.opsForValue().set(redisPassRoad,roads3);
                                        }
                                    }
                                }
                                for(Road road4 : roads3){
                                    if(road4.getBusNo().equals(road1.getBusNo())){
                                        for (Map.Entry<Double, MyStation> entry : metSta1.entrySet()) {
                                            if(entry.getValue() == station){
                                                if(mix1 > entry.getKey()){
                                                    mix1 = entry.getKey();
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                            for (Map.Entry<Double, MyStation> entry : metSta.entrySet()) {
                                if(entry.getKey().equals(mix)){
                                    near = entry.getValue();
                                }
                            }
                            for (Map.Entry<Double, MyStation> entry : metSta1.entrySet()) {
                                if(entry.getKey().equals(mix1)){
                                    near1 = entry.getValue();
                                }
                            }
                            List<Road> rad  = roadService.queryRoads(road.getBusNo());
                            List<Road> rad1 = roadService.queryRoads(road1.getBusNo());
                            List<Road> rd1 = roadService.queryPassRoads(near.getStationId(),road.getBusNo());
                            List<Road> rd2 = roadService.queryPassRoads(near1.getStationId(),road1.getBusNo());
                            List<Road> rd3 = roadService.queryPassRoads(road2.getStation().getStationId(),road.getBusNo());
                            List<Road> rd4 = roadService.queryPassRoads(road3.getStation().getStationId(),road1.getBusNo());
                            String dir = "";
                            if(rd1.get(0).getTravelSort() > rd3.get(0).getTravelSort()){
                                roadPlan.setStartDirection(rad.get(0).getStation().getStationName());
                                dir = rad.get(0).getStation().getStationName();
                            }else{
                                roadPlan.setStartDirection(rad.get(rad.size()-1).getStation().getStationName());
                                dir = rad.get(rad.size()-1).getStation().getStationName();
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

                            //总时间获取
                            GlobalCoordinates target =new GlobalCoordinates(Double.parseDouble(near.getYPoint()), Double.parseDouble(near.getXPoint()));
                            GlobalCoordinates source =new GlobalCoordinates(Double.parseDouble(near1.getYPoint()), Double.parseDouble(near1.getXPoint()));
                            double meter = getDistanceMeter(source,target, Ellipsoid.WGS84);
                            double time = ((meter)/(11/3.6))/60 + (mix+mix1)/1.1/60;
                            roadPlan.setAllDate(String.valueOf(time).substring(0,String.valueOf(time).lastIndexOf(".")));
                            //实时数据获取
                            RealData realData = getRealData(road.getBusNo(), dir, near.getStationName());
                            if(realData != null){
                                roadPlan.setPlanDate(realData.getTime());
                                roadPlan.setPlanStations(realData.getMoneyStation());
                            }

                            roadPlans.add(roadPlan);
                            break b;
                        }
                    }
                }
            }
        }
        //非同站换乘
        for(Road road : startPassRoad) {
            //使用redis缓存
            String redisRoads = "Roads"+road.getBusNo();
            List<Road> roads1 = (List<Road>)redisTemplate.opsForValue().get(redisRoads);
            if(roads1 == null){
                synchronized (this){
                    if(roads1 == null){
                        //从数据库查询
                        roads1 = roadService.queryRoads(road.getBusNo());
                        redisTemplate.opsForValue().set(redisRoads,roads1);
                    }
                }
            }
            start:
            for (Road road1 : endPassRoad) {
                int check = 0;
                double mi = 1000;
                String startTurn = "";
                String endTurn = "";
                Road ro2 = null;
                Road ro3 = null;
                //使用redis缓存
                String redisRoads1 = "Roads"+road1.getBusNo();
                List<Road> roads2 = (List<Road>)redisTemplate.opsForValue().get(redisRoads1);
                if(roads2 == null){
                    synchronized (this){
                        if(roads2 == null){
                            //从数据库查询
                            roads2 = roadService.queryRoads(road1.getBusNo());
                            redisTemplate.opsForValue().set(redisRoads1,roads2);
                        }
                    }
                }
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
                    MyStation near = new MyStation();
                    MyStation near1 = new MyStation();
                    for(MyStation station :startNearStation) {
                        //存入redis
                        String redisPassRoad = "PassRoad"+station.getStationId();
                        List<Road> roads3 = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
                        if(roads3 == null){
                            synchronized (this){
                                if(roads3 == null){
                                    //从数据库查询
                                    roads3 = roadService.queryPassRoad(station.getStationId());
                                    redisTemplate.opsForValue().set(redisPassRoad,roads3);
                                }
                            }
                        }
                        for(Road road4 : roads3){
                            if(road4.getBusNo().equals(road.getBusNo())){
                                for (Map.Entry<Double, MyStation> entry : metSta.entrySet()) {
                                    if(entry.getValue() == station){
                                        if(mix > entry.getKey()){
                                            mix = entry.getKey();
                                        }
                                    }
                                }
                            }
                        }

                    }
                    for(MyStation station :endNearStation) {
                        //存入redis
                        String redisPassRoad = "PassRoad"+station.getStationId();
                        List<Road> roads3 = (List<Road>)redisTemplate.opsForValue().get(redisPassRoad);
                        if(roads3 == null){
                            synchronized (this){
                                if(roads3 == null){
                                    //从数据库查询
                                    roads3 = roadService.queryPassRoad(station.getStationId());
                                    redisTemplate.opsForValue().set(redisPassRoad,roads3);
                                }
                            }
                        }
                        for(Road road4 : roads3){
                            if(road4.getBusNo().equals(road1.getBusNo())){
                                for (Map.Entry<Double, MyStation> entry : metSta1.entrySet()) {
                                    if(entry.getValue() == station){
                                        if(mix1 > entry.getKey()){
                                            mix1 = entry.getKey();
                                        }
                                    }
                                }
                            }
                        }

                    }
                    for (Map.Entry<Double, MyStation> entry : metSta.entrySet()) {
                        if(entry.getKey().equals(mix)){
                            near = entry.getValue();
                        }
                    }
                    for (Map.Entry<Double, MyStation> entry : metSta1.entrySet()) {
                        if(entry.getKey().equals(mix1)){
                            near1 = entry.getValue();
                        }
                    }
                    List<Road> rad  = roadService.queryRoads(road.getBusNo());
                    List<Road> rad1 = roadService.queryRoads(road1.getBusNo());
                    List<Road> rd1 = roadService.queryPassRoads(near.getStationId(),road.getBusNo());
                    List<Road> rd2 = roadService.queryPassRoads(near1.getStationId(),road1.getBusNo());
                    List<Road> rd3 = roadService.queryPassRoads(ro2.getStation().getStationId(),road.getBusNo());
                    List<Road> rd4 = roadService.queryPassRoads(ro3.getStation().getStationId(),road1.getBusNo());
                    String dir = "";
                    if(rd1.get(0).getTravelSort() > rd3.get(0).getTravelSort()){
                        roadPlan.setStartDirection(rad.get(0).getStation().getStationName());
                        dir = rad.get(0).getStation().getStationName();
                    }else{
                        roadPlan.setStartDirection(rad.get(rad.size()-1).getStation().getStationName());
                        dir = rad.get(rad.size()-1).getStation().getStationName();
                    }
                    if(rd2.get(0).getTravelSort() > rd4.get(0).getTravelSort()){
                        roadPlan.setEndDirection(rad1.get(0).getStation().getStationName());
                    }else{
                        roadPlan.setEndDirection(rad1.get(rad1.size()-1).getStation().getStationName());

                    }
                    //总的站点数量
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

                    //总时间获取
                    GlobalCoordinates target =new GlobalCoordinates(Double.parseDouble(near.getYPoint()), Double.parseDouble(near.getXPoint()));
                    GlobalCoordinates source =new GlobalCoordinates(Double.parseDouble(near1.getYPoint()), Double.parseDouble(near1.getXPoint()));
                    double meter = getDistanceMeter(source,target, Ellipsoid.WGS84);
                    double time = ((meter)/(11/3.6))/60 + (mix+mix1)/1.1/60;
                    roadPlan.setAllDate(String.valueOf(time).substring(0,String.valueOf(time).lastIndexOf(".")));
                    //实时数据获取
                    RealData realData = getRealData(road.getBusNo(), dir, near.getStationName());
                    if(realData != null){
                        roadPlan.setPlanDate(realData.getTime());
                        roadPlan.setPlanStations(realData.getMoneyStation());
                    }

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
        int allDate = 500;
        int check = 0;
        for(int i = 0;i<roadPlans.size();i++){
            if(Integer.parseInt(roadPlans.get(i).getAllWalk()) < meter){
                meter = Integer.parseInt(roadPlans.get(i).getAllWalk());
            }
            if(roadPlans.get(i).getCheck().equals("1")){
                check++;
                roadPlans.get(i).setLessTurn("lessTurn");
            }
            if(Integer.parseInt(roadPlans.get(i).getAllDate()) < allDate){
                System.out.println("11111");
                allDate = Integer.parseInt(roadPlans.get(i).getAllDate());
            }
        }

        for(int i = 0;i<roadPlans.size();i++){
            if(Integer.parseInt(roadPlans.get(i).getAllWalk()) == meter){
                roadPlans.get(i).setLessWalk("lessWalk");
            }
            if(check == 0 ){
                roadPlans.get(i).setLessTurn("lessTurn");
            }
            if(Integer.parseInt(roadPlans.get(i).getAllDate()) == allDate ){
                System.out.println("11111");
                roadPlans.get(i).setLessTime("lessTime");
            }
        }
        return roadPlans;
    }

//    @RequestMapping(value = "/getRealRunData")
//    public MyRealRun getRealRunData(String busNo, String direction, String stationName){
//        getRealData(busNo, direction, stationName);
//    }


    //经纬度计算距离
    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid) {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve =new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }

    public RealData getRealData(String busNo, String direction, String stationName){
        RealData realData = new RealData();
        Map<Double,RealData> meters1 = new HashMap<>();
        List<Double> lists = new ArrayList<>();
        MyStation myStation = stationService.queryStationName(direction);
        Integer dir = roadService.queryDirection(myStation.getStationId(), busNo);
        List<MyRealRun> myRealRuns = realRunService.queryRealRun(dir, busNo);
        MyStation sta = stationService.queryStationName(stationName);

        //拿到travelSort
        Road road = roadService.selectTravelSort(sta.getStationId(), busNo,dir);
        for(MyRealRun myRealRun : myRealRuns){
            MyStation sta1 = stationService.queryStationName(myRealRun.getNowStation());
            Road road1 = roadService.selectTravelSort(sta1.getStationId(), busNo,dir);
            if(road.getTravelSort() > road1.getTravelSort()){
                GlobalCoordinates target =new GlobalCoordinates(myRealRun.getRealYPoint(), myRealRun.getRealXPoint());
                GlobalCoordinates source =new GlobalCoordinates(Double.parseDouble(sta.getYPoint()), Double.parseDouble(sta.getXPoint()));
                double meter = getDistanceMeter(source,target, Ellipsoid.WGS84);
                lists.add(meter);
                RealData realData1 = new RealData();
                realData1.setMoneyStation(road.getTravelSort() - road1.getTravelSort());
                double time = ((meter)/(10/3.6))/60;
                realData1.setTime(String.valueOf(time).substring(0,String.valueOf(time).lastIndexOf("."))+"分钟");
                meters1.put(meter,realData1);
            }
        }
        if(lists.size() != 0){
            Double min1 = Collections.min(lists);
            for(Map.Entry<Double, RealData> entry : meters1.entrySet()){
                if(min1.equals(entry.getKey())){
                    realData = entry.getValue();
                }
            }
        }
        System.out.println(myRealRuns);
        System.out.println(stationName);
        System.out.println(lists);
        System.out.println(meters1);
        System.out.println(realData);
        return realData;
    }

}
