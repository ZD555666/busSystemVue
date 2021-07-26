package com.ruoyi.road.mapper;

import java.util.List;

import com.ruoyi.road.domain.TCity;
import com.ruoyi.road.domain.TStationinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 站点信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-07-25
 */
@Mapper
public interface TStationinfoMapper 
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
    public List<TStationinfo> selectTStationinfoList(@Param("tStationinfo") TStationinfo tStationinfo,@Param("tCity") TCity tCity);

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
     * 删除站点信息
     * 
     * @param stationid 站点信息ID
     * @return 结果
     */
    public int deleteTStationinfoById(Integer stationid);

    /**
     * 批量删除站点信息
     * 
     * @param stationids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTStationinfoByIds(Integer[] stationids);
}
