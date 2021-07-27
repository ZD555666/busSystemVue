package com.ruoyi.road.controller;

import java.util.List;

import com.ruoyi.road.domain.TCity;
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
import com.ruoyi.road.domain.TStationinfo;
import com.ruoyi.road.service.ITStationinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 站点信息Controller
 * 
 * @author ruoyi
 * @date 2021-07-25
 */
@RestController
@RequestMapping("/road/stationinfo")
public class TStationinfoController extends BaseController
{
    @Autowired
    private ITStationinfoService tStationinfoService;

    /**
     * 查询站点信息列表
     */
    @PreAuthorize("@ss.hasPermi('road:stationinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TStationinfo tStationinfo,TCity tCity)
    {
        startPage();
        List<TStationinfo> list = tStationinfoService.selectTStationinfoList(tStationinfo,tCity);
        System.out.println("3333333333333333333");
        System.out.println(tStationinfo);
        System.out.println(tCity);
        return getDataTable(list);
    }

    /**
     * 导出站点信息列表
     */
    @PreAuthorize("@ss.hasPermi('road:stationinfo:export')")
    @Log(title = "站点信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TStationinfo tStationinfo,TCity tCity)
    {
        List<TStationinfo> list = tStationinfoService.selectTStationinfoList(tStationinfo, tCity);
        ExcelUtil<TStationinfo> util = new ExcelUtil<TStationinfo>(TStationinfo.class);
        return util.exportExcel(list, "站点信息数据");
    }

    /**
     * 获取站点信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('road:stationinfo:query')")
    @GetMapping(value = "/{stationid}")
    public AjaxResult getInfo(@PathVariable("stationid") Integer stationid)
    {
        return AjaxResult.success(tStationinfoService.selectTStationinfoById(stationid));
    }

    /**
     * 新增站点信息
     */
    @PreAuthorize("@ss.hasPermi('road:stationinfo:add')")
    @Log(title = "站点信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TStationinfo tStationinfo)
    {
        return toAjax(tStationinfoService.insertTStationinfo(tStationinfo));
    }

    /**
     * 修改站点信息
     */
    @PreAuthorize("@ss.hasPermi('road:stationinfo:edit')")
    @Log(title = "站点信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TStationinfo tStationinfo)
    {
        return toAjax(tStationinfoService.updateTStationinfo(tStationinfo));
    }

    /**
     * 删除站点信息
     */
    @PreAuthorize("@ss.hasPermi('road:stationinfo:remove')")
    @Log(title = "站点信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stationids}")
    public AjaxResult remove(@PathVariable Integer[] stationids)
    {
        return toAjax(tStationinfoService.deleteTStationinfoByIds(stationids));
    }
}
