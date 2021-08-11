package com.ruoyi.wx.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.wx.wxuser.domain.MyTask;
import com.ruoyi.wx.wxuser.domain.WxSchedule;
import com.ruoyi.wx.wxuser.service.WxScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WxScheduleService scheduleService;

    @Autowired
    private ScheduledTask scheduledTask;

    @Scheduled(cron = "50 45 1 ? * *")
    public void querySchedule() {
        redisTemplate.opsForValue().set("num", 0);
        List<WxSchedule> wxSchedules = scheduleService.queryScheduled(1, 2);
        for (WxSchedule wxSchedule : wxSchedules) {
            wxSchedule.setExpression("*/" + wxSchedule.getTimeInterval() + " * * * * ?");
        }
        scheduledTask.refreshTask(wxSchedules);
//        redisTemplate.opsForValue().set("schedules", wxSchedules, 22, TimeUnit.HOURS);
    }


}
