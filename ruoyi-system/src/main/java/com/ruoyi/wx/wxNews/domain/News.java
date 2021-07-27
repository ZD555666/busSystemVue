package com.ruoyi.wx.wxNews.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/26 18:39
 * @desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private String title;
    private String time;
    private String src;
}
