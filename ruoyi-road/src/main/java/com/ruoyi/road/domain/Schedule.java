package com.ruoyi.road.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公交车时刻对象 t_schedule
 *
 * @author ruoyi
 * @date 2021-08-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    /**
     * 时刻id
     */
    private Integer scheduleId;

    /**
     * 公交车x路
     */
    @Excel(name = "公交车x路")
    private String busNo;

    /**
     * 各个时间段发车时间
     */
    @Excel(name = "各个时间段发车时间")
    private Integer timeInterval;

    /**
     * 发车时间段(前)
     */
    @Excel(name = "发车时间段(前)")
    private Integer starTime;

    /**
     * 发车时间段(后)
     */
    @Excel(name = "发车时间段(后)")
    private Integer endTime;

    /**
     * 车辆状态
     */
    @Excel(name = "车辆状态")
    private String busState;
}