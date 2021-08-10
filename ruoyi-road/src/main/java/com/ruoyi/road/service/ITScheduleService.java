package com.ruoyi.road.service;

import java.util.List;
import com.ruoyi.road.domain.TSchedule;

/**
 * 线路发车时刻配置Service接口
 * 
 * @author ruoyi
 * @date 2021-08-10
 */
public interface ITScheduleService 
{
    /**
     * 查询线路发车时刻配置
     * 
     * @param scheduleId 线路发车时刻配置ID
     * @return 线路发车时刻配置
     */
    public TSchedule selectTScheduleById(Integer scheduleId);

    /**
     * 查询线路发车时刻配置列表
     * 
     * @param tSchedule 线路发车时刻配置
     * @return 线路发车时刻配置集合
     */
    public List<TSchedule> selectTScheduleList(TSchedule tSchedule);

    /**
     * 新增线路发车时刻配置
     * 
     * @param tSchedule 线路发车时刻配置
     * @return 结果
     */
    public int insertTSchedule(TSchedule tSchedule);

    /**
     * 修改线路发车时刻配置
     * 
     * @param tSchedule 线路发车时刻配置
     * @return 结果
     */
    public int updateTSchedule(TSchedule tSchedule);

    /**
     * 批量删除线路发车时刻配置
     * 
     * @param scheduleIds 需要删除的线路发车时刻配置ID
     * @return 结果
     */
    public int deleteTScheduleByIds(Integer[] scheduleIds);

    /**
     * 删除线路发车时刻配置信息
     * 
     * @param scheduleId 线路发车时刻配置ID
     * @return 结果
     */
    public int deleteTScheduleById(Integer scheduleId);
}
