package com.ruoyi.busRepair.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busRepair.mapper.TRepairMapper;
import com.ruoyi.busRepair.domain.TRepair;
import com.ruoyi.busRepair.service.ITRepairService;

/**
 * 车辆维修Service业务层处理
 * 
 * @author lcy
 * @date 2021-07-23
 */
@Service
public class TRepairServiceImpl implements ITRepairService 
{
    @Autowired
    private TRepairMapper tRepairMapper;

    /**
     * 查询车辆维修
     * 
     * @param repairid 车辆维修ID
     * @return 车辆维修
     */
    @Override
    public TRepair selectTRepairById(Long repairid)
    {
        return tRepairMapper.selectTRepairById(repairid);
    }

    /**
     * 查询车辆维修列表
     * 
     * @param tRepair 车辆维修
     * @return 车辆维修
     */
    @Override
    public List<TRepair> selectTRepairList(TRepair tRepair)
    {
        return tRepairMapper.selectTRepairList(tRepair);
    }

    /**
     * 新增车辆维修
     * 
     * @param tRepair 车辆维修
     * @return 结果
     */
    @Override
    public int insertTRepair(TRepair tRepair)
    {
        return tRepairMapper.insertTRepair(tRepair);
    }

    /**
     * 修改车辆维修
     * 
     * @param tRepair 车辆维修
     * @return 结果
     */
    @Override
    public int updateTRepair(TRepair tRepair)
    {
        return tRepairMapper.updateTRepair(tRepair);
    }

    /**
     * 批量删除车辆维修
     * 
     * @param repairids 需要删除的车辆维修ID
     * @return 结果
     */
    @Override
    public int deleteTRepairByIds(Long[] repairids)
    {
        return tRepairMapper.deleteTRepairByIds(repairids);
    }

    /**
     * 删除车辆维修信息
     * 
     * @param repairid 车辆维修ID
     * @return 结果
     */
    @Override
    public int deleteTRepairById(Long repairid)
    {
        return tRepairMapper.deleteTRepairById(repairid);
    }
}
