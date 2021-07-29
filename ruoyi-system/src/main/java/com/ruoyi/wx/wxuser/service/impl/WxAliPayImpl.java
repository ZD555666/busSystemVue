package com.ruoyi.wx.wxuser.service.impl;

import com.ruoyi.wx.wxuser.domain.WxAliPay;
import com.ruoyi.wx.wxuser.mapper.AliPayMapper;
import com.ruoyi.wx.wxuser.service.WxAliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 16:34
 * @desc:
 */
@Service
public class WxAliPayImpl implements WxAliPayService {

    @Autowired
    private AliPayMapper aliPayMapper;

    @Override
    public int insertPayInfo(WxAliPay aliPay) {
        return aliPayMapper.insertPayInfo(aliPay);
    }

    @Override
    public int updPayStateByOpId(String opId, String orderNo, String payState) {
        return aliPayMapper.updPayStateByOpId(opId, orderNo, payState);
    }

    @Override
    public String queryPayState(String out_trade_no, String openId, int total_amount) {
        return aliPayMapper.queryPayState(out_trade_no, openId, total_amount);
    }
}
