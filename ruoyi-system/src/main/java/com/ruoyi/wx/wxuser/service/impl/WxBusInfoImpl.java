package com.ruoyi.wx.wxuser.service.impl;

import com.ruoyi.wx.wxuser.domain.WxBusInfo;
import com.ruoyi.wx.wxuser.mapper.WxBusInfoMapper;
import com.ruoyi.wx.wxuser.service.WxBusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/10 20:39
 */
@Service
public class WxBusInfoImpl implements WxBusInfoService {

    @Autowired
    private WxBusInfoMapper busInfoMapper;

    @Override
    public List<WxBusInfo> queryBus(String busNo, int busParam1, int busParam2) {
        return busInfoMapper.queryBus(busNo, busParam1, busParam2);
    }

    @Override
    public int insertRealRunBus(WxBusInfo busInfo) {
        return busInfoMapper.insertRealRunBus(busInfo);
    }

    @Override
    public int updBusState(String licensePlate, int busParam) {
        return busInfoMapper.updBusState(licensePlate, busParam);
    }

    @Override
    public int updBusWhenOver(int busParam) {
        return busInfoMapper.updBusWhenOver(busParam);
    }
}
