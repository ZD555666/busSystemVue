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
import com.ruoyi.wx.wxuser.domain.TWxUser;
import com.ruoyi.wx.wxuser.service.ITWxUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小程序用户Controller
 * 
 * @author gjw
 * @date 2021-07-24
 */
@RestController
@RequestMapping("/wxuser/user")
public class TWxUserController extends BaseController
{
    @Autowired
    private ITWxUserService tWxUserService;

    /**
     * 查询小程序用户列表
     */
    @PreAuthorize("@ss.hasPermi('wxuser:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TWxUser tWxUser)
    {
        startPage();
        List<TWxUser> list = tWxUserService.selectTWxUserList(tWxUser);
        return getDataTable(list);
    }

    /**
     * 导出小程序用户列表
     */
    @PreAuthorize("@ss.hasPermi('wxuser:user:export')")
    @Log(title = "小程序用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TWxUser tWxUser)
    {
        List<TWxUser> list = tWxUserService.selectTWxUserList(tWxUser);
        ExcelUtil<TWxUser> util = new ExcelUtil<TWxUser>(TWxUser.class);
        return util.exportExcel(list, "小程序用户数据");
    }

    /**
     * 获取小程序用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('wxuser:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tWxUserService.selectTWxUserById(id));
    }

    /**
     * 新增小程序用户
     */
    @PreAuthorize("@ss.hasPermi('wxuser:user:add')")
    @Log(title = "小程序用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TWxUser tWxUser)
    {
        return toAjax(tWxUserService.insertTWxUser(tWxUser));
    }

    /**
     * 修改小程序用户
     */
    @PreAuthorize("@ss.hasPermi('wxuser:user:edit')")
    @Log(title = "小程序用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TWxUser tWxUser)
    {
        return toAjax(tWxUserService.updateTWxUser(tWxUser));
    }

    /**
     * 删除小程序用户
     */
    @PreAuthorize("@ss.hasPermi('wxuser:user:remove')")
    @Log(title = "小程序用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tWxUserService.deleteTWxUserByIds(ids));
    }
}
