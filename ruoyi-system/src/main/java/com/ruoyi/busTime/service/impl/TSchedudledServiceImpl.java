package com.ruoyi.busTime.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busTime.mapper.TSchedudledMapper;
import com.ruoyi.busTime.domain.TSchedudled;
import com.ruoyi.busTime.service.ITSchedudleService;

/**
 * 公交车时刻Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-26
 */
@Service
public class TSchedudledServiceImpl implements ITSchedudleService
{
    @Autowired
    private TSchedudledMapper tSchedudleMapper;

    /**
     * 查询公交车时刻
     * 
     * @param scheduleid 公交车时刻ID
     * @return 公交车时刻
     */
    @Override
    public TSchedudled selectTSchedudleById(Long scheduleid)
    {
        return tSchedudleMapper.selectTSchedudleById(scheduleid);
    }

    /**
     * 查询公交车时刻列表
     * 
     * @param tSchedudle 公交车时刻
     * @return 公交车时刻
     */
    @Override
    public List<TSchedudled> selectTSchedudleList(TSchedudled tSchedudle)
    {
        return tSchedudleMapper.selectTSchedudleList(tSchedudle);
    }

    /**
     * 新增公交车时刻
     * 
     * @param tSchedudle 公交车时刻
     * @return 结果
     */
    @Override
    public int insertTSchedudle(TSchedudled tSchedudle)
    {
        return tSchedudleMapper.insertTSchedudle(tSchedudle);
    }

    /**
     * 修改公交车时刻
     * 
     * @param tSchedudle 公交车时刻
     * @return 结果
     */
    @Override
    public int updateTSchedudle(TSchedudled tSchedudle)
    {
        return tSchedudleMapper.updateTSchedudle(tSchedudle);
    }

    /**
     * 批量删除公交车时刻
     * 
     * @param scheduleids 需要删除的公交车时刻ID
     * @return 结果
     */
    @Override
    public int deleteTSchedudleByIds(Long[] scheduleids)
    {
        return tSchedudleMapper.deleteTSchedudleByIds(scheduleids);
    }

    /**
     * 删除公交车时刻信息
     * 
     * @param scheduleid 公交车时刻ID
     * @return 结果
     */
    @Override
    public int deleteTSchedudleById(Long scheduleid)
    {
        return tSchedudleMapper.deleteTSchedudleById(scheduleid);
    }
}
