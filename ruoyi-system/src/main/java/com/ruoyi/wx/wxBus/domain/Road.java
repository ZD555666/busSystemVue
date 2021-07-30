package com.ruoyi.wx.wxBus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/30 17:56
 * @desc:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Road {
    private int roadId;
    private int stationId;
    private String busNo;
    private int cost;
    private String travelTime;
    private int travelSort;
    private int cityId;
    private Station station;
}
