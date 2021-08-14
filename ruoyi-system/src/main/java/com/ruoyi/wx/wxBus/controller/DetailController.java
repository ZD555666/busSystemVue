package com.ruoyi.wx.wxBus.controller;

import com.ruoyi.wx.wxBus.domain.*;
import com.ruoyi.wx.wxBus.service.impl.RealRunServiceImpl;
import com.ruoyi.wx.wxBus.service.impl.RoadServiceImpl;
import com.ruoyi.wx.wxBus.service.impl.ScheduleServiceImpl;
import com.ruoyi.wx.wxBus.service.impl.StationServiceImpl;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/10 19:25
 * @desc:
 */
@RestController
@RequestMapping(value = "/wx")
public class DetailController {

    @Autowired
    private ScheduleServiceImpl scheduleService;
    @Autowired
    private RoadServiceImpl roadService;
    @Autowired
    private StationServiceImpl stationService;
    @Autowired
    private RealRunServiceImpl realRunService;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    //获取发车时刻表
    @RequestMapping(value = "/getTimeTable")
    public List<String> getTimeTable(String busNo){
        List<String> times = new ArrayList<>();
        //使用redis缓存
        String redisKey = "Schedule"+busNo;
        List<MySchedule> mySchedules = (List<MySchedule>)redisTemplate.opsForValue().get(redisKey);
        if(mySchedules == null){
            synchronized (this){
                if(mySchedules == null){
                    //从数据库查询
                    mySchedules = scheduleService.querySchedule(busNo);
                    redisTemplate.opsForValue().set(redisKey,mySchedules);
                }
            }
        }
        for(int i = 0; i< mySchedules.size(); i++){
            if(mySchedules.get(i).getStarTime() >=6 &&  mySchedules.get(i).getEndTime() <= 11  ){
                String timeQuantum = mySchedules.get(i).getStarTime()+":00-"+ mySchedules.get(i).getEndTime()+":00 (早高峰)";
                times.add(timeQuantum);
            }
            if(mySchedules.get(i).getStarTime() >=8 &&  mySchedules.get(i).getEndTime() <= 16 ){
                String timeQuantum = mySchedules.get(i).getStarTime()+":00-"+ mySchedules.get(i).getEndTime()+":00";
                times.add(timeQuantum);
            }
            if(mySchedules.get(i).getStarTime() >= 15 &&  mySchedules.get(i).getEndTime() <= 21 ){
                String timeQuantum = mySchedules.get(i).getStarTime()+":00-"+ mySchedules.get(i).getEndTime()+":00 (晚高峰)";
                times.add(timeQuantum);
            }
            if(mySchedules.get(i).getStarTime() >= 18 &&  mySchedules.get(i).getEndTime() <= 24 ){
                String timeQuantum = mySchedules.get(i).getStarTime()+":00-"+ mySchedules.get(i).getEndTime()+":00 ";
                times.add(timeQuantum);
            }
        }
        return times;
    }

    //获取发车时刻表
    @RequestMapping(value = "/getTimes")
    public List<List<String>> getTimes(String busNo){
        List<List<String>> times = new ArrayList<>();
        //redis缓存
        String redisKey = "Schedule"+busNo;
        List<MySchedule> mySchedules = (List<MySchedule>)redisTemplate.opsForValue().get(redisKey);
        if(mySchedules == null){
            synchronized (this){
                if(mySchedules == null){
                    //从数据库查询
                    mySchedules = scheduleService.querySchedule(busNo);
                    redisTemplate.opsForValue().set(redisKey,mySchedules);
                }
            }
        }
        for(MySchedule mySchedule : mySchedules){
            List<String> time = new ArrayList<>();
            String startTime = mySchedule.getStarTime()+":00";
            int number = (mySchedule.getEndTime()- mySchedule.getStarTime())*60/ mySchedule.getTimeInterval();
            String timeAdd = startTime;
            for(int i = 0;i<number-1;i++){
                try {
                    timeAdd = TimeAdd(timeAdd, String.valueOf(mySchedule.getTimeInterval()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                time.add(timeAdd);
            }
            times.add(time);

        }
        return times;
    }

    //获取公交开始和结束的时间
    @RequestMapping(value = "/getStaEndTime")
    public List<String> getStaEndTime(String busNo){
        List<String> time = new ArrayList<>();
        //redis缓存
        String redisKey = "Schedule"+busNo;
        List<MySchedule> mySchedules = (List<MySchedule>)redisTemplate.opsForValue().get(redisKey);
        if(mySchedules == null){
            synchronized (this){
                if(mySchedules == null){
                    //从数据库查询
                    mySchedules = scheduleService.querySchedule(busNo);
                    redisTemplate.opsForValue().set(redisKey,mySchedules);
                }
            }
        }
        if(mySchedules != null){
            String startTime = mySchedules.get(0).getStarTime()+":00";
            int check = 60-mySchedules.get(mySchedules.size()-1).getTimeInterval();
            String endTime = (mySchedules.get(mySchedules.size()-1).getEndTime()-1)+":"+check;
            time.add(startTime);time.add(endTime);
            return time;
        }else{
            return null;
        }

    }

    //获取公交每个时刻对应出发的时间
    public String getStartTime(String busNo){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        String format = df.format(new Date());
        System.out.println(format);
        //redis缓存
        String redisKey = "Schedule"+busNo;
        List<MySchedule> mySchedules = (List<MySchedule>)redisTemplate.opsForValue().get(redisKey);
        if(mySchedules == null){
            synchronized (this){
                if(mySchedules == null){
                    //从数据库查询
                    mySchedules = scheduleService.querySchedule(busNo);
                    redisTemplate.opsForValue().set(redisKey,mySchedules);
                }
            }
        }
        String depart = "";
        int check = 0;
        for(MySchedule mySchedule : mySchedules){
           String startTime =  mySchedule.getStarTime()+":00";
           String endTime =  mySchedule.getEndTime()+":00";
           if(compareTime(format,startTime) && compareTime(endTime,format)){
               check = 1;
               int number = (mySchedule.getEndTime()- mySchedule.getStarTime())*60/ mySchedule.getTimeInterval();
               String timeAdd = startTime;
               for(int i = 0;i<number;i++){
                   try {
                       timeAdd = TimeAdd(timeAdd, String.valueOf(mySchedule.getTimeInterval()));
                       if(compareTime(timeAdd,format)){
                           depart = timeAdd;
                           break;
                       }
                   } catch (ParseException e) {
                       e.printStackTrace();
                   }
               }
           }
        }
        if(check == 1){
            return depart;
        }else{
            return mySchedules.get(0).getStarTime()+":00";
        }
    }

    //数据实时更新
    @RequestMapping(value = "/getRealData")
    public RealData getRealData(String busNo,
                                String latitude,
                                String longitude,
                                String direction){
        //使用redis缓存
        String redisRoads = "Roads"+busNo;
        List<Road> roads = (List<Road>)redisTemplate.opsForValue().get(redisRoads);
        if(roads == null){
            synchronized (this){
                if(roads == null){
                    //从数据库查询
                    roads = roadService.queryRoads(busNo);
                    redisTemplate.opsForValue().set(redisRoads,roads);
                }
            }
        }
        Map<String,Double> meters = new HashMap<>();
        List<Double> list = new ArrayList<>();
        String stationName = "";
        for(Road road : roads){
            //拿到离起点最近的站点
            GlobalCoordinates target =new GlobalCoordinates(Double.parseDouble(latitude), Double.parseDouble(longitude));
            GlobalCoordinates source =new GlobalCoordinates(Double.parseDouble(road.getStation().getYPoint()), Double.parseDouble(road.getStation().getXPoint()));
            double meter = getDistanceMeter(source, target, Ellipsoid.WGS84);
            System.out.println(meter);
            list.add(meter);
            meters.put(road.getStation().getStationName(),meter);
        }

        Double min = Collections.min(list);
        for(Map.Entry<String,Double> entry : meters.entrySet()){
            if(min.equals(entry.getValue())){
                stationName = entry.getKey();
            }
        }
        RealData realData = new RealData();
        //获取每个时刻的出发时间
        String startTime = getStartTime(busNo);
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
                //拿到离起点最近的站点
                GlobalCoordinates target =new GlobalCoordinates(myRealRun.getRealYPoint(), myRealRun.getRealXPoint());
                GlobalCoordinates source =new GlobalCoordinates(Double.parseDouble(sta.getYPoint()), Double.parseDouble(sta.getXPoint()));
                double meter = getDistanceMeter(source,target, Ellipsoid.WGS84);
                lists.add(meter);
                RealData realData1 = new RealData();
                double nowMe = 0.0;
                if(meter > 1000){
                    nowMe = meter/1000;
                    realData1.setMeter(String.valueOf(nowMe).substring(0,(String.valueOf(nowMe).lastIndexOf("."))+2)+"公里");
                }else{
                    realData1.setMeter(String.valueOf(meter).substring(0,String.valueOf(meter).lastIndexOf("."))+"米");
                }
                realData1.setMoneyStation(road.getTravelSort() - road1.getTravelSort());
                double time = ((meter)/(10/3.6))/60;
                MyStation mysta = stationService.queryStationName(myRealRun.getNowStation());
                Road road2 = roadService.selectTravelSort(mysta.getStationId(), busNo, dir);
                realData1.setNowStation(road2.getTravelSort());
                realData1.setTime(String.valueOf(time).substring(0,String.valueOf(time).lastIndexOf("."))+"分钟");
                meters1.put(meter,realData1);
            }
        }
        if(lists.size() != 0){
            Double min1 = Collections.min(lists);
            for(Map.Entry<Double,RealData> entry : meters1.entrySet()){
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
        realData.setNearStation(stationName);
        realData.setStartTime(startTime);
        return realData;
    }

    //获取时间间隔的下一个时间
    public static String  TimeAdd(String oldTime,String add) throws ParseException {
        int addMit = Integer.valueOf(add);
        DateFormat df = new SimpleDateFormat("HH:mm");
        Date date = df.parse(oldTime);
        Date expireTime = new Date(date.getTime() + addMit*60*1000);
        String newTime = df.format(expireTime);
        return newTime;
    }

    //比较时间的大小
    public static boolean compareTime(String time1,String time2) {
        // 比较 时 分
        DateFormat df = new SimpleDateFormat("HH:mm");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = df.parse(time1);
            date2 = df.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1.getTime() >= date2.getTime();
    }

    //经纬度计算距离
    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid) {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve =new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }
}
