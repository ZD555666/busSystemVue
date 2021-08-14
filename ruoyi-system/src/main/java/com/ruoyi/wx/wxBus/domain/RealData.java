package com.ruoyi.wx.wxBus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/13 1:03
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RealData implements Serializable {
    private int moneyStation;
    private String time;
    private String meter;
    private String startTime;
    private String allData;
    private String nearStation;
    private int nowStation;
}
