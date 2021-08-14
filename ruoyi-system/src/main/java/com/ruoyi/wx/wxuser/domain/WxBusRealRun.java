package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/2 16:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxBusRealRun {

    private int realrunId;
    private String busNo;
    private String licensePlate;
    private double realXPoint;
    private double realYPoint;
    private String realPoint;
    private String nowStation;
    private String nextStation;
    private String roadState;
    private double speed;
    private String personNum;
    private int cityId;
    private double distance;
    private String expression;
    private double xPoint;
    private double yPoint;
    private int direction;
    private String stationName;


}
