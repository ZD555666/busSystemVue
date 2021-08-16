package com.ruoyi.busInfo.controller;

import java.util.List;


//import com.ruoyi.busInfo.domain.TBusscheduling;
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
import com.ruoyi.busInfo.domain.TBusinfo;
import com.ruoyi.busInfo.service.ITBusinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 车辆信息Controller
 *
 * @author
 * @date 2021-07-23
 */
@RestController
@RequestMapping("/busManage/businfo")
public class TBusinfoController extends BaseController
{
    @Autowired
    private ITBusinfoService tBusinfoService;

    /**
     * 查询车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('busManage:businfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TBusinfo tBusinfo)
    {
        startPage();
        List<TBusinfo> list = tBusinfoService.selectTBusinfoList(tBusinfo);
        System.out.println("》》》》》》》"+list);
        return getDataTable(list);

    }
    @PreAuthorize("@ss.hasPermi('busManage:businfo:list1')")
    @GetMapping("/list1")
    public TableDataInfo list1(TBusinfo tBusinfo)
    {
        startPage();
        List<TBusinfo> list1 = tBusinfoService.selectTBusinfoList1(tBusinfo);
        System.out.println("水水水水》》》》》》》"+list1);

        return getDataTable(list1);

    }
    @PreAuthorize("@ss.hasPermi('busManage:businfo:list2')")
    @GetMapping("/list2")
    public TableDataInfo list2(TBusinfo tBusinfo)
    {
        startPage();
        List<TBusinfo> list2 = tBusinfoService.selectTBusinfoList2(tBusinfo);
        System.out.println("水水水水aaaaaaaaaaaaaaaaaaa》》》》》》》"+list2);

        return getDataTable(list2);

    }
    @PreAuthorize("@ss.hasPermi('busManage:businfo:list3')")
    @GetMapping("/list3")
    public TableDataInfo list3(TBusinfo tBusinfo)
    {
        startPage();
        List<TBusinfo> list3 = tBusinfoService.selectTBusinfoList3(tBusinfo);
        System.out.println("3333aaaaaaaaaaaaaaaaaaa》》》》》》》"+list3);

        return getDataTable(list3);

    }
    @PreAuthorize("@ss.hasPermi('busManage:businfo:list4')")
    @GetMapping("/list4")
    public TableDataInfo list4(TBusinfo tBusinfo)
    {
        startPage();
        List<TBusinfo> list4 = tBusinfoService.selectTBusinfoList4(tBusinfo);
        System.out.println("4444aaaaaaaaaaaaaaaaaaa》》》》》》》"+list4);

        return getDataTable(list4);

    }
    @PreAuthorize("@ss.hasPermi('busManage:businfo:list5')")
    @GetMapping("/list5")
    public TableDataInfo list5(TBusinfo tBusinfo)
    {
        startPage();
        List<TBusinfo> list5 = tBusinfoService.selectTBusinfoList5(tBusinfo);
        System.out.println("5555aaaaaaaaaaaaaaaaaaa》》》》》》》"+list5);

        return getDataTable(list5);

    }
//    @PreAuthorize("@ss.hasPermi('busManage:businfo:list6')")
//    @GetMapping("/list6")
//    public TableDataInfo list6(TBusscheduling tBusscheduling)
//    {
//        startPage();
//        List<TBusscheduling> list6 = tBusinfoService.selectTBusschedulingList(tBusscheduling);
//        return getDataTable(list6);
//    }
//    @PreAuthorize("@ss.hasPermi('busManage:businfo:edit')")
//    @Log(title = "车辆信息", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody TBusscheduling tBusscheduling)
//    {
//        return toAjax(tBusinfoService.updateTBusscheduling(tBusscheduling));
//    }
//    @PreAuthorize("@ss.hasPermi('busManage:businfo:query')")
//    @GetMapping(value = "/{busid}")
//    public AjaxResult getInfo1(@PathVariable("busid") Long busid)
//    {
//        return AjaxResult.success(tBusinfoService.selectTBusschedulingById(busid));
//    }
    /**
     * 导出车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('busManage:businfo:export')")
    @Log(title = "车辆信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TBusinfo tBusinfo)
    {
        List<TBusinfo> list = tBusinfoService.selectTBusinfoList(tBusinfo);
        ExcelUtil<TBusinfo> util = new ExcelUtil<TBusinfo>(TBusinfo.class);
        return util.exportExcel(list, "车辆信息数据");
    }

    /**
     * 获取车辆信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('busManage:businfo:query')")
    @GetMapping(value = "/{busid}")
    public AjaxResult getInfo(@PathVariable("busid") Long busid)
    {
        return AjaxResult.success(tBusinfoService.selectTBusinfoById(busid));
    }

    /**
     * 新增车辆信息
     */
    @PreAuthorize("@ss.hasPermi('busManage:businfo:add')")
    @Log(title = "车辆信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TBusinfo tBusinfo)
    {
        return toAjax(tBusinfoService.insertTBusinfo(tBusinfo));
    }
//    @PreAuthorize("@ss.hasPermi('busScheduling:busscheduling:add')")
//    @Log(title = "busscheduling", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody TBusscheduling tBusscheduling)
//    {
//        return toAjax(tBusinfoService.insertTBusscheduling(tBusscheduling));
//    }


    /**
     * 修改车辆信息
     */
    @PreAuthorize("@ss.hasPermi('busManage:businfo:edit')")
    @Log(title = "车辆信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TBusinfo tBusinfo)
    {
        return toAjax(tBusinfoService.updateTBusinfo(tBusinfo));
    }

    /**
     * 删除车辆信息
     */
    @PreAuthorize("@ss.hasPermi('busManage:businfo:remove')")
    @Log(title = "车辆信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{busids}")
    public AjaxResult remove(@PathVariable Long[] busids)
    {
        return toAjax(tBusinfoService.deleteTBusinfoByIds(busids));
    }
}
