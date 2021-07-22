package com.ruoyi.road.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.road.mapper.TBusinfoMapper;
import com.ruoyi.road.domain.TBusinfo;
import com.ruoyi.road.service.ITBusinfoService;

/**
 * 详细信息Service业务层处理
 * 
 * @author gjw
 * @date 2021-07-22
 */
@Service
public class TBusinfoServiceImpl implements ITBusinfoService 
{
    @Autowired
    private TBusinfoMapper tBusinfoMapper;

    /**
     * 查询详细信息
     * 
     * @param busid 详细信息ID
     * @return 详细信息
     */
    @Override
    public TBusinfo selectTBusinfoById(Long busid)
    {
        return tBusinfoMapper.selectTBusinfoById(busid);
    }

    /**
     * 查询详细信息列表
     * 
     * @param tBusinfo 详细信息
     * @return 详细信息
     */
    @Override
    public List<TBusinfo> selectTBusinfoList(TBusinfo tBusinfo)
    {
        return tBusinfoMapper.selectTBusinfoList(tBusinfo);
    }

    /**
     * 新增详细信息
     * 
     * @param tBusinfo 详细信息
     * @return 结果
     */
    @Override
    public int insertTBusinfo(TBusinfo tBusinfo)
    {
        return tBusinfoMapper.insertTBusinfo(tBusinfo);
    }

    /**
     * 修改详细信息
     * 
     * @param tBusinfo 详细信息
     * @return 结果
     */
    @Override
    public int updateTBusinfo(TBusinfo tBusinfo)
    {
        return tBusinfoMapper.updateTBusinfo(tBusinfo);
    }

    /**
     * 批量删除详细信息
     * 
     * @param busids 需要删除的详细信息ID
     * @return 结果
     */
    @Override
    public int deleteTBusinfoByIds(Long[] busids)
    {
        return tBusinfoMapper.deleteTBusinfoByIds(busids);
    }

    /**
     * 删除详细信息信息
     * 
     * @param busid 详细信息ID
     * @return 结果
     */
    @Override
    public int deleteTBusinfoById(Long busid)
    {
        return tBusinfoMapper.deleteTBusinfoById(busid);
    }
}
