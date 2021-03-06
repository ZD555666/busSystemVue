package com.ruoyi.road.service;

import java.util.List;
import com.ruoyi.road.domain.TCity;

/**
 * 城市配置Service接口
 * 
 * @author ruoyi
 * @date 2021-07-24
 */
public interface ITCityService 
{
    /**
     * 查询城市配置
     * 
     * @param cityid 城市配置ID
     * @return 城市配置
     */
    public TCity selectTCityById(Integer cityid);

    /**
     * 查询城市配置列表
     * 
     * @param tCity 城市配置
     * @return 城市配置集合
     */
    public List<TCity> selectTCityList(TCity tCity);

    /**
     * 新增城市配置
     * 
     * @param tCity 城市配置
     * @return 结果
     */
    public int insertTCity(TCity tCity);

    /**
     * 修改城市配置
     * 
     * @param tCity 城市配置
     * @return 结果
     */
    public int updateTCity(TCity tCity);

    /**
     * 批量删除城市配置
     * 
     * @param cityids 需要删除的城市配置ID
     * @return 结果
     */
    public int deleteTCityByIds(Integer[] cityids);

    /**
     * 删除城市配置信息
     * 
     * @param cityid 城市配置ID
     * @return 结果
     */
    public int deleteTCityById(Integer cityid);
}
