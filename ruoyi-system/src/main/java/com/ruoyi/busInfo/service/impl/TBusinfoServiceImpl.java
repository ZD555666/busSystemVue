package com.ruoyi.busInfo.service.impl;

import java.util.List;

//import com.ruoyi.busInfo.domain.TBusscheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busInfo.mapper.TBusinfoMapper;
import com.ruoyi.busInfo.domain.TBusinfo;
import com.ruoyi.busInfo.service.ITBusinfoService;

/**
 * 车辆信息Service业务层处理
 *
 * @author
 * @date 2021-07-23
 */
@Service
public class TBusinfoServiceImpl implements ITBusinfoService
{
    @Autowired
    private TBusinfoMapper tBusinfoMapper;

    /**
     * 查询车辆信息
     *
     * @param busid 车辆信息ID
     * @return 车辆信息
     */
    @Override
    public TBusinfo selectTBusinfoById(Long busid)
    {
        return tBusinfoMapper.selectTBusinfoById(busid);
    }
//    public TBusscheduling selectTBusschedulingById(Long busid)
//    {
//        return tBusinfoMapper.selectTBusschedulingById(busid);
//    }
    /**
     * 查询车辆信息列表
     *
     * @param tBusinfo 车辆信息
     * @return 车辆信息
     */
    @Override
    public List<TBusinfo> selectTBusinfoList(TBusinfo tBusinfo)
    {
        return tBusinfoMapper.selectTBusinfoList(tBusinfo);
    }

    @Override
    public List<TBusinfo> selectTBusinfoList1(TBusinfo tBusinfo) {
        return tBusinfoMapper.selectTBusinfoList1(tBusinfo);
    }
    @Override
    public List<TBusinfo> selectTBusinfoList2(TBusinfo tBusinfo) {
        return tBusinfoMapper.selectTBusinfoList2(tBusinfo);
    }
    public List<TBusinfo> selectTBusinfoList3(TBusinfo tBusinfo) {
        return tBusinfoMapper.selectTBusinfoList3(tBusinfo);
    }
    public List<TBusinfo> selectTBusinfoList4(TBusinfo tBusinfo) {
        return tBusinfoMapper.selectTBusinfoList4(tBusinfo);
    }
    public List<TBusinfo> selectTBusinfoList5(TBusinfo tBusinfo) {
        return tBusinfoMapper.selectTBusinfoList5(tBusinfo);
    }
//    @Override
//    public List<TBusscheduling> selectTBusschedulingList(TBusscheduling tBusscheduling)
//    {
//        return tBusinfoMapper.selectTBusschedulingList(tBusscheduling);
//    }
//
//    public int updateTBusscheduling(TBusscheduling tBusscheduling)
//    {
//        return tBusinfoMapper.updateTBusscheduling(tBusscheduling);
//    }
//
//    public int insertTBusscheduling(TBusscheduling tBusscheduling)
//    {
//        return tBusinfoMapper.insertTBusscheduling(tBusscheduling);
//    }
    /**
     * 新增车辆信息
     *
     * @param tBusinfo 车辆信息
     * @return 结果
     */
    @Override
    public int insertTBusinfo(TBusinfo tBusinfo)
    {
        return tBusinfoMapper.insertTBusinfo(tBusinfo);
    }
    public int insertTBusinfo1(TBusinfo tBusinfo)
    {
        return tBusinfoMapper.insertTBusinfo1(tBusinfo);
    }
    /**
     * 修改车辆信息
     *
     * @param tBusinfo 车辆信息
     * @return 结果
     */
    @Override
    public int updateTBusinfo(TBusinfo tBusinfo)
    {
        return tBusinfoMapper.updateTBusinfo(tBusinfo);
    }

    /**
     * 批量删除车辆信息
     *
     * @param busids 需要删除的车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteTBusinfoByIds(Long[] busids)
    {
        return tBusinfoMapper.deleteTBusinfoByIds(busids);
    }

    /**
     * 删除车辆信息信息
     *
     * @param busid 车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteTBusinfoById(Long busid)
    {
        return tBusinfoMapper.deleteTBusinfoById(busid);
    }
}
