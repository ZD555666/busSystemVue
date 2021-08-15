package com.ruoyi.wx.wxuser.controller;

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
import com.ruoyi.wx.wxuser.domain.TWxRecord;
import com.ruoyi.wx.wxuser.service.ITWxRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 消费记录Controller
 * 
 * @author gjw
 * @date 2021-08-15
 */
@RestController
@RequestMapping("/wxuser/record")
public class TWxRecordController extends BaseController
{
    @Autowired
    private ITWxRecordService tWxRecordService;

    /**
     * 查询消费记录列表
     */
    @PreAuthorize("@ss.hasPermi('wxuser:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(TWxRecord tWxRecord)
    {
        startPage();
        List<TWxRecord> list = tWxRecordService.selectTWxRecordList(tWxRecord);
        return getDataTable(list);
    }

    /**
     * 导出消费记录列表
     */
    @PreAuthorize("@ss.hasPermi('wxuser:record:export')")
    @Log(title = "消费记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TWxRecord tWxRecord)
    {
        List<TWxRecord> list = tWxRecordService.selectTWxRecordList(tWxRecord);
        ExcelUtil<TWxRecord> util = new ExcelUtil<TWxRecord>(TWxRecord.class);
        return util.exportExcel(list, "消费记录数据");
    }

    /**
     * 获取消费记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('wxuser:record:query')")
    @GetMapping(value = "/{rid}")
    public AjaxResult getInfo(@PathVariable("rid") Long rid)
    {
        return AjaxResult.success(tWxRecordService.selectTWxRecordById(rid));
    }

    /**
     * 新增消费记录
     */
    @PreAuthorize("@ss.hasPermi('wxuser:record:add')")
    @Log(title = "消费记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TWxRecord tWxRecord)
    {
        return toAjax(tWxRecordService.insertTWxRecord(tWxRecord));
    }

    /**
     * 修改消费记录
     */
    @PreAuthorize("@ss.hasPermi('wxuser:record:edit')")
    @Log(title = "消费记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TWxRecord tWxRecord)
    {
        return toAjax(tWxRecordService.updateTWxRecord(tWxRecord));
    }

    /**
     * 删除消费记录
     */
    @PreAuthorize("@ss.hasPermi('wxuser:record:remove')")
    @Log(title = "消费记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rids}")
    public AjaxResult remove(@PathVariable Long[] rids)
    {
        return toAjax(tWxRecordService.deleteTWxRecordByIds(rids));
    }
}
