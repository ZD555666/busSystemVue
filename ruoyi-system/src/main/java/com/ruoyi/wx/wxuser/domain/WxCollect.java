package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/3 23:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxCollect {

    private int collectId;
    private String stationName;
    private String cityName;
    private String openId;

}
