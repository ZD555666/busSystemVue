package com.ruoyi.road.controller;

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
import com.ruoyi.road.domain.TBusinfo;
import com.ruoyi.road.service.ITBusinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 详细信息Controller
 * 
 * @author gjw
 * @date 2021-07-22
 */
@RestController
@RequestMapping("/bus/businfo")
public class TBusinfoController extends BaseController
{
    @Autowired
    private ITBusinfoService tBusinfoService;

    /**
     * 查询详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('bus:businfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TBusinfo tBusinfo)
    {
        startPage();
        List<TBusinfo> list = tBusinfoService.selectTBusinfoList(tBusinfo);
        return getDataTable(list);
    }

    /**
     * 导出详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('bus:businfo:export')")
    @Log(title = "详细信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TBusinfo tBusinfo)
    {
        List<TBusinfo> list = tBusinfoService.selectTBusinfoList(tBusinfo);
        ExcelUtil<TBusinfo> util = new ExcelUtil<TBusinfo>(TBusinfo.class);
        return util.exportExcel(list, "详细信息数据");
    }

    /**
     * 获取详细信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('bus:businfo:query')")
    @GetMapping(value = "/{busid}")
    public AjaxResult getInfo(@PathVariable("busid") Long busid)
    {
        return AjaxResult.success(tBusinfoService.selectTBusinfoById(busid));
    }

    /**
     * 新增详细信息
     */
    @PreAuthorize("@ss.hasPermi('bus:businfo:add')")
    @Log(title = "详细信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TBusinfo tBusinfo)
    {
        return toAjax(tBusinfoService.insertTBusinfo(tBusinfo));
    }

    /**
     * 修改详细信息
     */
    @PreAuthorize("@ss.hasPermi('bus:businfo:edit')")
    @Log(title = "详细信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TBusinfo tBusinfo)
    {
        return toAjax(tBusinfoService.updateTBusinfo(tBusinfo));
    }

    /**
     * 删除详细信息
     */
    @PreAuthorize("@ss.hasPermi('bus:businfo:remove')")
    @Log(title = "详细信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{busids}")
    public AjaxResult remove(@PathVariable Long[] busids)
    {
        return toAjax(tBusinfoService.deleteTBusinfoByIds(busids));
    }
}
