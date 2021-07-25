package com.ruoyi.road.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.road.mapper.TCityMapper;
import com.ruoyi.road.domain.TCity;
import com.ruoyi.road.service.ITCityService;

/**
 * 城市配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-24
 */
@Service
public class TCityServiceImpl implements ITCityService 
{
    @Autowired
    private TCityMapper tCityMapper;

    /**
     * 查询城市配置
     * 
     * @param cityid 城市配置ID
     * @return 城市配置
     */
    @Override
    public TCity selectTCityById(Integer cityid)
    {
        return tCityMapper.selectTCityById(cityid);
    }

    /**
     * 查询城市配置列表
     * 
     * @param tCity 城市配置
     * @return 城市配置
     */
    @Override
    public List<TCity> selectTCityList(TCity tCity)
    {
        return tCityMapper.selectTCityList(tCity);
    }

    /**
     * 新增城市配置
     * 
     * @param tCity 城市配置
     * @return 结果
     */
    @Override
    public int insertTCity(TCity tCity)
    {
        return tCityMapper.insertTCity(tCity);
    }

    /**
     * 修改城市配置
     * 
     * @param tCity 城市配置
     * @return 结果
     */
    @Override
    public int updateTCity(TCity tCity)
    {
        return tCityMapper.updateTCity(tCity);
    }

    /**
     * 批量删除城市配置
     * 
     * @param cityids 需要删除的城市配置ID
     * @return 结果
     */
    @Override
    public int deleteTCityByIds(Integer[] cityids)
    {
        return tCityMapper.deleteTCityByIds(cityids);
    }

    /**
     * 删除城市配置信息
     * 
     * @param cityid 城市配置ID
     * @return 结果
     */
    @Override
    public int deleteTCityById(Integer cityid)
    {
        return tCityMapper.deleteTCityById(cityid);
    }
}
