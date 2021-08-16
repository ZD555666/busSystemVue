package com.ruoyi.wx.wxBus.service.impl;

import com.ruoyi.wx.wxBus.mapper.IsShowMapper;
import com.ruoyi.wx.wxBus.service.IsShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/16 17:06
 * @desc:
 */
@Service
public class IsShowServiceImpl implements IsShowService {
    @Autowired
    private IsShowMapper isShowMapper;

    @Override
    public Integer selectNew() {
        return isShowMapper.selectNew();
    }
}
