package com.ruoyi.publicBusManage.departureSchedule.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.publicBusManage.departureSchedule.mapper.TSchedudleMapper;
import com.ruoyi.publicBusManage.departureSchedule.domain.TSchedudle;
import com.ruoyi.publicBusManage.departureSchedule.service.ITSchedudleService;

/**
 * 发车时刻表Service业务层处理
 *
 * @author ym
 * @date 2021-07-28
 */
@Service
public class TSchedudleServiceImpl implements ITSchedudleService
{
    @Autowired
    private TSchedudleMapper tSchedudleMapper;

    /**
     * 查询发车时刻表
     *
     * @param scheduleid 发车时刻表ID
     * @return 发车时刻表
     */
    @Override
    public TSchedudle selectTSchedudleById(Long scheduleid)
    {
        return tSchedudleMapper.selectTSchedudleById(scheduleid);
    }

    /**
     * 查询发车时刻表列表
     *
     * @param tSchedudle 发车时刻表
     * @return 发车时刻表
     */
    @Override
    public List<TSchedudle> selectTSchedudleList(TSchedudle tSchedudle)
    {
        return tSchedudleMapper.selectTSchedudleList(tSchedudle);
    }

    /**
     * 新增发车时刻表
     *
     * @param tSchedudle 发车时刻表
     * @return 结果
     */
    @Override
    public int insertTSchedudle(TSchedudle tSchedudle)
    {
        return tSchedudleMapper.insertTSchedudle(tSchedudle);
    }

    /**
     * 修改发车时刻表
     *
     * @param tSchedudle 发车时刻表
     * @return 结果
     */
    @Override
    public int updateTSchedudle(TSchedudle tSchedudle)
    {
        return tSchedudleMapper.updateTSchedudle(tSchedudle);
    }

    /**
     * 批量删除发车时刻表
     *
     * @param scheduleids 需要删除的发车时刻表ID
     * @return 结果
     */
    @Override
    public int deleteTSchedudleByIds(Long[] scheduleids)
    {
        return tSchedudleMapper.deleteTSchedudleByIds(scheduleids);
    }

    /**
     * 删除发车时刻表信息
     *
     * @param scheduleid 发车时刻表ID
     * @return 结果
     */
    @Override
    public int deleteTSchedudleById(Long scheduleid)
    {
        return tSchedudleMapper.deleteTSchedudleById(scheduleid);
    }
}
