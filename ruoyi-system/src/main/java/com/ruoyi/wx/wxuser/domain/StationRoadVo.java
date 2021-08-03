package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/1 21:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StationRoadVo implements Serializable {

    private int travelSort;
    private String stationName;
    private String busNo;
    private String startStation;
    private String endStation;
    private String licensePlate;
    private double realXPoint;
    private double realYPoint;
    private String nowStation;
    private String nextStation;
    private double speed;
    private double distance;
    private double distanceTime;
    private double xPoint;
    private double yPoint;
    private int surplusStation;

}
