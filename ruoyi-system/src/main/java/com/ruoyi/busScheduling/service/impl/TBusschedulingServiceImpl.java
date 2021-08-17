package com.ruoyi.busScheduling.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busScheduling.mapper.TBusschedulingMapper;
import com.ruoyi.busScheduling.domain.TBusscheduling;
import com.ruoyi.busScheduling.service.ITBusschedulingService;

/**
 * busschedulingService业务层处理
 * 
 * @author 林
 * @date 2021-08-15
 */
@Service
public class TBusschedulingServiceImpl implements ITBusschedulingService 
{
    @Autowired
    private TBusschedulingMapper tBusschedulingMapper;

    /**
     * 查询busscheduling
     * 
     * @param busid busschedulingID
     * @return busscheduling
     */
    @Override
    public TBusscheduling selectTBusschedulingById(Long busid)
    {
        return tBusschedulingMapper.selectTBusschedulingById(busid);
    }

    /**
     * 查询busscheduling列表
     * 
     * @param tBusscheduling busscheduling
     * @return busscheduling
     */
    @Override
    public List<TBusscheduling> selectTBusschedulingList(TBusscheduling tBusscheduling)
    {
        return tBusschedulingMapper.selectTBusschedulingList(tBusscheduling);
    }
    public List<TBusscheduling> selectTBusschedulingList1(TBusscheduling tBusscheduling)
    {
        return tBusschedulingMapper.selectTBusschedulingList1(tBusscheduling);
    }
    public List<TBusscheduling> selectTBusschedulingList2(TBusscheduling tBusscheduling)
    {
        return tBusschedulingMapper.selectTBusschedulingList2(tBusscheduling);
    }

    /**
     * 新增busscheduling
     * 
     * @param tBusscheduling busscheduling
     * @return 结果
     */
    @Override
    public int insertTBusscheduling(TBusscheduling tBusscheduling)
    {
        return tBusschedulingMapper.insertTBusscheduling(tBusscheduling);
    }

    /**
     * 修改busscheduling
     * 
     * @param tBusscheduling busscheduling
     * @return 结果
     */
    @Override
    public int updateTBusscheduling(TBusscheduling tBusscheduling)
    {
        return tBusschedulingMapper.updateTBusscheduling(tBusscheduling);
    }

    /**
     * 批量删除busscheduling
     * 
     * @param busids 需要删除的busschedulingID
     * @return 结果
     */
    @Override
    public int deleteTBusschedulingByIds(Long[] busids)
    {
        return tBusschedulingMapper.deleteTBusschedulingByIds(busids);
    }

    /**
     * 删除busscheduling信息
     * 
     * @param busid busschedulingID
     * @return 结果
     */
    @Override
    public int deleteTBusschedulingById(Long busid)
    {
        return tBusschedulingMapper.deleteTBusschedulingById(busid);
    }
}
