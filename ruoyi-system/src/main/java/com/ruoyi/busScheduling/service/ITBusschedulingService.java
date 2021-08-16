package com.ruoyi.busScheduling.service;

import java.util.List;
import com.ruoyi.busScheduling.domain.TBusscheduling;

/**
 * busschedulingService接口
 * 
 * @author 林
 * @date 2021-08-15
 */
public interface ITBusschedulingService 
{
    /**
     * 查询busscheduling
     * 
     * @param busid busschedulingID
     * @return busscheduling
     */
    public TBusscheduling selectTBusschedulingById(Long busid);

    /**
     * 查询busscheduling列表
     * 
     * @param tBusscheduling busscheduling
     * @return busscheduling集合
     */
    public List<TBusscheduling> selectTBusschedulingList(TBusscheduling tBusscheduling);
    public List<TBusscheduling> selectTBusschedulingList1(TBusscheduling tBusscheduling);
    public List<TBusscheduling> selectTBusschedulingList2(TBusscheduling tBusscheduling);
    /**
     * 新增busscheduling
     * 
     * @param tBusscheduling busscheduling
     * @return 结果
     */
    public int insertTBusscheduling(TBusscheduling tBusscheduling);

    /**
     * 修改busscheduling
     * 
     * @param tBusscheduling busscheduling
     * @return 结果
     */
    public int updateTBusscheduling(TBusscheduling tBusscheduling);

    /**
     * 批量删除busscheduling
     * 
     * @param busids 需要删除的busschedulingID
     * @return 结果
     */
    public int deleteTBusschedulingByIds(Long[] busids);

    /**
     * 删除busscheduling信息
     * 
     * @param busid busschedulingID
     * @return 结果
     */
    public int deleteTBusschedulingById(Long busid);
}
