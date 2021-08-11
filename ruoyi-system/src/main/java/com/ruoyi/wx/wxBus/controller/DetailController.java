package com.ruoyi.wx.wxBus.controller;

import com.ruoyi.wx.wxBus.domain.Schedule;
import com.ruoyi.wx.wxBus.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

//    //获取发车时刻表
//    @RequestMapping(value = "/getTimeTable")
//    public Map<String, List<String>> getTimeTable(String busNo){
//        Map<String,List<String>> times = new HashMap<>();
//        List<Schedule> schedules = scheduleService.querySchedule(busNo);
//        for(Schedule schedule : schedules){
//            List<String> time = new ArrayList<>();
//            String timeQuantum = schedule.getStarTime()+":00-"+schedule.getEndTime()+":00";
//            String startTime = schedule.getStarTime()+":00";
//            int number = (schedule.getEndTime()-schedule.getStarTime())*60/schedule.getTimeInterval();
//            String timeAdd = startTime;
//            for(int i = 0;i<number-1;i++){
//                try {
//                    timeAdd = TimeAdd(timeAdd, String.valueOf(schedule.getTimeInterval()));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                time.add(timeAdd);
//            }
//            times.put(timeQuantum,time);
//
//        }
//        return times;
//    }
    //获取发车时刻表
    @RequestMapping(value = "/getTimeTable")
    public List<String> getTimeTable(String busNo){
        List<String> times = new ArrayList<>();
        List<Schedule> schedules = scheduleService.querySchedule(busNo);
        for(int i = 0;i<schedules.size();i++){
            if(i == 0){
                String timeQuantum = schedules.get(i).getStarTime()+":00-"+schedules.get(i).getEndTime()+":00 (早高峰)";
                times.add(timeQuantum);
            }
            if(i == 1){
                String timeQuantum = schedules.get(i).getStarTime()+":00-"+schedules.get(i).getEndTime()+":00";
                times.add(timeQuantum);
            }
            if(i == 2){
                String timeQuantum = schedules.get(i).getStarTime()+":00-"+schedules.get(i).getEndTime()+":00 (晚高峰)";
                times.add(timeQuantum);
            }
            if(i > 2){
                String timeQuantum = schedules.get(i).getStarTime()+":00-"+schedules.get(i).getEndTime()+":00";
                times.add(timeQuantum);
            }

        }
//        for(Schedule schedule : schedules){
//            String timeQuantum = schedule.getStarTime()+":00-"+schedule.getEndTime()+":00";
//            times.add(timeQuantum);
//        }
        return times;
    }

    //获取发车时刻表
    @RequestMapping(value = "/getTimes")
    public List<List<String>> getTimes(String busNo){
        List<List<String>> times = new ArrayList<>();
        List<Schedule> schedules = scheduleService.querySchedule(busNo);
        for(Schedule schedule : schedules){
            List<String> time = new ArrayList<>();
            String startTime = schedule.getStarTime()+":00";
            int number = (schedule.getEndTime()-schedule.getStarTime())*60/schedule.getTimeInterval();
            String timeAdd = startTime;
            for(int i = 0;i<number-1;i++){
                try {
                    timeAdd = TimeAdd(timeAdd, String.valueOf(schedule.getTimeInterval()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                time.add(timeAdd);
            }
            times.add(time);

        }
        return times;
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
}
