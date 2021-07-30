package com.ruoyi.busRealrun.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busRealrun.mapper.TBusRealrunMapper;
import com.ruoyi.busRealrun.domain.TBusRealrun;
import com.ruoyi.busRealrun.service.ITBusRealrunService;

/**
 * busRealrunService业务层处理
 * 
 * @author
 * @date 2021-07-28
 */
@Service
public class TBusRealrunServiceImpl implements ITBusRealrunService 
{
    @Autowired
    private TBusRealrunMapper tBusRealrunMapper;

    /**
     * 查询busRealrun
     * 
     * @param realrunid busRealrunID
     * @return busRealrun
     */
    @Override
    public TBusRealrun selectTBusRealrunById(Long realrunid)
    {
        return tBusRealrunMapper.selectTBusRealrunById(realrunid);
    }

    /**
     * 查询busRealrun列表
     * 
     * @param tBusRealrun busRealrun
     * @return busRealrun
     */
    @Override
    public List<TBusRealrun> selectTBusRealrunList(TBusRealrun tBusRealrun)
    {
        return tBusRealrunMapper.selectTBusRealrunList(tBusRealrun);
    }

    /**
     * 新增busRealrun
     * 
     * @param tBusRealrun busRealrun
     * @return 结果
     */
    @Override
    public int insertTBusRealrun(TBusRealrun tBusRealrun)
    {
        return tBusRealrunMapper.insertTBusRealrun(tBusRealrun);
    }

    /**
     * 修改busRealrun
     * 
     * @param tBusRealrun busRealrun
     * @return 结果
     */
    @Override
    public int updateTBusRealrun(TBusRealrun tBusRealrun)
    {
        return tBusRealrunMapper.updateTBusRealrun(tBusRealrun);
    }

    /**
     * 批量删除busRealrun
     * 
     * @param realrunids 需要删除的busRealrunID
     * @return 结果
     */
    @Override
    public int deleteTBusRealrunByIds(Long[] realrunids)
    {
        return tBusRealrunMapper.deleteTBusRealrunByIds(realrunids);
    }

    /**
     * 删除busRealrun信息
     * 
     * @param realrunid busRealrunID
     * @return 结果
     */
    @Override
    public int deleteTBusRealrunById(Long realrunid)
    {
        return tBusRealrunMapper.deleteTBusRealrunById(realrunid);
    }
}
