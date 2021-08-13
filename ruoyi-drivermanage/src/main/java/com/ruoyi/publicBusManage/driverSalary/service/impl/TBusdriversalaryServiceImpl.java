package com.ruoyi.publicBusManage.driverSalary.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.publicBusManage.driverSalary.mapper.TBusdriversalaryMapper;
import com.ruoyi.publicBusManage.driverSalary.domain.TBusdriversalary;
import com.ruoyi.publicBusManage.driverSalary.service.ITBusdriversalaryService;

/**
 * driverSalaryService业务层处理
 *
 * @author ruoyi
 * @date 2021-08-09
 */
@Service
public class TBusdriversalaryServiceImpl implements ITBusdriversalaryService
{
    @Autowired
    private TBusdriversalaryMapper tBusdriversalaryMapper;

    /**
     * 查询driverSalary
     *
     * @param salaryid driverSalaryID
     * @return driverSalary
     */
    @Override
    public TBusdriversalary selectTBusdriversalaryById(Long salaryid)
    {
        return tBusdriversalaryMapper.selectTBusdriversalaryById(salaryid);
    }

    /**
     * 查询driverSalary列表
     *
     * @param tBusdriversalary driverSalary
     * @return driverSalary
     */
    @Override
    public List<TBusdriversalary> selectTBusdriversalaryList(TBusdriversalary tBusdriversalary)
    {
        return tBusdriversalaryMapper.selectTBusdriversalaryList(tBusdriversalary);
    }

    /**
     * 新增driverSalary
     *
     * @param tBusdriversalary driverSalary
     * @return 结果
     */
    @Override
    public int insertTBusdriversalary(TBusdriversalary tBusdriversalary)
    {
        return tBusdriversalaryMapper.insertTBusdriversalary(tBusdriversalary);
    }

    /**
     * 修改driverSalary
     *
     * @param tBusdriversalary driverSalary
     * @return 结果
     */
    @Override
    public int updateTBusdriversalary(TBusdriversalary tBusdriversalary)
    {
        return tBusdriversalaryMapper.updateTBusdriversalary(tBusdriversalary);
    }

    /**
     * 批量删除driverSalary
     *
     * @param salaryids 需要删除的driverSalaryID
     * @return 结果
     */
    @Override
    public int deleteTBusdriversalaryByIds(Long[] salaryids)
    {
        return tBusdriversalaryMapper.deleteTBusdriversalaryByIds(salaryids);
    }

    /**
     * 删除driverSalary信息
     *
     * @param salaryid driverSalaryID
     * @return 结果
     */
    @Override
    public int deleteTBusdriversalaryById(Long salaryid)
    {
        return tBusdriversalaryMapper.deleteTBusdriversalaryById(salaryid);
    }
}
