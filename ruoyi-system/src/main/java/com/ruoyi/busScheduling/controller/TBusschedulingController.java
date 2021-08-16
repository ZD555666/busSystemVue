package com.ruoyi.busScheduling.controller;

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
import com.ruoyi.busScheduling.domain.TBusscheduling;
import com.ruoyi.busScheduling.service.ITBusschedulingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * busschedulingController
 * 
 * @author 林
 * @date 2021-08-15
 */
@RestController
@RequestMapping("/busScheduling/busscheduling")
public class TBusschedulingController extends BaseController
{
    @Autowired
    private ITBusschedulingService tBusschedulingService;

    /**
     * 查询busscheduling列表
     */
    @PreAuthorize("@ss.hasPermi('busScheduling:busscheduling:list')")
    @GetMapping("/list")
    public TableDataInfo list(TBusscheduling tBusscheduling)
    {
        startPage();
        List<TBusscheduling> list = tBusschedulingService.selectTBusschedulingList(tBusscheduling);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('busScheduling:busscheduling:list1')")
    @GetMapping("/list1")
    public TableDataInfo list1(TBusscheduling tBusscheduling)
    {
        startPage();
        List<TBusscheduling> list1 = tBusschedulingService.selectTBusschedulingList1(tBusscheduling);
        return getDataTable(list1);
    }
    @PreAuthorize("@ss.hasPermi('busScheduling:busscheduling:list2')")
    @GetMapping("/list2")
    public TableDataInfo list2(TBusscheduling tBusscheduling)
    {
        startPage();
        List<TBusscheduling> list2 = tBusschedulingService.selectTBusschedulingList2(tBusscheduling);
        return getDataTable(list2);
    }

    /**
     * 导出busscheduling列表
     */
    @PreAuthorize("@ss.hasPermi('busScheduling:busscheduling:export')")
    @Log(title = "busscheduling", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TBusscheduling tBusscheduling)
    {
        List<TBusscheduling> list = tBusschedulingService.selectTBusschedulingList(tBusscheduling);
        ExcelUtil<TBusscheduling> util = new ExcelUtil<TBusscheduling>(TBusscheduling.class);
        return util.exportExcel(list, "busscheduling数据");
    }

    /**
     * 获取busscheduling详细信息
     */
    @PreAuthorize("@ss.hasPermi('busScheduling:busscheduling:query')")
    @GetMapping(value = "/{busid}")
    public AjaxResult getInfo(@PathVariable("busid") Long busid)
    {
        return AjaxResult.success(tBusschedulingService.selectTBusschedulingById(busid));
    }

    /**
     * 新增busscheduling
     */
    @PreAuthorize("@ss.hasPermi('busScheduling:busscheduling:add')")
    @Log(title = "busscheduling", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TBusscheduling tBusscheduling)
    {
        return toAjax(tBusschedulingService.insertTBusscheduling(tBusscheduling));
    }

    /**
     * 修改busscheduling
     */
    @PreAuthorize("@ss.hasPermi('busScheduling:busscheduling:edit')")
    @Log(title = "busscheduling", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TBusscheduling tBusscheduling)
    {
        return toAjax(tBusschedulingService.updateTBusscheduling(tBusscheduling));
    }

    /**
     * 删除busscheduling
     */
    @PreAuthorize("@ss.hasPermi('busScheduling:busscheduling:remove')")
    @Log(title = "busscheduling", businessType = BusinessType.DELETE)
	@DeleteMapping("/{busids}")
    public AjaxResult remove(@PathVariable Long[] busids)
    {
        return toAjax(tBusschedulingService.deleteTBusschedulingByIds(busids));
    }
}
