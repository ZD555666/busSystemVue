package com.ruoyi.wx.config;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.HttpClientUtil;
import com.ruoyi.wx.wxuser.domain.*;
import com.ruoyi.wx.wxuser.service.AddressService;
import com.ruoyi.wx.wxuser.service.WxBusInfoService;
import com.ruoyi.wx.wxuser.service.WxBusRealRunService;
import com.ruoyi.wx.wxuser.service.WxScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 14:21
 * @desc:
 */
@Service
@EnableScheduling
public class ScheduledConfig {

    public static final String ak = "KuEqKpyYQztElOnuetq8SDzX7Gn6qWiO";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WxScheduleService scheduleService;

    @Autowired
    private ScheduledTask scheduledTask;

    @Autowired
    private BusRealRunTask realRunTask;

    @Autowired
    private AddressService addressService;

    @Autowired
    private WxBusRealRunService realRunService;

    @Scheduled(cron = "0 59 12 ? * *")
    public void querySchedule() {
//        redisTemplate.opsForValue().set("num", 1);
        List<WxSchedule> wxSchedules = scheduleService.queryScheduled();
        for (WxSchedule wxSchedule : wxSchedules) {
            wxSchedule.setExpression("0 */" + wxSchedule.getTimeInterval() + " " + wxSchedule.getStarTime() + "-" + wxSchedule.getEndTime() + " * * ?");
        }
        //  0 */10 7 * * ?
        redisTemplate.opsForValue().set("isWait", true);
        scheduledTask.refreshTask(wxSchedules);
    }

    @Scheduled(cron = "45 * * * * ?")
    public void doBus() {
        String base_url = "https://api.map.baidu.com/routematrix/v2/driving?output=json&origins=";
        if ((Boolean) redisTemplate.opsForValue().get("isWait") == true) {
            List<WxBusRealRun> wxBusRealRuns = realRunService.queryAllRunBus();
            System.err.println(wxBusRealRuns + "<<<<<<<<<<<<<<<==========");
            for (WxBusRealRun wxBusRealRun : wxBusRealRuns) {
                WxBusRealRun nowStation = realRunService.queryNowStationXy(wxBusRealRun.getNowStation());
                WxBusRealRun nextStation = realRunService.queryNextStationXy(wxBusRealRun.getNextStation());
                String url = base_url + nowStation.getYPoint() + "," + nowStation.getXPoint() + "&destinations=" + nextStation.getYPoint() + "," + nextStation.getXPoint() + "&tactics=12&ak=" + ak;
                System.out.println(url);
                String wxResult = HttpClientUtil.doGet(url);
//                System.out.println(wxResult);
                BaiDuRes res = JSONObject.parseObject(wxResult, BaiDuRes.class);
                double timeValue = res.getResult().get(0).getDuration().getValue();
                wxBusRealRun.setSpeed((res.getResult().get(0).getDistance().getValue() / 1000) / (timeValue / 3600));
                int i = (int) ((timeValue / 60) < 1 ? 1 : timeValue / 60);
                System.out.println(i);
                wxBusRealRun.setExpression("0 0/" + i + " * * * ?");
//            System.err.println(res);
            }
            System.out.println(wxBusRealRuns);
            redisTemplate.opsForValue().set("isWait", false);
            if (wxBusRealRuns.size() == 0) redisTemplate.opsForValue().set("isWait", true);
            realRunTask.refreshTask(wxBusRealRuns);
        } else {
            System.out.println("======>>>wait Update<<<=====");
        }


    }


}
