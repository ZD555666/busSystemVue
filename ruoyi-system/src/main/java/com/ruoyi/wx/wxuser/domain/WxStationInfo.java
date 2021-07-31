package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:55
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxStationInfo {

    private int stationId;
    private int cityId;
    private String stationName;
    private double xPoint;
    private double yPoint;
    private String point;
    private WxRoad wxRoad;
    private double distance;

}
