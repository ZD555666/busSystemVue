package com.ruoyi.publicBusManage.driverSalary.mapper;

import java.util.List;
import com.ruoyi.publicBusManage.driverSalary.domain.TBusdriversalary;
import org.apache.ibatis.annotations.Mapper;

/**
 * driverSalaryMapper接口
 *
 * @author ruoyi
 * @date 2021-08-09
 */
@Mapper
public interface TBusdriversalaryMapper
{
    /**
     * 查询driverSalary
     *
     * @param salaryid driverSalaryID
     * @return driverSalary
     */
    public TBusdriversalary selectTBusdriversalaryById(Long salaryid);

    /**
     * 查询driverSalary列表
     *
     * @param tBusdriversalary driverSalary
     * @return driverSalary集合
     */
    public List<TBusdriversalary> selectTBusdriversalaryList(TBusdriversalary tBusdriversalary);

    /**
     * 新增driverSalary
     *
     * @param tBusdriversalary driverSalary
     * @return 结果
     */
    public int insertTBusdriversalary(TBusdriversalary tBusdriversalary);

    /**
     * 修改driverSalary
     *
     * @param tBusdriversalary driverSalary
     * @return 结果
     */
    public int updateTBusdriversalary(TBusdriversalary tBusdriversalary);

    /**
     * 删除driverSalary
     *
     * @param salaryid driverSalaryID
     * @return 结果
     */
    public int deleteTBusdriversalaryById(Long salaryid);

    /**
     * 批量删除driverSalary
     *
     * @param salaryids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTBusdriversalaryByIds(Long[] salaryids);
}
