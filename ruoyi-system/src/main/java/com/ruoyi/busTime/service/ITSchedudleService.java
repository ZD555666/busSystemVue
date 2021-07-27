package com.ruoyi.busTime.service;

import java.util.List;
import com.ruoyi.busTime.domain.TSchedudle;

/**
 * 公交车时刻Service接口
 * 
 * @author ruoyi
 * @date 2021-07-26
 */
public interface ITSchedudleService 
{
    /**
     * 查询公交车时刻
     * 
     * @param scheduleid 公交车时刻ID
     * @return 公交车时刻
     */
    public TSchedudle selectTSchedudleById(Long scheduleid);

    /**
     * 查询公交车时刻列表
     * 
     * @param tSchedudle 公交车时刻
     * @return 公交车时刻集合
     */
    public List<TSchedudle> selectTSchedudleList(TSchedudle tSchedudle);

    /**
     * 新增公交车时刻
     * 
     * @param tSchedudle 公交车时刻
     * @return 结果
     */
    public int insertTSchedudle(TSchedudle tSchedudle);

    /**
     * 修改公交车时刻
     * 
     * @param tSchedudle 公交车时刻
     * @return 结果
     */
    public int updateTSchedudle(TSchedudle tSchedudle);

    /**
     * 批量删除公交车时刻
     * 
     * @param scheduleids 需要删除的公交车时刻ID
     * @return 结果
     */
    public int deleteTSchedudleByIds(Long[] scheduleids);

    /**
     * 删除公交车时刻信息
     * 
     * @param scheduleid 公交车时刻ID
     * @return 结果
     */
    public int deleteTSchedudleById(Long scheduleid);
}
