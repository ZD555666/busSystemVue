package com.ruoyi.road.controller;

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
import com.ruoyi.road.domain.TSchedule;
import com.ruoyi.road.service.ITScheduleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 线路发车时刻配置Controller
 * 
 * @author ruoyi
 * @date 2021-08-10
 */
@RestController
@RequestMapping("/road/schedule")
public class TScheduleController extends BaseController
{
    @Autowired
    private ITScheduleService tScheduleService;

    /**
     * 查询线路发车时刻配置列表
     */
    @PreAuthorize("@ss.hasPermi('road:schedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSchedule tSchedule)
    {
        startPage();
        List<TSchedule> list = tScheduleService.selectTScheduleList(tSchedule);
        return getDataTable(list);
    }

    /**
     * 导出线路发车时刻配置列表
     */
    @PreAuthorize("@ss.hasPermi('road:schedule:export')")
    @Log(title = "线路发车时刻配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSchedule tSchedule)
    {
        List<TSchedule> list = tScheduleService.selectTScheduleList(tSchedule);
        ExcelUtil<TSchedule> util = new ExcelUtil<TSchedule>(TSchedule.class);
        return util.exportExcel(list, "线路发车时刻配置数据");
    }

    /**
     * 获取线路发车时刻配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('road:schedule:query')")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Integer scheduleId)
    {
        return AjaxResult.success(tScheduleService.selectTScheduleById(scheduleId));
    }

    /**
     * 新增线路发车时刻配置
     */
    @PreAuthorize("@ss.hasPermi('road:schedule:add')")
    @Log(title = "线路发车时刻配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSchedule tSchedule)
    {
        return toAjax(tScheduleService.insertTSchedule(tSchedule));
    }

    /**
     * 修改线路发车时刻配置
     */
    @PreAuthorize("@ss.hasPermi('road:schedule:edit')")
    @Log(title = "线路发车时刻配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSchedule tSchedule)
    {
        return toAjax(tScheduleService.updateTSchedule(tSchedule));
    }

    /**
     * 删除线路发车时刻配置
     */
    @PreAuthorize("@ss.hasPermi('road:schedule:remove')")
    @Log(title = "线路发车时刻配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable Integer[] scheduleIds)
    {
        return toAjax(tScheduleService.deleteTScheduleByIds(scheduleIds));
    }
}
