package com.ruoyi.wx.wxBus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/11 23:41
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyRealRun implements Serializable {
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
    private int direction;

}
