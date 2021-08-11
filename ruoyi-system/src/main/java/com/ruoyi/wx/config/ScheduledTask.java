package com.ruoyi.wx.config;

import com.ruoyi.wx.wxuser.domain.WxBusInfo;
import com.ruoyi.wx.wxuser.domain.WxSchedule;
import com.ruoyi.wx.wxuser.mapper.WxBusInfoMapper;
import com.ruoyi.wx.wxuser.service.WxBusInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component
public class ScheduledTask implements SchedulingConfigurer {

    @Autowired
    private WxBusInfoService busInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    private volatile ScheduledTaskRegistrar registrar;

    private final ConcurrentHashMap<String, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, CronTask> cronTasks = new ConcurrentHashMap<>();

    /**
     * 默认启动10个线程
     */
    private static final Integer DEFAULT_THREAD_POOL = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.setScheduler(Executors.newScheduledThreadPool(DEFAULT_THREAD_POOL));
        this.registrar = registrar;
    }

    @PreDestroy
    public void destroy() {
        this.registrar.destroy();
    }

    /**
     * @methodName：refreshTask
     * @description：初始化任务 1、从数据库获取执行任务的集合【TxTask】
     * 2、通过调用 【refresh】 方法刷新任务列表
     * 3、每次数据库中的任务发生变化后重新执行【1、2】
     * @Params： [tasks]
     * @Return： void
     * @editNote：
     */
    public void refreshTask(List<WxSchedule> tasks) {
        // 删除已经取消任务
        scheduledFutures.keySet().forEach(key -> {
            if (Objects.isNull(tasks) || tasks.size() == 0) {
                scheduledFutures.get(key).cancel(false);
                scheduledFutures.remove(key);
                cronTasks.remove(key);
                return;
            }
            tasks.forEach(task -> {
                if (!Objects.equals(key, String.valueOf(task.getScheduleId()))) {
                    scheduledFutures.get(key).cancel(false);
                    scheduledFutures.remove(key);
                    cronTasks.remove(key);
                    return;
                }
            });
        });

        Lock lock = new ReentrantLock();

        // 添加新任务、更改执行规则任务
        tasks.forEach(txTask -> {
            String expression = txTask.getExpression();
            // 任务表达式为空则跳过
            if (StringUtils.isEmpty(expression)) {
                return;
            }

            // 任务已存在并且表达式未发生变化则跳过
            if (scheduledFutures.containsKey(String.valueOf(txTask.getScheduleId())) && cronTasks.get(String.valueOf(txTask.getScheduleId())).getExpression().equals(expression)) {
                return;
            }

            // 任务执行时间发生了变化，则删除该任务
            if (scheduledFutures.containsKey(String.valueOf(txTask.getScheduleId()))) {
                scheduledFutures.get(String.valueOf(txTask.getScheduleId())).cancel(false);
                scheduledFutures.remove(String.valueOf(txTask.getScheduleId()));
                cronTasks.remove(String.valueOf(txTask.getScheduleId()));
            }
            log.info("redisCache {}", redisTemplate.opsForValue().get("num"));
//            final Integer[] i = {0};

            CronTask task = new CronTask(new Runnable() {
                @Override
                public void run() {
                    // 执行业务逻辑
                    try {
                        log.info("执行单个任务，任务ID【{}】执行规则【{}】", txTask.getScheduleId(), txTask.getExpression());
                        lock.lock();
                        List<WxBusInfo> wxBusInfos = busInfoService.queryBus(txTask.getBusNo(), 1, 2);
                        System.err.println(wxBusInfos);
                        int num = (int) redisTemplate.opsForValue().get("num");
                        log.info("num==>{}", num);
                        if (num < tasks.size()) {
                            num = num + 1;
                            redisTemplate.opsForValue().set("num", num);
                            log.info("num======================================>{}", num);
                            log.info("redisCache====>>>num{}",redisTemplate.opsForValue().get("num"));
                            WxBusInfo wxBusInfo = wxBusInfos.get(0);

                            int i = busInfoService.insertRealRunBus(wxBusInfo);
                            if (i == 1) {
                                busInfoService.updBusState(wxBusInfo.getLicensePlate(), 0);
                                lock.unlock();
                            }
                        }else {
                            return;
                        }


                        System.out.println("==========================执行任务=============================");
                    } catch (Exception e) {
                        log.error("执行发送消息任务异常，异常信息：{}", e);
                    }
                }
            }, expression);
            ScheduledFuture<?> future = registrar.getScheduler().schedule(task.getRunnable(), task.getTrigger());
            cronTasks.put(String.valueOf(txTask.getScheduleId()), task);
            scheduledFutures.put(String.valueOf(txTask.getScheduleId()), future);
        });
    }

}