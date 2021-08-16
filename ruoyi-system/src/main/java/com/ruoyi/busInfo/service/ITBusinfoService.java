package com.ruoyi.busInfo.service;

import java.util.List;
import com.ruoyi.busInfo.domain.TBusinfo;
//import com.ruoyi.busInfo.domain.TBusscheduling;


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
    public List<TBusinfo> selectTBusinfoList1(TBusinfo tBusinfo);
    public List<TBusinfo> selectTBusinfoList2(TBusinfo tBusinfo);
    public List<TBusinfo> selectTBusinfoList3(TBusinfo tBusinfo);
    public List<TBusinfo> selectTBusinfoList4(TBusinfo tBusinfo);
    public List<TBusinfo> selectTBusinfoList5(TBusinfo tBusinfo);
//    public List<TBusscheduling> selectTBusschedulingList(TBusscheduling tBusscheduling);
//    public int updateTBusscheduling(TBusscheduling tBusscheduling);
//    public TBusscheduling selectTBusschedulingById(Long busid);
//    public int insertTBusscheduling(TBusscheduling tBusscheduling);
    /**
     * 新增车辆信息
     *
     * @param tBusinfo 车辆信息
     * @return 结果
     */
    public int insertTBusinfo(TBusinfo tBusinfo);
    public int insertTBusinfo1(TBusinfo tBusinfo);
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
