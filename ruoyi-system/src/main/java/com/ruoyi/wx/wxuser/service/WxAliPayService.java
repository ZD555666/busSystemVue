package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.WxAliPay;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 16:34
 * @desc:
 */
public interface WxAliPayService {

    int insertPayInfo(WxAliPay aliPay);

    int updPayStateByOpId(String opId,String orderNo, String payState);

}
