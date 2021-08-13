package com.ruoyi.publicBusManage.departureSchedule.mapper;

import java.util.List;
import com.ruoyi.publicBusManage.departureSchedule.domain.TSchedudle;
import org.springframework.stereotype.Repository;

/**
 * 发车时刻表Mapper接口
 *
 * @author ym
 * @date 2021-07-28
 */
@Repository
public interface TSchedudleMapper
{
    /**
     * 查询发车时刻表
     *
     * @param scheduleid 发车时刻表ID
     * @return 发车时刻表
     */
    public TSchedudle selectTSchedudleById(Long scheduleid);

    /**
     * 查询发车时刻表列表
     *
     * @param tSchedudle 发车时刻表
     * @return 发车时刻表集合
     */
    public List<TSchedudle> selectTSchedudleList(TSchedudle tSchedudle);

    /**
     * 新增发车时刻表
     *
     * @param tSchedudle 发车时刻表
     * @return 结果
     */
    public int insertTSchedudle(TSchedudle tSchedudle);

    /**
     * 修改发车时刻表
     *
     * @param tSchedudle 发车时刻表
     * @return 结果
     */
    public int updateTSchedudle(TSchedudle tSchedudle);

    /**
     * 删除发车时刻表
     *
     * @param scheduleid 发车时刻表ID
     * @return 结果
     */
    public int deleteTSchedudleById(Long scheduleid);

    /**
     * 批量删除发车时刻表
     *
     * @param scheduleids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTSchedudleByIds(Long[] scheduleids);
}
