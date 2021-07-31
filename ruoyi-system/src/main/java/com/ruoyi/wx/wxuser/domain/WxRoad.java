package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/7/31 18:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxRoad {

    private int roadId;
    private int stationId;
    private String busNo;
    private int cost;
    private String travelTime;
    private int travelSort;
    private int cityId;

}
