package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/4 15:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Children {

    private String text;
    private int id;
    private double xPoint;
    private double yPoint;
    private String cityName;

}
