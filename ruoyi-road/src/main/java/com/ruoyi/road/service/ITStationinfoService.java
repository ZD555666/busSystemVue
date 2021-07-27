package com.ruoyi.road.service;

import java.util.List;

import com.ruoyi.road.domain.TCity;
import com.ruoyi.road.domain.TStationinfo;

/**
 * 站点信息Service接口
 * 
 * @author ruoyi
 * @date 2021-07-25
 */
public interface ITStationinfoService 
{
    /**
     * 查询站点信息
     * 
     * @param stationid 站点信息ID
     * @return 站点信息
     */
    public TStationinfo selectTStationinfoById(Integer stationid);

    /**
     * 查询站点信息列表
     * 
     * @param tStationinfo 站点信息
     * @return 站点信息集合
     */
    public List<TStationinfo> selectTStationinfoList(TStationinfo tStationinfo, TCity tCity);

    /**
     * 新增站点信息
     * 
     * @param tStationinfo 站点信息
     * @return 结果
     */
    public int insertTStationinfo(TStationinfo tStationinfo);

    /**
     * 修改站点信息
     * 
     * @param tStationinfo 站点信息
     * @return 结果
     */
    public int updateTStationinfo(TStationinfo tStationinfo);

    /**
     * 批量删除站点信息
     * 
     * @param stationids 需要删除的站点信息ID
     * @return 结果
     */
    public int deleteTStationinfoByIds(Integer[] stationids);

    /**
     * 删除站点信息信息
     * 
     * @param stationid 站点信息ID
     * @return 结果
     */
    public int deleteTStationinfoById(Integer stationid);
}
