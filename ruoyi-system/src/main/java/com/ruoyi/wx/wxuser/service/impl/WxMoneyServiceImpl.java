package com.ruoyi.wx.wxuser.service.impl;

import com.ruoyi.wx.wxuser.mapper.WxMoneyMapper;
import com.ruoyi.wx.wxuser.service.WxMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/27 13:58
 * @desc:
 */
@Service
public class WxMoneyServiceImpl implements WxMoneyService {

    @Autowired
    private WxMoneyMapper wxMoneyMapper;

    @Override
    public int insertMoneyByOpId(String opId, int money) {
        return wxMoneyMapper.insertMoneyByOpId(opId, money);
    }

    @Override
    public int queryMoneyByOpId(String opId) {
        return wxMoneyMapper.queryMoneyByOpId(opId);
    }

    @Override
    public int updMoneyByOpId(String opId, int money) {
        return wxMoneyMapper.updMoneyByOpId(opId, money);
    }
}
