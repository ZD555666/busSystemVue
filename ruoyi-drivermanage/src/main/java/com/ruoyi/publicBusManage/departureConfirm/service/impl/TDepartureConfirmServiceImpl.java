package com.ruoyi.publicBusManage.departureConfirm.service.impl;

import java.util.List;

import com.ruoyi.publicBusManage.departureConfirm.domain.TDriverym;
import com.ruoyi.publicBusManage.departureConfirm.domain.TScheduleym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.publicBusManage.departureConfirm.mapper.TDepartureConfirmMapper;
import com.ruoyi.publicBusManage.departureConfirm.domain.TDepartureConfirm;
import com.ruoyi.publicBusManage.departureConfirm.service.ITDepartureConfirmService;

/**
 * 公交车信息Service业务层处理
 *
 * @author YM
 * @date 2021-08-10
 */
@Service
public class TDepartureConfirmServiceImpl implements ITDepartureConfirmService
{
    @Autowired
    private TDepartureConfirmMapper tDepartureConfirmMapper;

    /**
     * 查询公交车信息
     *
     * @param busid 公交车信息ID
     * @return 公交车信息
     */
    @Override
    public TDepartureConfirm selectTDepartureConfirmById(Long busid)
    {
        return tDepartureConfirmMapper.selectTDepartureConfirmById(busid);
    }

    /**
     * 查询公交车信息列表
     *
     * @param tDepartureConfirm 公交车信息
     * @return 公交车信息
     */
    @Override
    public List<TDepartureConfirm> selectTDepartureConfirmList(TDepartureConfirm tDepartureConfirm)
    {
        return tDepartureConfirmMapper.selectTDepartureConfirmList(tDepartureConfirm);
    }

    /**
     * 新增公交车信息
     *
     * @param tDepartureConfirm 公交车信息
     * @return 结果
     */
    @Override
    public int insertTDepartureConfirm(TDepartureConfirm tDepartureConfirm)
    {
        return tDepartureConfirmMapper.insertTDepartureConfirm(tDepartureConfirm);
    }

    /**
     * 修改公交车信息
     *
     * @param tDepartureConfirm 公交车信息
     * @return 结果
     */
    @Override
    public int updateTDepartureConfirm(TDepartureConfirm tDepartureConfirm)
    {
        return tDepartureConfirmMapper.updateTDepartureConfirm(tDepartureConfirm);
    }

    /**
     * 批量删除公交车信息
     *
     * @param busids 需要删除的公交车信息ID
     * @return 结果
     */
    @Override
    public int deleteTDepartureConfirmByIds(Long[] busids)
    {
        return tDepartureConfirmMapper.deleteTDepartureConfirmByIds(busids);
    }

    /**
     * 删除公交车信息信息
     *
     * @param busid 公交车信息ID
     * @return 结果
     */
    @Override
    public int deleteTDepartureConfirmById(Long busid)
    {
        return tDepartureConfirmMapper.deleteTDepartureConfirmById(busid);
    }

//    新增方法，确认发车
    @Override
    public int addGoBusConfirm(TDepartureConfirm tDepartureConfirm) {
        return tDepartureConfirmMapper.addGoBusConfirm(tDepartureConfirm);
    }

//    确认发车，更改发车时刻表状态
    @Override
    public int updateBusScheduleState(TScheduleym tScheduleym) {

        return tDepartureConfirmMapper.updateBusScheduleState(tScheduleym);
    }

//    查询司机信息，判断是否存在
    @Override
    public List<TDriverym> selectDriverInfoList(TDriverym tDriverym) {
        return tDepartureConfirmMapper.selectDriverInfoList(tDriverym);
    }
}
