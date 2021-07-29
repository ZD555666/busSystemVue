package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.WxAliPay;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 16:34
 * @desc:
 */
public interface WxAliPayService {

    int insertPayInfo(WxAliPay aliPay);

    int updPayStateByOpId(String opId,String orderNo, String payState);

    String queryPayState(String out_trade_no,String openId,int total_amount);

}
