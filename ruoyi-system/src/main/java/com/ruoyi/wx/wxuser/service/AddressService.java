package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.WxStationInfo;

import java.util.List;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:50
 * @desc:
 */
public interface AddressService {

    List<WxStationInfo> queryNearStation(double minLng, double maxLng, double minLat, double maxLat, String cityName);


}
