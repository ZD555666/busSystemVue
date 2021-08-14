package com.ruoyi.wx.wxBus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/26 18:39
 * @desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News implements Serializable {
    private String title;
    private String time;
    private String src;
}
