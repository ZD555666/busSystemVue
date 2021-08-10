package com.ruoyi.road.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.road.mapper.TScheduleMapper;
import com.ruoyi.road.domain.TSchedule;
import com.ruoyi.road.service.ITScheduleService;

/**
 * 线路发车时刻配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-10
 */
@Service
public class TScheduleServiceImpl implements ITScheduleService 
{
    @Autowired
    private TScheduleMapper tScheduleMapper;

    /**
     * 查询线路发车时刻配置
     * 
     * @param scheduleId 线路发车时刻配置ID
     * @return 线路发车时刻配置
     */
    @Override
    public TSchedule selectTScheduleById(Integer scheduleId)
    {
        return tScheduleMapper.selectTScheduleById(scheduleId);
    }

    /**
     * 查询线路发车时刻配置列表
     * 
     * @param tSchedule 线路发车时刻配置
     * @return 线路发车时刻配置
     */
    @Override
    public List<TSchedule> selectTScheduleList(TSchedule tSchedule)
    {
        return tScheduleMapper.selectTScheduleList(tSchedule);
    }

    /**
     * 新增线路发车时刻配置
     * 
     * @param tSchedule 线路发车时刻配置
     * @return 结果
     */
    @Override
    public int insertTSchedule(TSchedule tSchedule)
    {
        return tScheduleMapper.insertTSchedule(tSchedule);
    }

    /**
     * 修改线路发车时刻配置
     * 
     * @param tSchedule 线路发车时刻配置
     * @return 结果
     */
    @Override
    public int updateTSchedule(TSchedule tSchedule)
    {
        return tScheduleMapper.updateTSchedule(tSchedule);
    }

    /**
     * 批量删除线路发车时刻配置
     * 
     * @param scheduleIds 需要删除的线路发车时刻配置ID
     * @return 结果
     */
    @Override
    public int deleteTScheduleByIds(Integer[] scheduleIds)
    {
        return tScheduleMapper.deleteTScheduleByIds(scheduleIds);
    }

    /**
     * 删除线路发车时刻配置信息
     * 
     * @param scheduleId 线路发车时刻配置ID
     * @return 结果
     */
    @Override
    public int deleteTScheduleById(Integer scheduleId)
    {
        return tScheduleMapper.deleteTScheduleById(scheduleId);
    }
}
