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
import com.ruoyi.road.domain.TCity;
import com.ruoyi.road.service.ITCityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 城市配置Controller
 * 
 * @author ruoyi
 * @date 2021-07-24
 */
@RestController
@RequestMapping("/road/city")
public class TCityController extends BaseController
{
    @Autowired
    private ITCityService tCityService;

    /**
     * 查询城市配置列表
     */
    @PreAuthorize("@ss.hasPermi('road:city:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCity tCity)
    {
        startPage();
        List<TCity> list = tCityService.selectTCityList(tCity);
        return getDataTable(list);
    }

    /**
     * 导出城市配置列表
     */
    @PreAuthorize("@ss.hasPermi('road:city:export')")
    @Log(title = "城市配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TCity tCity)
    {
        List<TCity> list = tCityService.selectTCityList(tCity);
        ExcelUtil<TCity> util = new ExcelUtil<TCity>(TCity.class);
        return util.exportExcel(list, "城市配置数据");
    }

    /**
     * 获取城市配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('road:city:query')")
    @GetMapping(value = "/{cityid}")
    public AjaxResult getInfo(@PathVariable("cityid") Integer cityid)
    {
        return AjaxResult.success(tCityService.selectTCityById(cityid));
    }

    /**
     * 新增城市配置
     */
    @PreAuthorize("@ss.hasPermi('road:city:add')")
    @Log(title = "城市配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCity tCity)
    {
        return toAjax(tCityService.insertTCity(tCity));
    }

    /**
     * 修改城市配置
     */
    @PreAuthorize("@ss.hasPermi('road:city:edit')")
    @Log(title = "城市配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCity tCity)
    {
        return toAjax(tCityService.updateTCity(tCity));
    }

    /**
     * 删除城市配置
     */
    @PreAuthorize("@ss.hasPermi('road:city:remove')")
    @Log(title = "城市配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cityids}")
    public AjaxResult remove(@PathVariable Integer[] cityids)
    {
        return toAjax(tCityService.deleteTCityByIds(cityids));
    }
}
