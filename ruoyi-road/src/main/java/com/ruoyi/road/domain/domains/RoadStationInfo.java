package com.ruoyi.road.domain.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: William
 * @date: 2021/8/3 22:58
 * @desc:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadStationInfo {
    private int stationId;
    private String busNo;
    private int cost;
    //一趟的时间
    private String travelTime;
    //站点顺序
    private int travelSort;
    //始发，还是终点发  0，1
    private int direction;
    private int cityId;
    private String cityName;
    private String stationName;
    private double xPoint;
    private double yPoint;
    //坐标位置
    private String point;
}
