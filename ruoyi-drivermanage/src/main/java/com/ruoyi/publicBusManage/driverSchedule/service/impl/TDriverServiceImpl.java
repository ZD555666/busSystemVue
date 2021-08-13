package com.ruoyi.publicBusManage.driverSchedule.service.impl;

import java.util.List;

import com.ruoyi.publicBusManage.driverSchedule.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.publicBusManage.driverSchedule.mapper.TDriverMapper;
import com.ruoyi.publicBusManage.driverSchedule.service.ITDriverService;

/**
 * 司机排班Service业务层处理
 *
 * @author YM
 * @date 2021-07-28
 */
@Service
public class TDriverServiceImpl implements ITDriverService
{
    @Autowired
    private TDriverMapper tDriverMapper;

    /**
     * 查询司机排班
     *
     * @param driverworkid 司机排班ID
     * @return 司机排班
     */
    @Override
    public TDriver selectTDriverById(Long driverworkid)
    {
        return tDriverMapper.selectTDriverById(driverworkid);
    }

    /**
     * 查询司机排班列表
     *
     * @param tDriver 司机排班
     * @return 司机排班
     */
    @Override
    public List<TDriver> selectTDriverList(TDriver tDriver)
    {
        return tDriverMapper.selectTDriverList(tDriver);
    }

    /**
     * 新增司机排班
     *
     * @param tDriver 司机排班
     * @return 结果
     */
    @Override
    public int insertTDriver(TDriver tDriver)
    {
        return tDriverMapper.insertTDriver(tDriver);
    }

    /**
     * 修改司机排班
     *
     * @param tDriver 司机排班
     * @return 结果
     */
    @Override
    public int updateTDriver(TDriver tDriver)
    {
        return tDriverMapper.updateTDriver(tDriver);
    }

    /**
     * 批量删除司机排班
     *
     * @param driverworkids 需要删除的司机排班ID
     * @return 结果
     */
    @Override
    public int deleteTDriverByIds(Long[] driverworkids)
    {
        return tDriverMapper.deleteTDriverByIds(driverworkids);
    }

    /**
     * 删除司机排班信息
     *
     * @param driverworkid 司机排班ID
     * @return 结果
     */
    @Override
    public int deleteTDriverById(Long driverworkid)
    {
        return tDriverMapper.deleteTDriverById(driverworkid);
    }

    //    新增方法
    /**
     * 查询公交车排班信息
     *
     * @param tschedudleInfo 司机排班ID
     * @return 结果
     */
    @Override
    public List<TschedudleInfo> busScheduleInfoList(TschedudleInfo tschedudleInfo) {
        List<TschedudleInfo> scheduleInfoList=tDriverMapper.busScheduleInfoList(tschedudleInfo);
        return scheduleInfoList;
    }

    /**
     * 查询站点信息
     *
     * @param
     * @return 结果
     */
    @Override
    public List<TDriver> selectStationInfolist() {

        List<TDriver> stationInformationList=tDriverMapper.selectStationInfolist();
        return stationInformationList;
    }

//    查询公交车信息
    @Override
    public List<BusInformation> selectBusInformationList() {

        List<BusInformation> busInformationList=tDriverMapper.selectBusInformationList();
        return busInformationList;
    }

    @Override
    public List<sysUserInfor> selectBusDriverInformationList() {
        List<sysUserInfor> sysUserInforList=tDriverMapper.selectBusDriverInformationList();
        return sysUserInforList;
    }

//    新增排班信息
    @Override
    public int addDialogInputInfo(TDriver tDriver) {
        return 0;
    }


}
