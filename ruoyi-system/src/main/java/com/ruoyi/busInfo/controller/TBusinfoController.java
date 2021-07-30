package com.ruoyi.busInfo.controller;

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
    public TableDataInfo list(TBusinfo tBusinfo, HttpServletRequest session)
    {
        startPage();
        List<TBusinfo> list = tBusinfoService.selectTBusinfoList(tBusinfo);
        System.out.println("》》》》》》》"+list);

        session.setAttribute("list",list);

        return getDataTable(list);

    }

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
