package com.ruoyi.wx.wxuser.mapper;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/27 13:46
 * @desc:
 */
public interface WxMoneyMapper {

    int insertMoneyByOpId(String opId);

    int queryMoneyByOpId(String opId);


}
