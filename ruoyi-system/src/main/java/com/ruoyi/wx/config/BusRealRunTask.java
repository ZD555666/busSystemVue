package com.ruoyi.wx.config;

import com.ruoyi.wx.wxuser.domain.StationRoadVo;
import com.ruoyi.wx.wxuser.domain.WxBusInfo;
import com.ruoyi.wx.wxuser.domain.WxBusRealRun;
import com.ruoyi.wx.wxuser.domain.WxSchedule;
import com.ruoyi.wx.wxuser.mapper.WxBusInfoMapper;
import com.ruoyi.wx.wxuser.service.AddressService;
import com.ruoyi.wx.wxuser.service.WxBusInfoService;
import com.ruoyi.wx.wxuser.service.WxBusRealRunService;
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
public class BusRealRunTask implements SchedulingConfigurer {

    @Autowired
    private WxBusInfoService busInfoService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WxBusRealRunService realRunService;

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

    public void refreshTask(List<WxBusRealRun> tasks) {
        // 删除已经取消任务
        try {
            scheduledFutures.keySet().forEach(key -> {
                if (Objects.isNull(tasks) || tasks.size() == 0) {
                    scheduledFutures.get(key).cancel(false);
                    scheduledFutures.remove(key);
                    cronTasks.remove(key);
                    return;
                }
                tasks.forEach(task -> {
//                    log.info("scheduledFutures key {}", key, task.getRealrunId());
                    if (!Objects.equals(key, String.valueOf(task.getRealrunId()))) {
                        try {
                            log.info("！！！！！！！scheduledFutures key {} , {}", key, task.getRealrunId());
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        scheduledFutures.get(key).cancel(false);
                        scheduledFutures.remove(key);
                        cronTasks.remove(key);
                        return;
                    }
                });
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
            redisTemplate.opsForValue().set("isWait", true);
        }

        Lock lock = new ReentrantLock();

        // 添加新任务、更改执行规则任务
        tasks.forEach(txTask -> {
            try {
                log.info("定时任务传来的Task为：{} ", txTask);
                log.info("Futures里面的ID是{} {}", txTask.getRealrunId(), scheduledFutures.containsKey(String.valueOf(txTask.getRealrunId())));
                log.info("cronTasks：{} ", cronTasks.get(String.valueOf(txTask.getRealrunId())).getExpression());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            String expression = txTask.getExpression();
            // 任务表达式为空则跳过
            if (StringUtils.isEmpty(expression)) {
                return;
            }

//             任务已存在并且表达式未发生变化则跳过
            if (scheduledFutures.containsKey(String.valueOf(txTask.getRealrunId())) && cronTasks.get(String.valueOf(txTask.getRealrunId())).getExpression().equals(expression)) {
                log.info("任务已存在并且表达式未发生变化： {} {} {}", txTask.getRealrunId(), scheduledFutures.containsKey(String.valueOf(txTask.getRealrunId())), cronTasks.get(String.valueOf(txTask.getRealrunId())).getExpression().equals(expression));
                return;
            }

            // 任务执行时间发生了变化，则删除该任务
            if (scheduledFutures.containsKey(String.valueOf(txTask.getRealrunId()))) {
                log.info("任务执行时间发生了变化： {} ", txTask.getRealrunId());
                scheduledFutures.get(String.valueOf(txTask.getRealrunId())).cancel(false);
                scheduledFutures.remove(String.valueOf(txTask.getRealrunId()));
                cronTasks.remove(String.valueOf(txTask.getRealrunId()));
            }
            CronTask task = new CronTask(new Runnable() {
                @Override
                public void run() {
                    // 执行业务逻辑
                    try {
                        log.info("执行单个Bus任务，任务ID【{}】执行规则【{}】", txTask.getRealrunId(), txTask.getExpression());
                        System.out.println("==========================执行Bus任务=============================");
                        lock.lock();
                        WxBusRealRun newRun = realRunService.queryNewRunsByPlate(txTask.getLicensePlate());
                        newRun.setSpeed(txTask.getSpeed());
                        log.info("newRun is {}  taskSpeed is{}", newRun, txTask.getSpeed());
                        int travelSort = realRunService.queryTravelSort(newRun.getNowStation(), newRun.getDirection(), newRun.getBusNo(), newRun.getLicensePlate());
                        int next = travelSort + 2;
                        int hasNext = realRunService.queryHasNext(newRun.getBusNo(), newRun.getDirection(), newRun.getCityId(), next);
                        if (hasNext == 0) {
                            int num = next - 1;
                            if (realRunService.queryHasNext(newRun.getBusNo(), newRun.getDirection(), newRun.getCityId(), num) == 1) {
                                List<WxBusRealRun> list = realRunService.queryNextInfo(newRun.getBusNo(), newRun.getDirection(), num, num);
                                if (list.size() != 0) {
                                    list.get(0).setNowStation(list.get(0).getStationName());
                                    list.get(0).setNextStation(list.get(0).getStationName());
                                    list.get(0).setLicensePlate(newRun.getLicensePlate());
                                    list.get(0).setSpeed(newRun.getSpeed() > 40 ? 25 : newRun.getSpeed());
                                    realRunService.updateRealRun(list.get(0));
                                }
                            } else {
                                int ts = realRunService.queryTravelSort(newRun.getNextStation(), newRun.getDirection(), newRun.getBusNo(), newRun.getLicensePlate());
                                int realNext = ts + 1;
                                int hasSort = realRunService.queryHasNext(newRun.getBusNo(), newRun.getDirection(), newRun.getCityId(), realNext);
                                if (hasSort == 0) {
                                    log.info("删除的车牌是：{} 公交车是：{} 当前站点是：{} 方向是：{}", newRun.getLicensePlate(), newRun.getBusNo(), newRun.getNowStation(), newRun.getDirection());
                                    realRunService.delEndBus(newRun.getLicensePlate());
                                    realRunService.updateBusInfoState(newRun.getLicensePlate(), newRun.getDirection() == 0 ? 2 : 1);
                                }
                            }
                        } else {
                            int nowTravelSort = travelSort + 1;
                            int nextTravelSort = travelSort + 2;
                            List<WxBusRealRun> list = realRunService.queryNextInfo(newRun.getBusNo(), newRun.getDirection(), nowTravelSort, nextTravelSort);
                            log.info("找到的下一站为：{}", list);
                            if (list.size() != 0) {
                                list.get(0).setNowStation(list.get(0).getStationName());
                                list.get(0).setNextStation(list.get(1).getStationName());
                                list.get(0).setLicensePlate(newRun.getLicensePlate());
                                list.get(0).setSpeed(newRun.getSpeed());
//                                redisTemplate.opsForValue().set("isWait", false);
                                realRunService.updateRealRun(list.get(0));
//                                redisTemplate.opsForValue().set("isWait", true);
                            }
                        }
                        redisTemplate.opsForValue().set("isWait", true);
                        lock.unlock();
                    } catch (Exception e) {
                        lock.unlock();
                        e.printStackTrace();
                        redisTemplate.opsForValue().set("isWait", true);
                        log.error("执行发送消息任务异常，异常信息：{} 异常车辆 {}", e, txTask.getLicensePlate());
                    }
                }
            }, expression);
            ScheduledFuture<?> future = registrar.getScheduler().schedule(task.getRunnable(), task.getTrigger());
            cronTasks.put(String.valueOf(txTask.getRealrunId()), task);
            scheduledFutures.put(String.valueOf(txTask.getRealrunId()), future);
            try {
                log.info("id是 {} scheduledFutures后的东西是：{} ", txTask.getRealrunId(), scheduledFutures.get(String.valueOf(txTask.getRealrunId())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}