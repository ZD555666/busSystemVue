package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/12 11:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaiduDistance implements Serializable {
    //{"result":[{"duration":{"text":"1分钟","value":35},"distance":{"text":"941米","value":941}}],"message":"成功","status":0}
    private String text;
    private double value;
}
