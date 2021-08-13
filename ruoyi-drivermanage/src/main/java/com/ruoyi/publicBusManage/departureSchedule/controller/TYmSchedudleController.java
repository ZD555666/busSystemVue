package com.ruoyi.publicBusManage.departureSchedule.controller;

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
import com.ruoyi.publicBusManage.departureSchedule.domain.TSchedudle;
import com.ruoyi.publicBusManage.departureSchedule.service.ITSchedudleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 发车时刻表Controller
 *
 * @author ym
 * @date 2021-07-28
 */
@RestController
@RequestMapping("/drivermanager/departureSchedule")
public class TYmSchedudleController extends BaseController
{
    @Autowired
    private ITSchedudleService tSchedudleService;

    /**
     * 查询发车时刻表列表
     */
    @PreAuthorize("@ss.hasPermi('drivermanager:departureSchedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSchedudle tSchedudle)
    {
        startPage();
        List<TSchedudle> list = tSchedudleService.selectTSchedudleList(tSchedudle);
        System.out.println("打印集合"+list);
        return getDataTable(list);
    }

    /**
     * 导出发车时刻表列表
     */
    @PreAuthorize("@ss.hasPermi('drivermanager:departureSchedule:export')")
    @Log(title = "发车时刻表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSchedudle tSchedudle)
    {
        List<TSchedudle> list = tSchedudleService.selectTSchedudleList(tSchedudle);
        ExcelUtil<TSchedudle> util = new ExcelUtil<TSchedudle>(TSchedudle.class);
        return util.exportExcel(list, "发车时刻表数据");
    }

    /**
     * 获取发车时刻表详细信息
     */
    @PreAuthorize("@ss.hasPermi('drivermanager:departureSchedule:query')")
    @GetMapping(value = "/{scheduleid}")
    public AjaxResult getInfo(@PathVariable("scheduleid") Long scheduleid)
    {
        return AjaxResult.success(tSchedudleService.selectTSchedudleById(scheduleid));
    }

    /**
     * 新增发车时刻表
     */
    @PreAuthorize("@ss.hasPermi('drivermanager:departureSchedule:add')")
    @Log(title = "发车时刻表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSchedudle tSchedudle)
    {
        return toAjax(tSchedudleService.insertTSchedudle(tSchedudle));
    }

    /**
     * 修改发车时刻表
     */
    @PreAuthorize("@ss.hasPermi('drivermanager:departureSchedule:edit')")
    @Log(title = "发车时刻表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSchedudle tSchedudle)
    {
        return toAjax(tSchedudleService.updateTSchedudle(tSchedudle));
    }

    /**
     * 删除发车时刻表
     */
    @PreAuthorize("@ss.hasPermi('drivermanager:departureSchedule:remove')")
    @Log(title = "发车时刻表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleids}")
    public AjaxResult remove(@PathVariable Long[] scheduleids)
    {
        return toAjax(tSchedudleService.deleteTSchedudleByIds(scheduleids));
    }
}
