package com.ruoyi.publicBusManage.quantifies.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import com.ruoyi.publicBusManage.departureConfirm.domain.TDepartureConfirm;
import com.ruoyi.publicBusManage.quantifies.domain.TDriverTime;
import com.ruoyi.publicBusManage.quantifies.domain.TDriverYy;
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
import com.ruoyi.publicBusManage.quantifies.domain.TDriverWorktime;
import com.ruoyi.publicBusManage.quantifies.service.ITDriverWorktimeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 司机工作时间Controller
 *
 * @author ym
 * @date 2021-08-09
 */
@RestController
@RequestMapping("/quantifies/quantifies")
public class TDriverWorktimeController extends BaseController {
    @Autowired
    private ITDriverWorktimeService tDriverWorktimeService;

    /**
     * 查询司机工作时间列表
     */
    @PreAuthorize("@ss.hasPermi('quantifies:quantifies:list')")
    @GetMapping("/list")
    public TableDataInfo list(TDriverWorktime tDriverWorktime) {
        startPage();
        List<TDriverWorktime> list = tDriverWorktimeService.selectTDriverWorktimeList(tDriverWorktime);
        return getDataTable(list);
    }

    /**
     * 导出司机工作时间列表
     */
    @PreAuthorize("@ss.hasPermi('quantifies:quantifies:export')")
    @Log(title = "司机工作时间", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TDriverWorktime tDriverWorktime) {
        List<TDriverWorktime> list = tDriverWorktimeService.selectTDriverWorktimeList(tDriverWorktime);
        ExcelUtil<TDriverWorktime> util = new ExcelUtil<TDriverWorktime>(TDriverWorktime.class);
        return util.exportExcel(list, "司机工作时间数据");
    }

    /**
     * 获取司机工作时间详细信息
     */
    @PreAuthorize("@ss.hasPermi('quantifies:quantifies:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tDriverWorktimeService.selectTDriverWorktimeById(id));
    }

    /**
     * 新增司机工作时间
     */
    @PreAuthorize("@ss.hasPermi('quantifies:quantifies:add')")
    @Log(title = "司机工作时间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TDriverWorktime tDriverWorktime) {
        return toAjax(tDriverWorktimeService.insertTDriverWorktime(tDriverWorktime));
    }

    /**
     * 修改司机工作时间
     */
    @PreAuthorize("@ss.hasPermi('quantifies:quantifies:edit')")
    @Log(title = "司机工作时间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TDriverWorktime tDriverWorktime) {
        return toAjax(tDriverWorktimeService.updateTDriverWorktime(tDriverWorktime));
    }

    /**
     * 删除司机工作时间
     */
    @PreAuthorize("@ss.hasPermi('quantifies:quantifies:remove')")
    @Log(title = "司机工作时间", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tDriverWorktimeService.deleteTDriverWorktimeByIds(ids));
    }

    //    上班打卡
    @PostMapping("/updateWorkPunchIn")
    public int updateWorkPunchIn(@RequestBody TDriverWorktime tDriverWorktime) {
        //            获得系统时间
        GetTimeUtils getTimeUtils = new GetTimeUtils();
        String getTimeAndDateStr = getTimeUtils.getTime();
        String[] splitStr = getTimeAndDateStr.split(" ");
//        时间
        String getTimeStr = splitStr[1];
//        日期
        String getDeteStr = splitStr[0];

        String[] ifTimeSplite = getTimeStr.split(":");

        String ifTime = ifTimeSplite[0];

//        获取对象
        TDriverYy tDriverYy = new TDriverYy();
        tDriverYy.setDriverName(tDriverWorktime.getUserName());
        //获得司机id
        TDriverYy tSelsectDriverId = tDriverWorktimeService.selectDriverId(tDriverYy);

        if (tSelsectDriverId != null) {
            if (Integer.parseInt(ifTime) <= 9) {
                if (tSelsectDriverId != null) {
                    //                设置到对象
                    tDriverWorktime.setAtworkpunch(getDeteStr);
                    tDriverWorktime.setDriverworktime("8");
                    tDriverWorktime.setGobuspunch("是");
                    tDriverWorktime.settDriverId(tSelsectDriverId.getDriverWorkId());
                    int xxx = tDriverWorktimeService.insertPunchInRecord(tDriverWorktime);
//                        上班打卡成功
                    return 1;
                }
            } else if (Integer.parseInt(ifTime) >= 17) {
                tDriverWorktime.setOffdutypunch(getDeteStr);
                tDriverWorktime.setGobuspunch("是");
                tDriverWorktime.settDriverId(tSelsectDriverId.getDriverWorkId());
                tDriverWorktime.setDriverworktime("8");
                int yyy = tDriverWorktimeService.insertOffDutyRecord(tDriverWorktime);
//                        下班打卡成功
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }

        return 0;
    }
}
