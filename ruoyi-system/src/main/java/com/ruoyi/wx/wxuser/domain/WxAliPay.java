package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 14:53
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WxAliPay implements Serializable {

    private int id;
    private String out_trade_no;
    private int total_amount;
    private String subject;
    private String store_id;
    private String openId;
    private String payState;


}
