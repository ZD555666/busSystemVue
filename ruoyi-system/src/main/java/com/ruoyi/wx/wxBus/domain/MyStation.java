package com.ruoyi.wx.wxBus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/30 17:59
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyStation {
    private int stationId;
    private int cityId;
    private String stationName;
    private String xPoint;
    private String yPoint;
    private String Point;
    private Road road;
}
