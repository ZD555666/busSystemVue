package com.ruoyi.publicBusManage.departureConfirm.service;

import java.util.List;
import com.ruoyi.publicBusManage.departureConfirm.domain.TDepartureConfirm;
import com.ruoyi.publicBusManage.departureConfirm.domain.TDriverym;
import com.ruoyi.publicBusManage.departureConfirm.domain.TScheduleym;

/**
 * 公交车信息Service接口
 *
 * @author YM
 * @date 2021-08-10
 */
public interface ITDepartureConfirmService
{
    /**
     * 查询公交车信息
     *
     * @param busid 公交车信息ID
     * @return 公交车信息
     */
    public TDepartureConfirm selectTDepartureConfirmById(Long busid);

    /**
     * 查询公交车信息列表
     *
     * @param tDepartureConfirm 公交车信息
     * @return 公交车信息集合
     */
    public List<TDepartureConfirm> selectTDepartureConfirmList(TDepartureConfirm tDepartureConfirm);

    /**
     * 新增公交车信息
     *
     * @param tDepartureConfirm 公交车信息
     * @return 结果
     */
    public int insertTDepartureConfirm(TDepartureConfirm tDepartureConfirm);

    /**
     * 修改公交车信息
     *
     * @param tDepartureConfirm 公交车信息
     * @return 结果
     */
    public int updateTDepartureConfirm(TDepartureConfirm tDepartureConfirm);

    /**
     * 批量删除公交车信息
     *
     * @param busids 需要删除的公交车信息ID
     * @return 结果
     */
    public int deleteTDepartureConfirmByIds(Long[] busids);

    /**
     * 删除公交车信息信息
     *
     * @param busid 公交车信息ID
     * @return 结果
     */
    public int deleteTDepartureConfirmById(Long busid);

//    新增方法
//    确认发车，新增信息
    public int addGoBusConfirm(TDepartureConfirm tDepartureConfirm);

//    确认发车，更改发车时刻表，时间状态
    public int updateBusScheduleState(TScheduleym tScheduleym);

//    查询司机信息
    public List<TDriverym> selectDriverInfoList(TDriverym tDriverym);

}
