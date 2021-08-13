package com.ruoyi.publicBusManage.driverSchedule.service;

import java.util.List;

import com.ruoyi.publicBusManage.driverSchedule.domain.*;

/**
 * 司机排班Service接口
 *
 * @author YM
 * @date 2021-07-28
 */
public interface ITDriverService
{
    /**
     * 查询司机排班
     *
     * @param driverworkid 司机排班ID
     * @return 司机排班
     */
    public TDriver selectTDriverById(Long driverworkid);

    /**
     * 查询司机排班列表
     *
     * @param tDriver 司机排班
     * @return 司机排班集合
     */
    public List<TDriver> selectTDriverList(TDriver tDriver);

    /**
     * 新增司机排班
     *
     * @param tDriver 司机排班
     * @return 结果
     */
    public int insertTDriver(TDriver tDriver);

    /**
     * 修改司机排班
     *
     * @param tDriver 司机排班
     * @return 结果
     */
    public int updateTDriver(TDriver tDriver);

    /**
     * 批量删除司机排班
     *
     * @param driverworkids 需要删除的司机排班ID
     * @return 结果
     */
    public int deleteTDriverByIds(Long[] driverworkids);

    /**
     * 删除司机排班信息
     *
     * @param driverworkid 司机排班ID
     * @return 结果
     */
    public int deleteTDriverById(Long driverworkid);

//    新增方法
    /**
     * 查询发车时刻信息
     *
     * @param tschedudleInfo 司机排班ID
     * @return 结果
     */
    public List<TschedudleInfo> busScheduleInfoList(TschedudleInfo tschedudleInfo);

    /**
     * 查询站点信息
     *
     * @param
     * @return 结果
     */
    public List<TDriver> selectStationInfolist();

//    查询公交车信息
    public List<BusInformation> selectBusInformationList();

//    查询公交司机信息
    public List<sysUserInfor> selectBusDriverInformationList();

//    新增排班信息
    public int addDialogInputInfo(TDriver tDriver);
}
