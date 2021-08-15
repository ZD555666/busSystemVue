package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/15 1:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaiDuMarkers implements Serializable {
    private String iconPath = "../../images/marker_red.png";
    private int id;
    private double longitude;
    private double latitude;
    private String width = "25px";
    private String height = "32px";
    private String stationName;
}
