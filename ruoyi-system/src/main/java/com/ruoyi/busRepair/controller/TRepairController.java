package com.ruoyi.busRepair.controller;

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
import com.ruoyi.busRepair.domain.TRepair;
import com.ruoyi.busRepair.service.ITRepairService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆维修Controller
 * 
 * @author lcy
 * @date 2021-07-23
 */
@RestController
@RequestMapping("/busRepair/busrepair")
public class TRepairController extends BaseController
{
    @Autowired
    private ITRepairService tRepairService;

    /**
     * 查询车辆维修列表
     */
    @PreAuthorize("@ss.hasPermi('busRepair:busrepair:list')")
    @GetMapping("/list")
    public TableDataInfo list(TRepair tRepair)
    {
        startPage();
        List<TRepair> list = tRepairService.selectTRepairList(tRepair);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 导出车辆维修列表
     */
    @PreAuthorize("@ss.hasPermi('busRepair:busrepair:export')")
    @Log(title = "车辆维修", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TRepair tRepair)
    {
        List<TRepair> list = tRepairService.selectTRepairList(tRepair);
        ExcelUtil<TRepair> util = new ExcelUtil<TRepair>(TRepair.class);
        return util.exportExcel(list, "车辆维修数据");
    }

    /**
     * 获取车辆维修详细信息
     */
    @PreAuthorize("@ss.hasPermi('busRepair:busrepair:query')")
    @GetMapping(value = "/{repairid}")
    public AjaxResult getInfo(@PathVariable("repairid") Long repairid)
    {
        return AjaxResult.success(tRepairService.selectTRepairById(repairid));
    }

    /**
     * 新增车辆维修
     */
    @PreAuthorize("@ss.hasPermi('busRepair:busrepair:add')")
    @Log(title = "车辆维修", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TRepair tRepair)
    {
        return toAjax(tRepairService.insertTRepair(tRepair));
    }

    /**
     * 修改车辆维修
     */
    @PreAuthorize("@ss.hasPermi('busRepair:busrepair:edit')")
    @Log(title = "车辆维修", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TRepair tRepair)
    {
        return toAjax(tRepairService.updateTRepair(tRepair));
    }

    /**
     * 删除车辆维修
     */
    @PreAuthorize("@ss.hasPermi('busRepair:busrepair:remove')")
    @Log(title = "车辆维修", businessType = BusinessType.DELETE)
	@DeleteMapping("/{repairids}")
    public AjaxResult remove(@PathVariable Long[] repairids)
    {
        return toAjax(tRepairService.deleteTRepairByIds(repairids));
    }
}
