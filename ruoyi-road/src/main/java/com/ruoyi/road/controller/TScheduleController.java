package com.ruoyi.road.controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.road.domain.TCity;
import com.ruoyi.road.domain.domains.RoadInfo;
import com.ruoyi.road.domain.domains.Station;
import com.ruoyi.road.service.ITRoadService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private ITRoadService RoadService;

    //  获取所有的线路
    @GetMapping("getAllBusNo")
    public List<RoadInfo> getAllBusNo() {
        RoadInfo roadInfo2 = new RoadInfo();
        List<RoadInfo> roadInfoList=RoadService.selectAllBusNo(roadInfo2);
//        List<RoadInfo> roadInfoList=null;
        System.out.println("132131211312313232132132Ni");
        return roadInfoList;
    }
    //一条路线新增多个时刻
    @GetMapping("addManyTimes")
    public String addManyTimes(@RequestParam("busNo") String busNo, @RequestParam("roadSchedule") String roadSchedule){
        Gson gson=new Gson();
        List<TSchedule> roadSchedules = gson.fromJson(roadSchedule, new TypeToken<List<TSchedule>>() {
        }.getType());
        for (int i = 0; i < roadSchedules.size(); i++) {
            roadSchedules.get(i).setBusNo(busNo);
        }
        System.out.println(roadSchedules);
        for (TSchedule s: roadSchedules) {
            tScheduleService.insertTSchedule(s);
        }
        return "新增成功";
    };
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
