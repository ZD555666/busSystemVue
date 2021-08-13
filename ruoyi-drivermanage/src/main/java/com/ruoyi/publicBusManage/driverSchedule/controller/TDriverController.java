package com.ruoyi.publicBusManage.driverSchedule.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.publicBusManage.driverSchedule.domain.*;
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
import com.ruoyi.publicBusManage.driverSchedule.service.ITDriverService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 司机排班Controller
 *
 * @author YM
 * @date 2021-07-28
 */
@RestController
@RequestMapping("/driverManager/driverSchedule")
public class TDriverController extends BaseController {
    @Autowired
    private ITDriverService tDriverService;

    /**
     * 查询司机排班列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverSchedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(TDriver tDriver) {
        startPage();
        List<TDriver> list = tDriverService.selectTDriverList(tDriver);
        return getDataTable(list);
    }

    /**
     * 导出司机排班列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverSchedule:export')")
    @Log(title = "司机排班", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TDriver tDriver) {
        List<TDriver> list = tDriverService.selectTDriverList(tDriver);
        ExcelUtil<TDriver> util = new ExcelUtil<TDriver>(TDriver.class);
        return util.exportExcel(list, "司机排班数据");
    }

    /**
     * 获取司机排班详细信息
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverSchedule:query')")
    @GetMapping(value = "/{driverworkid}")
    public AjaxResult getInfo(@PathVariable("driverworkid") Long driverworkid) {
        return AjaxResult.success(tDriverService.selectTDriverById(driverworkid));
    }

    /**
     * 新增司机排班
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverSchedule:add')")
    @Log(title = "司机排班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TDriver tDriver) {
        return toAjax(tDriverService.insertTDriver(tDriver));
    }

    /**
     * 修改司机排班
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverSchedule:edit')")
    @Log(title = "司机排班", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TDriver tDriver) {
        return toAjax(tDriverService.updateTDriver(tDriver));
    }

    /**
     * 删除司机排班
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverSchedule:remove')")
    @Log(title = "司机排班", businessType = BusinessType.DELETE)
    @DeleteMapping("/{driverworkids}")
    public AjaxResult remove(@PathVariable Long[] driverworkids) {
        return toAjax(tDriverService.deleteTDriverByIds(driverworkids));
    }


    //    新方法，发车时刻
    @PreAuthorize("@ss.hasPermi('driverManager:driverSchedule:busScheduleList')")
    @GetMapping("/busScheduleList")
    public AjaxResult busScheduleInfoList(TschedudleInfo tschedudleInfo) {
        List<TschedudleInfo> scheduleInfoList = tDriverService.busScheduleInfoList(tschedudleInfo);

        System.out.println("这个方法进来了!!  " + scheduleInfoList);

        return AjaxResult.success(scheduleInfoList);
    }

    //查询站点信息
    @PostMapping("/StationInfolist")
    public List<TDriver> selectStationInfolist(@RequestBody TDriver tDriver) {
        List<TDriver> stationInforList = tDriverService.selectStationInfolist();
        List<TDriver> driverStaInfoList=new ArrayList<TDriver>();

        for (TDriver i:stationInforList
             ) {
            i.setValue(i.getStationName());
            driverStaInfoList.add(i);
        }
        System.out.println("这个方法进来了!!  " + driverStaInfoList);
        return driverStaInfoList;
    }

//    新增新的排班
    @PostMapping("/dialogInputInfoList")
    public List<TDriver> addDialogInputInfo(@RequestBody TDriver tDriver) {



        System.out.println("上传的参数对象为" + tDriver);
        return null;
    }

//    查询公交线路
    @PostMapping("/busnoInformationList")
    public List<BusInformation> selectBusnoInformationList(@RequestBody TDriver tDriver){
        List<BusInformation> busInformationList=tDriverService.selectBusInformationList();
        List<BusInformation> busInformations=new ArrayList<BusInformation>();
        for (BusInformation x:busInformationList
             ) {
            x.setValue(x.getBusNo());
            busInformations.add(x);
        }

        return busInformations;
    }

//    查询公交车牌
    @PostMapping("/busPlateInformationList")
    public List<BusInformation> busPlateInformationList(@RequestBody TDriver tDriver){
        List<BusInformation> busInformationList=tDriverService.selectBusInformationList();
        List<BusInformation> busInformations=new ArrayList<BusInformation>();

        for (BusInformation y:busInformationList
             ) {
            y.setValue(y.getLicensePlate());
            busInformations.add(y);
        }

        return busInformations;
    }

//    查询司机信息
    @PostMapping("/busDriverInformationList")
    public List<sysUserInfor> selectBusDriverInformationList(@RequestBody TDriver tDriver){
            List<sysUserInfor> userInforList=tDriverService.selectBusDriverInformationList();
            List<sysUserInfor> userInforList11=new ArrayList<sysUserInfor>();

        for (sysUserInfor j:userInforList
             ) {
            j.setValue(j.getUserName());
            userInforList11.add(j);
        }

        return userInforList11;
    }


}
