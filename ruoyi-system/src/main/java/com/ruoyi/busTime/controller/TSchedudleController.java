package com.ruoyi.busTime.controller;

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
import com.ruoyi.busTime.domain.TSchedudle;
import com.ruoyi.busTime.service.ITSchedudleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公交车时刻Controller
 * 
 * @author ruoyi
 * @date 2021-07-26
 */
@RestController
@RequestMapping("/busTime/bustime")
public class TSchedudleController extends BaseController
{
    @Autowired
    private ITSchedudleService tSchedudleService;

    /**
     * 查询公交车时刻列表
     */
    @PreAuthorize("@ss.hasPermi('busTime:bustime:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSchedudle tSchedudle)
    {
        startPage();
        List<TSchedudle> list = tSchedudleService.selectTSchedudleList(tSchedudle);
        return getDataTable(list);
    }

    /**
     * 导出公交车时刻列表
     */
    @PreAuthorize("@ss.hasPermi('busTime:bustime:export')")
    @Log(title = "公交车时刻", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSchedudle tSchedudle)
    {
        List<TSchedudle> list = tSchedudleService.selectTSchedudleList(tSchedudle);
        ExcelUtil<TSchedudle> util = new ExcelUtil<TSchedudle>(TSchedudle.class);
        return util.exportExcel(list, "公交车时刻数据");
    }

    /**
     * 获取公交车时刻详细信息
     */
    @PreAuthorize("@ss.hasPermi('busTime:bustime:query')")
    @GetMapping(value = "/{scheduleid}")
    public AjaxResult getInfo(@PathVariable("scheduleid") Long scheduleid)
    {
        return AjaxResult.success(tSchedudleService.selectTSchedudleById(scheduleid));
    }

    /**
     * 新增公交车时刻
     */
    @PreAuthorize("@ss.hasPermi('busTime:bustime:add')")
    @Log(title = "公交车时刻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSchedudle tSchedudle)
    {
        return toAjax(tSchedudleService.insertTSchedudle(tSchedudle));
    }

    /**
     * 修改公交车时刻
     */
    @PreAuthorize("@ss.hasPermi('busTime:bustime:edit')")
    @Log(title = "公交车时刻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSchedudle tSchedudle)
    {
        return toAjax(tSchedudleService.updateTSchedudle(tSchedudle));
    }

    /**
     * 删除公交车时刻
     */
    @PreAuthorize("@ss.hasPermi('busTime:bustime:remove')")
    @Log(title = "公交车时刻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleids}")
    public AjaxResult remove(@PathVariable Long[] scheduleids)
    {
        return toAjax(tSchedudleService.deleteTSchedudleByIds(scheduleids));
    }
}
