package com.ruoyi.wx.complaint.controller;

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
import com.ruoyi.wx.complaint.domain.TWxComplaint;
import com.ruoyi.wx.complaint.service.ITWxComplaintService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 投诉建议管理Controller
 * 
 * @author ruoyi
 * @date 2021-08-16
 */
@RestController
@RequestMapping("/complaint/complaint")
public class TWxComplaintController extends BaseController
{
    @Autowired
    private ITWxComplaintService tWxComplaintService;

    /**
     * 查询投诉建议管理列表
     */
    @PreAuthorize("@ss.hasPermi('complaint:complaint:list')")
    @GetMapping("/list")
    public TableDataInfo list(TWxComplaint tWxComplaint)
    {
        startPage();
        List<TWxComplaint> list = tWxComplaintService.selectTWxComplaintList(tWxComplaint);
        return getDataTable(list);
    }

    /**
     * 导出投诉建议管理列表
     */
    @PreAuthorize("@ss.hasPermi('complaint:complaint:export')")
    @Log(title = "投诉建议管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TWxComplaint tWxComplaint)
    {
        List<TWxComplaint> list = tWxComplaintService.selectTWxComplaintList(tWxComplaint);
        ExcelUtil<TWxComplaint> util = new ExcelUtil<TWxComplaint>(TWxComplaint.class);
        return util.exportExcel(list, "投诉建议管理数据");
    }

    /**
     * 获取投诉建议管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('complaint:complaint:query')")
    @GetMapping(value = "/{complaintid}")
    public AjaxResult getInfo(@PathVariable("complaintid") Long complaintid)
    {
        return AjaxResult.success(tWxComplaintService.selectTWxComplaintById(complaintid));
    }

    /**
     * 新增投诉建议管理
     */
    @PreAuthorize("@ss.hasPermi('complaint:complaint:add')")
    @Log(title = "投诉建议管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TWxComplaint tWxComplaint)
    {
        return toAjax(tWxComplaintService.insertTWxComplaint(tWxComplaint));
    }

    /**
     * 修改投诉建议管理
     */
    @PreAuthorize("@ss.hasPermi('complaint:complaint:edit')")
    @Log(title = "投诉建议管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TWxComplaint tWxComplaint)
    {
        return toAjax(tWxComplaintService.updateTWxComplaint(tWxComplaint));
    }

    /**
     * 删除投诉建议管理
     */
    @PreAuthorize("@ss.hasPermi('complaint:complaint:remove')")
    @Log(title = "投诉建议管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{complaintids}")
    public AjaxResult remove(@PathVariable Long[] complaintids)
    {
        return toAjax(tWxComplaintService.deleteTWxComplaintByIds(complaintids));
    }
}
