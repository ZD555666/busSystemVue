package com.ruoyi.wx.wxuser.service;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/27 13:58
 * @desc:
 */
public interface WxMoneyService {

    int insertMoneyByOpId(String opId, int money);

    int queryMoneyByOpId(String opId);

    int updMoneyByOpId(String opId, int money);
}
