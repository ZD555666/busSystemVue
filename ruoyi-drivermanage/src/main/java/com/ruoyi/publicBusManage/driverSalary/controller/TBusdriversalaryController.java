package com.ruoyi.publicBusManage.driverSalary.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.publicBusManage.driverSalary.domain.TBusdriversalary;
import com.ruoyi.publicBusManage.driverSalary.service.ITBusdriversalaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * driverSalaryController
 *
 * @author ruoyi
 * @date 2021-08-09
 */
@RestController
@RequestMapping("/driverSalary/driverSalary")
public class TBusdriversalaryController extends BaseController
{
    @Autowired
    private ITBusdriversalaryService tBusdriversalaryService;

    /**
     * 查询driverSalary列表
     */
    @PreAuthorize("@ss.hasPermi('driverSalary:driverSalary:list')")
    @GetMapping("/list")
    public TableDataInfo list(TBusdriversalary tBusdriversalary)
    {
        startPage();
        List<TBusdriversalary> list = tBusdriversalaryService.selectTBusdriversalaryList(tBusdriversalary);
        return getDataTable(list);
    }

    /**
     * 导出driverSalary列表
     */
    @PreAuthorize("@ss.hasPermi('driverSalary:driverSalary:export')")
    @Log(title = "driverSalary", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TBusdriversalary tBusdriversalary)
    {
        List<TBusdriversalary> list = tBusdriversalaryService.selectTBusdriversalaryList(tBusdriversalary);
        ExcelUtil<TBusdriversalary> util = new ExcelUtil<TBusdriversalary>(TBusdriversalary.class);
        return util.exportExcel(list, "driverSalary数据");
    }

    /**
     * 获取driverSalary详细信息
     */
    @PreAuthorize("@ss.hasPermi('driverSalary:driverSalary:query')")
    @GetMapping(value = "/{salaryid}")
    public AjaxResult getInfo(@PathVariable("salaryid") Long salaryid)
    {
        return AjaxResult.success(tBusdriversalaryService.selectTBusdriversalaryById(salaryid));
    }

    /**
     * 新增driverSalary
     */
    @PreAuthorize("@ss.hasPermi('driverSalary:driverSalary:add')")
    @Log(title = "driverSalary", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TBusdriversalary tBusdriversalary)
    {
        return toAjax(tBusdriversalaryService.insertTBusdriversalary(tBusdriversalary));
    }

    /**
     * 修改driverSalary
     */
    @PreAuthorize("@ss.hasPermi('driverSalary:driverSalary:edit')")
    @Log(title = "driverSalary", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TBusdriversalary tBusdriversalary)
    {
        return toAjax(tBusdriversalaryService.updateTBusdriversalary(tBusdriversalary));
    }

    /**
     * 删除driverSalary
     */
    @PreAuthorize("@ss.hasPermi('driverSalary:driverSalary:remove')")
    @Log(title = "driverSalary", businessType = BusinessType.DELETE)
	@DeleteMapping("/{salaryids}")
    public AjaxResult remove(@PathVariable Long[] salaryids)
    {
        return toAjax(tBusdriversalaryService.deleteTBusdriversalaryByIds(salaryids));
    }
}
