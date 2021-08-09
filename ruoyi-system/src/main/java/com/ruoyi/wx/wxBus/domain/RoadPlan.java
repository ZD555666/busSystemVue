package com.ruoyi.wx.wxBus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/6 0:31
 * @desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoadPlan {

    private String startWalk;
    private String endWalk;
    private String allTime;
    private String busNo;
    private String direction;
    private String startStation;
    private String endStation;
    private int momeyStation;
    private String allWalk;
    private String sameStation;
    private String startDirection;
    private String endDirection;
    private String startBus;
    private String endBus;
    private String middleWalk;
    private String startTurn;
    private String endTurn;


}
