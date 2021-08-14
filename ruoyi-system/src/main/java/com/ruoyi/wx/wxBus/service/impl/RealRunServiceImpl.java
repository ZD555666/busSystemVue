package com.ruoyi.wx.wxBus.service.impl;

import com.ruoyi.wx.wxBus.domain.MyRealRun;
import com.ruoyi.wx.wxBus.mapper.RealRunMapper;
import com.ruoyi.wx.wxBus.service.RealRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/8/13 0:04
 * @desc:
 */
@Service
public class RealRunServiceImpl implements RealRunService {
    @Autowired
    private RealRunMapper realRunMapper;

    @Override
    public List<MyRealRun> queryRealRun(int direction, String busNo) {
        return realRunMapper.selectRealRun(direction,busNo);
    }
}
