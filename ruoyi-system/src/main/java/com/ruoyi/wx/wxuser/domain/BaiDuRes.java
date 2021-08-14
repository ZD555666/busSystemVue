package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/12 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaiDuRes implements Serializable {
    //{"result":[{"duration":{"text":"1分钟","value":35},"distance":{"text":"941米","value":941}}],"message":"成功","status":0}
    private List<BaiduResult> result;
    private String message;
    private int status;

}
