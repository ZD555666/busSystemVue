package com.ruoyi.busInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busInfo.mapper.TBusinfoMapper;
import com.ruoyi.busInfo.domain.TBusinfo;
import com.ruoyi.busInfo.service.ITBusinfoService;

/**
 * 车辆信息Service业务层处理
 * 
 * @author lvy
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
