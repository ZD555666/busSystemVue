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
public class AddressVo {

    private int stationId;
    private String busNo;
    private int cost;
    private String travelTIme;
    private int cityId;
    private String stationName;
    private String xPoint;
    private String yPoint;
    private String point;

}
