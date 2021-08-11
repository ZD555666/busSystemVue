package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/10 13:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WxSchedule implements Serializable {

    private int scheduleId;
    private String busNo;
    private int timeInterval;
    private int starTime;
    private int endTime;
    private String busState;
    private String expression;



}
