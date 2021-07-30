package com.ruoyi.busRealrun.mapper;

import java.util.List;
import com.ruoyi.busRealrun.domain.TBusRealrun;

/**
 * busRealrunMapper接口
 * 
 * @author
 * @date 2021-07-28
 */
public interface TBusRealrunMapper 
{
    /**
     * 查询busRealrun
     * 
     * @param realrunid busRealrunID
     * @return busRealrun
     */
    public TBusRealrun selectTBusRealrunById(Long realrunid);

    /**
     * 查询busRealrun列表
     * 
     * @param tBusRealrun busRealrun
     * @return busRealrun集合
     */
    public List<TBusRealrun> selectTBusRealrunList(TBusRealrun tBusRealrun);

    /**
     * 新增busRealrun
     * 
     * @param tBusRealrun busRealrun
     * @return 结果
     */
    public int insertTBusRealrun(TBusRealrun tBusRealrun);

    /**
     * 修改busRealrun
     * 
     * @param tBusRealrun busRealrun
     * @return 结果
     */
    public int updateTBusRealrun(TBusRealrun tBusRealrun);

    /**
     * 删除busRealrun
     * 
     * @param realrunid busRealrunID
     * @return 结果
     */
    public int deleteTBusRealrunById(Long realrunid);

    /**
     * 批量删除busRealrun
     * 
     * @param realrunids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTBusRealrunByIds(Long[] realrunids);
}
