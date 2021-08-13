package com.ruoyi.publicBusManage.driverSchedule.mapper;

import java.util.List;

import com.ruoyi.publicBusManage.driverSchedule.domain.*;
import org.springframework.stereotype.Repository;

/**
 * 司机排班Mapper接口
 *
 * @author YM
 * @date 2021-07-28
 */
@Repository
public interface TDriverMapper
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
     * 删除司机排班
     *
     * @param driverworkid 司机排班ID
     * @return 结果
     */
    public int deleteTDriverById(Long driverworkid);

    /**
     * 批量删除司机排班
     *
     * @param driverworkids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTDriverByIds(Long[] driverworkids);

//    新增的方法
//    查询发车时刻表的所有信息
    /**
     * 查询发车时刻表的所有信息
     *
     * @param tschedudleInfo
     * @return 结果
     */
    public List<TschedudleInfo> busScheduleInfoList(TschedudleInfo tschedudleInfo);

//    查询站点信息
    /**
     * 查询站点信息
     *
     * @param
     * @return 结果
     */
    public List<TDriver> selectStationInfolist();

//   查询公交车信息
    public List<BusInformation> selectBusInformationList();

//    查询公交司机信息
    public List<sysUserInfor>selectBusDriverInformationList();

//    新增排班信息
    public int addDialogInputInfo(TDriver tDriver);

}
