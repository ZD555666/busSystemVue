package com.ruoyi.publicBusManage.busFaultReport.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.publicBusManage.busFaultReport.mapper.TbusFaultReportMapper;
import com.ruoyi.publicBusManage.busFaultReport.domain.TbusFaultReport;
import com.ruoyi.publicBusManage.busFaultReport.service.ITbusFaultReportService;

/**
 * 车辆维修上报Service业务层处理
 *
 * @author ym
 * @date 2021-08-09
 */
@Service
public class TbusFaultReportServiceImpl implements ITbusFaultReportService
{
    @Autowired
    private TbusFaultReportMapper tbusFaultReportMapper;

    /**
     * 查询车辆维修上报
     *
     * @param repairid 车辆维修上报ID
     * @return 车辆维修上报
     */
    @Override
    public TbusFaultReport selectTbusFaultReportById(Long repairid)
    {
        return tbusFaultReportMapper.selectTbusFaultReportById(repairid);
    }

    /**
     * 查询车辆维修上报列表
     *
     * @param tbusFaultReport 车辆维修上报
     * @return 车辆维修上报
     */
    @Override
    public List<TbusFaultReport> selectTbusFaultReportList(TbusFaultReport tbusFaultReport)
    {
        return tbusFaultReportMapper.selectTbusFaultReportList(tbusFaultReport);
    }

    /**
     * 新增车辆维修上报
     *
     * @param tbusFaultReport 车辆维修上报
     * @return 结果
     */
    @Override
    public int insertTbusFaultReport(TbusFaultReport tbusFaultReport)
    {
        return tbusFaultReportMapper.insertTbusFaultReport(tbusFaultReport);
    }

    /**
     * 修改车辆维修上报
     *
     * @param tbusFaultReport 车辆维修上报
     * @return 结果
     */
    @Override
    public int updateTbusFaultReport(TbusFaultReport tbusFaultReport)
    {
        return tbusFaultReportMapper.updateTbusFaultReport(tbusFaultReport);
    }

    /**
     * 批量删除车辆维修上报
     *
     * @param repairids 需要删除的车辆维修上报ID
     * @return 结果
     */
    @Override
    public int deleteTbusFaultReportByIds(Long[] repairids)
    {
        return tbusFaultReportMapper.deleteTbusFaultReportByIds(repairids);
    }

    /**
     * 删除车辆维修上报信息
     *
     * @param repairid 车辆维修上报ID
     * @return 结果
     */
    @Override
    public int deleteTbusFaultReportById(Long repairid)
    {
        return tbusFaultReportMapper.deleteTbusFaultReportById(repairid);
    }
}
