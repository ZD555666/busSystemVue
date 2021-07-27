package com.ruoyi.busRepair.service;

import java.util.List;
import com.ruoyi.busRepair.domain.TRepair;

/**
 * 车辆维修Service接口
 * 
 * @author lcy
 * @date 2021-07-23
 */
public interface ITRepairService 
{
    /**
     * 查询车辆维修
     * 
     * @param repairid 车辆维修ID
     * @return 车辆维修
     */
    public TRepair selectTRepairById(Long repairid);

    /**
     * 查询车辆维修列表
     * 
     * @param tRepair 车辆维修
     * @return 车辆维修集合
     */
    public List<TRepair> selectTRepairList(TRepair tRepair);

    /**
     * 新增车辆维修
     * 
     * @param tRepair 车辆维修
     * @return 结果
     */
    public int insertTRepair(TRepair tRepair);

    /**
     * 修改车辆维修
     * 
     * @param tRepair 车辆维修
     * @return 结果
     */
    public int updateTRepair(TRepair tRepair);

    /**
     * 批量删除车辆维修
     * 
     * @param repairids 需要删除的车辆维修ID
     * @return 结果
     */
    public int deleteTRepairByIds(Long[] repairids);

    /**
     * 删除车辆维修信息
     * 
     * @param repairid 车辆维修ID
     * @return 结果
     */
    public int deleteTRepairById(Long repairid);
}
