package com.ruoyi.busRealrun.controller;

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
import com.ruoyi.busRealrun.domain.TBusRealrun;
import com.ruoyi.busRealrun.service.ITBusRealrunService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * busRealrunController
 * 
 * @author
 * @date 2021-07-28
 */
@RestController
@RequestMapping("/busRealrun/busrealrun")
public class TBusRealrunController extends BaseController
{
    @Autowired
    private ITBusRealrunService tBusRealrunService;

    /**
     * 查询busRealrun列表
     */
    @PreAuthorize("@ss.hasPermi('busRealrun:busrealrun:list')")
    @GetMapping("/list")
    public TableDataInfo list(TBusRealrun tBusRealrun)
    {
        startPage();
        List<TBusRealrun> list = tBusRealrunService.selectTBusRealrunList(tBusRealrun);
        return getDataTable(list);
    }

    /**
     * 导出busRealrun列表
     */
    @PreAuthorize("@ss.hasPermi('busRealrun:busrealrun:export')")
    @Log(title = "busRealrun", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TBusRealrun tBusRealrun)
    {
        List<TBusRealrun> list = tBusRealrunService.selectTBusRealrunList(tBusRealrun);
        ExcelUtil<TBusRealrun> util = new ExcelUtil<TBusRealrun>(TBusRealrun.class);
        return util.exportExcel(list, "busRealrun数据");
    }

    /**
     * 获取busRealrun详细信息
     */
    @PreAuthorize("@ss.hasPermi('busRealrun:busrealrun:query')")
    @GetMapping(value = "/{realrunid}")
    public AjaxResult getInfo(@PathVariable("realrunid") Long realrunid)
    {
        return AjaxResult.success(tBusRealrunService.selectTBusRealrunById(realrunid));
    }

    /**
     * 新增busRealrun
     */
    @PreAuthorize("@ss.hasPermi('busRealrun:busrealrun:add')")
    @Log(title = "busRealrun", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TBusRealrun tBusRealrun)
    {
        return toAjax(tBusRealrunService.insertTBusRealrun(tBusRealrun));
    }

    /**
     * 修改busRealrun
     */
    @PreAuthorize("@ss.hasPermi('busRealrun:busrealrun:edit')")
    @Log(title = "busRealrun", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TBusRealrun tBusRealrun)
    {
        return toAjax(tBusRealrunService.updateTBusRealrun(tBusRealrun));
    }

    /**
     * 删除busRealrun
     */
    @PreAuthorize("@ss.hasPermi('busRealrun:busrealrun:remove')")
    @Log(title = "busRealrun", businessType = BusinessType.DELETE)
	@DeleteMapping("/{realrunids}")
    public AjaxResult remove(@PathVariable Long[] realrunids)
    {
        return toAjax(tBusRealrunService.deleteTBusRealrunByIds(realrunids));
    }
}
