package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/10 20:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WxBusInfo implements Serializable {

    private int busId;
    private String busNo;
    private String licensePlate;
    private int busParam;
    private int roadId;
    private double realXPoint;
    private double realYPoint;
    private String nowStation;
    private String nextStation;
    private double speed;
    private int cityId;
    private int direction;

}
