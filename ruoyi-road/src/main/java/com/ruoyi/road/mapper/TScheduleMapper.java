package com.ruoyi.road.mapper;

import java.util.List;
import com.ruoyi.road.domain.TSchedule;
import org.apache.ibatis.annotations.Mapper;

/**
 * 线路发车时刻配置Mapper接口
 * 
 * @author ruoyi
 * @date 2021-08-10
 */
@Mapper
public interface TScheduleMapper 
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
     * 删除线路发车时刻配置
     * 
     * @param scheduleId 线路发车时刻配置ID
     * @return 结果
     */
    public int deleteTScheduleById(Integer scheduleId);

    /**
     * 批量删除线路发车时刻配置
     * 
     * @param scheduleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTScheduleByIds(Integer[] scheduleIds);
}
