package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/6 11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WxHistory implements Serializable {
    private int historyId;
    private int stationId;
    private String stationName;
    private String endStation;
    private double xPoint;
    private double yPoint;
    private String busNo;
    private String cityName;
    private String openId;

}
