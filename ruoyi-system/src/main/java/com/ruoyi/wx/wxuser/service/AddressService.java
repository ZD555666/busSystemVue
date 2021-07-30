package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.AddressVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:50
 * @desc:
 */
public interface AddressService {

    List<AddressVo> queryStationOrRoad(String cityName, String info);


}
