package com.ruoyi.publicBusManage.departureConfirm.controller;

import java.util.List;

import com.ruoyi.publicBusManage.departureConfirm.domain.TDriverym;
import com.ruoyi.publicBusManage.departureConfirm.domain.TScheduleym;
import org.apache.poi.ss.formula.functions.T;
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
import com.ruoyi.publicBusManage.departureConfirm.domain.TDepartureConfirm;
import com.ruoyi.publicBusManage.departureConfirm.service.ITDepartureConfirmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公交车信息Controller
 *
 * @author YM
 * @date 2021-08-10
 */
@RestController
@RequestMapping("/departureConfirm/departureConfirm")
public class TDepartureConfirmController extends BaseController
{
    @Autowired
    private ITDepartureConfirmService tDepartureConfirmService;

    /**
     * 查询公交车信息列表
     */
    @PreAuthorize("@ss.hasPermi('departureConfirm:departureConfirm:list')")
    @GetMapping("/list")
    public TableDataInfo list(TDepartureConfirm tDepartureConfirm)
    {
        startPage();
        List<TDepartureConfirm> list = tDepartureConfirmService.selectTDepartureConfirmList(tDepartureConfirm);
        return getDataTable(list);
    }

    /**
     * 导出公交车信息列表
     */
    @PreAuthorize("@ss.hasPermi('departureConfirm:departureConfirm:export')")
    @Log(title = "公交车信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TDepartureConfirm tDepartureConfirm)
    {
        List<TDepartureConfirm> list = tDepartureConfirmService.selectTDepartureConfirmList(tDepartureConfirm);
        ExcelUtil<TDepartureConfirm> util = new ExcelUtil<TDepartureConfirm>(TDepartureConfirm.class);
        return util.exportExcel(list, "公交车信息数据");
    }

    /**
     * 获取公交车信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('departureConfirm:departureConfirm:query')")
    @GetMapping(value = "/{busid}")
    public AjaxResult getInfo(@PathVariable("busid") Long busid)
    {
        return AjaxResult.success(tDepartureConfirmService.selectTDepartureConfirmById(busid));
    }

    /**
     * 新增公交车信息
     */
    @PreAuthorize("@ss.hasPermi('departureConfirm:departureConfirm:add')")
    @Log(title = "公交车信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TDepartureConfirm tDepartureConfirm)
    {
        return toAjax(tDepartureConfirmService.insertTDepartureConfirm(tDepartureConfirm));
    }

    /**
     * 修改公交车信息
     */
    @PreAuthorize("@ss.hasPermi('departureConfirm:departureConfirm:edit')")
    @Log(title = "公交车信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TDepartureConfirm tDepartureConfirm)
    {
        System.out.println("打印提交的参数"+tDepartureConfirm);
        return toAjax(tDepartureConfirmService.updateTDepartureConfirm(tDepartureConfirm));
    }

    /**
     * 删除公交车信息
     */
    @PreAuthorize("@ss.hasPermi('departureConfirm:departureConfirm:remove')")
    @Log(title = "公交车信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{busids}")
    public AjaxResult remove(@PathVariable Long[] busids)
    {
        return toAjax(tDepartureConfirmService.deleteTDepartureConfirmByIds(busids));
    }

//    出站确认，新增信息
    /**
     * 新增出站信息
     */
    @PostMapping("/addGoBusConfirm")
    public int addGoBusConfirm(@RequestBody TDepartureConfirm tDepartureConfirm)
    {
        int x=0;
        if (tDepartureConfirm.getDriverName()!=null){
            TDriverym tDriverym=new TDriverym();
            tDriverym.setDriverName(tDepartureConfirm.getDriverName());
            List<TDriverym> driverInfoList=tDepartureConfirmService.selectDriverInfoList(tDriverym);
            x=driverInfoList.size();
            System.out.println("司机的集合长度是"+driverInfoList.size());
        }
        System.out.println("xxxx的值"+x);
        if(x!=0){
            if(tDepartureConfirm.getLicenseplate()!=null){
                TScheduleym tScheduleym=new TScheduleym();
                tScheduleym.setBusNo(tDepartureConfirm.getBusno());
                String timeStr=tDepartureConfirm.getGoBusTime();
                String[] split=timeStr.split(":");
                String TStr= split[1];
                tScheduleym.setStartTime(TStr);
                tScheduleym.setBusState(String.valueOf(tDepartureConfirm.getBusparam()));
                tDepartureConfirmService.updateBusScheduleState(tScheduleym);
                System.out.println("熬夜打印参数"+tScheduleym);
            }
                tDepartureConfirmService.addGoBusConfirm(tDepartureConfirm);
            return 1;
        }
        return 0;
    }

}
