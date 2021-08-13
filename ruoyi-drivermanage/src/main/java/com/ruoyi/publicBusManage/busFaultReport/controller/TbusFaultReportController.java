package com.ruoyi.publicBusManage.busFaultReport.controller;

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
import com.ruoyi.publicBusManage.busFaultReport.domain.TbusFaultReport;
import com.ruoyi.publicBusManage.busFaultReport.service.ITbusFaultReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆维修上报Controller
 *
 * @author ym
 * @date 2021-08-09
 */
@RestController
@RequestMapping("/busFaultReport/busFaultReport")
public class TbusFaultReportController extends BaseController
{
    @Autowired
    private ITbusFaultReportService tbusFaultReportService;

    /**
     * 查询车辆维修上报列表
     */
    @PreAuthorize("@ss.hasPermi('busFaultReport:busFaultReport:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbusFaultReport tbusFaultReport)
    {
        startPage();
        List<TbusFaultReport> list = tbusFaultReportService.selectTbusFaultReportList(tbusFaultReport);
        return getDataTable(list);
    }

    /**
     * 导出车辆维修上报列表
     */
    @PreAuthorize("@ss.hasPermi('busFaultReport:busFaultReport:export')")
    @Log(title = "车辆维修上报", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbusFaultReport tbusFaultReport)
    {
        List<TbusFaultReport> list = tbusFaultReportService.selectTbusFaultReportList(tbusFaultReport);
        ExcelUtil<TbusFaultReport> util = new ExcelUtil<TbusFaultReport>(TbusFaultReport.class);
        return util.exportExcel(list, "车辆维修上报数据");
    }

    /**
     * 获取车辆维修上报详细信息
     */
    @PreAuthorize("@ss.hasPermi('busFaultReport:busFaultReport:query')")
    @GetMapping(value = "/{repairid}")
    public AjaxResult getInfo(@PathVariable("repairid") Long repairid)
    {
        return AjaxResult.success(tbusFaultReportService.selectTbusFaultReportById(repairid));
    }

    /**
     * 新增车辆维修上报
     */
    @PreAuthorize("@ss.hasPermi('busFaultReport:busFaultReport:add')")
    @Log(title = "车辆维修上报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbusFaultReport tbusFaultReport)
    {
        return toAjax(tbusFaultReportService.insertTbusFaultReport(tbusFaultReport));
    }

    /**
     * 修改车辆维修上报
     */
    @PreAuthorize("@ss.hasPermi('busFaultReport:busFaultReport:edit')")
    @Log(title = "车辆维修上报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbusFaultReport tbusFaultReport)
    {
        return toAjax(tbusFaultReportService.updateTbusFaultReport(tbusFaultReport));
    }

    /**
     * 删除车辆维修上报
     */
    @PreAuthorize("@ss.hasPermi('busFaultReport:busFaultReport:remove')")
    @Log(title = "车辆维修上报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{repairids}")
    public AjaxResult remove(@PathVariable Long[] repairids)
    {
        return toAjax(tbusFaultReportService.deleteTbusFaultReportByIds(repairids));
    }
}
