package com.ruoyi.busInfo.service;

import java.util.List;
import com.ruoyi.busInfo.domain.TBusinfo;

/**
 * 车辆信息Service接口
 * 
 * @author
 * @date 2021-07-23
 */
public interface ITBusinfoService 
{
    /**
     * 查询车辆信息
     * 
     * @param busid 车辆信息ID
     * @return 车辆信息
     */
    public TBusinfo selectTBusinfoById(Long busid);

    /**
     * 查询车辆信息列表
     * 
     * @param tBusinfo 车辆信息
     * @return 车辆信息集合
     */
    public List<TBusinfo> selectTBusinfoList(TBusinfo tBusinfo);

    /**
     * 新增车辆信息
     * 
     * @param tBusinfo 车辆信息
     * @return 结果
     */
    public int insertTBusinfo(TBusinfo tBusinfo);

    /**
     * 修改车辆信息
     * 
     * @param tBusinfo 车辆信息
     * @return 结果
     */
    public int updateTBusinfo(TBusinfo tBusinfo);

    /**
     * 批量删除车辆信息
     * 
     * @param busids 需要删除的车辆信息ID
     * @return 结果
     */
    public int deleteTBusinfoByIds(Long[] busids);

    /**
     * 删除车辆信息信息
     * 
     * @param busid 车辆信息ID
     * @return 结果
     */
    public int deleteTBusinfoById(Long busid);
}
