package com.ruoyi.road.service.impl;

import java.util.List;

import com.ruoyi.road.domain.TCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.road.mapper.TStationinfoMapper;
import com.ruoyi.road.domain.TStationinfo;
import com.ruoyi.road.service.ITStationinfoService;

/**
 * 站点信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-25
 */
@Service
public class TStationinfoServiceImpl implements ITStationinfoService 
{
    @Autowired
    private TStationinfoMapper tStationinfoMapper;

    /**
     * 查询站点信息
     * 
     * @param stationid 站点信息ID
     * @return 站点信息
     */
    @Override
    public TStationinfo selectTStationinfoById(Integer stationid)
    {
        return tStationinfoMapper.selectTStationinfoById(stationid);
    }

    /**
     * 查询站点信息列表
     * 
     * @param tStationinfo 站点信息
     * @return 站点信息
     */
    @Override
    public List<TStationinfo> selectTStationinfoList(TStationinfo tStationinfo, TCity tCity)
    {
        return tStationinfoMapper.selectTStationinfoList(tStationinfo, tCity);
    }

    /**
     * 新增站点信息
     * 
     * @param tStationinfo 站点信息
     * @return 结果
     */
    @Override
    public int insertTStationinfo(TStationinfo tStationinfo)
    {
        return tStationinfoMapper.insertTStationinfo(tStationinfo);
    }

    /**
     * 修改站点信息
     * 
     * @param tStationinfo 站点信息
     * @return 结果
     */
    @Override
    public int updateTStationinfo(TStationinfo tStationinfo)
    {
        return tStationinfoMapper.updateTStationinfo(tStationinfo);
    }

    /**
     * 批量删除站点信息
     * 
     * @param stationids 需要删除的站点信息ID
     * @return 结果
     */
    @Override
    public int deleteTStationinfoByIds(Integer[] stationids)
    {
        return tStationinfoMapper.deleteTStationinfoByIds(stationids);
    }

    /**
     * 删除站点信息信息
     * 
     * @param stationid 站点信息ID
     * @return 结果
     */
    @Override
    public int deleteTStationinfoById(Integer stationid)
    {
        return tStationinfoMapper.deleteTStationinfoById(stationid);
    }
}
