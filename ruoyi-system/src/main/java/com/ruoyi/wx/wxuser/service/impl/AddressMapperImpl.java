package com.ruoyi.wx.wxuser.service.impl;

import com.ruoyi.wx.wxuser.domain.AddressVo;
import com.ruoyi.wx.wxuser.mapper.AddressMapper;
import com.ruoyi.wx.wxuser.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:51
 * @desc:
 */
@Service
public class AddressMapperImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<AddressVo> queryStationOrRoad(String cityName, String info) {
        return addressMapper.queryStationOrRoad(cityName, info);
    }
}
