package com.ruoyi.publicBusManage.busFaultReport.service;

import java.util.List;
import com.ruoyi.publicBusManage.busFaultReport.domain.TbusFaultReport;

/**
 * 车辆维修上报Service接口
 *
 * @author ym
 * @date 2021-08-09
 */
public interface ITbusFaultReportService
{
    /**
     * 查询车辆维修上报
     *
     * @param repairid 车辆维修上报ID
     * @return 车辆维修上报
     */
    public TbusFaultReport selectTbusFaultReportById(Long repairid);

    /**
     * 查询车辆维修上报列表
     *
     * @param tbusFaultReport 车辆维修上报
     * @return 车辆维修上报集合
     */
    public List<TbusFaultReport> selectTbusFaultReportList(TbusFaultReport tbusFaultReport);

    /**
     * 新增车辆维修上报
     *
     * @param tbusFaultReport 车辆维修上报
     * @return 结果
     */
    public int insertTbusFaultReport(TbusFaultReport tbusFaultReport);

    /**
     * 修改车辆维修上报
     *
     * @param tbusFaultReport 车辆维修上报
     * @return 结果
     */
    public int updateTbusFaultReport(TbusFaultReport tbusFaultReport);

    /**
     * 批量删除车辆维修上报
     *
     * @param repairids 需要删除的车辆维修上报ID
     * @return 结果
     */
    public int deleteTbusFaultReportByIds(Long[] repairids);

    /**
     * 删除车辆维修上报信息
     *
     * @param repairid 车辆维修上报ID
     * @return 结果
     */
    public int deleteTbusFaultReportById(Long repairid);
}
