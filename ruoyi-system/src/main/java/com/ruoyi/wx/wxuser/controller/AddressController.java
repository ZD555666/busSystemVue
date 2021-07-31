package com.ruoyi.wx.wxuser.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.NearbyUtil;
import com.ruoyi.wx.wxuser.domain.WxStationInfo;
import com.ruoyi.wx.wxuser.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:44
 * @desc:
 */
@RestController
@RequestMapping("/wx")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("queryNearStation")
    public AjaxResult queryNearStation(@RequestBody HashMap<String, Object> map) {
        double area = Double.valueOf(map.get("distance").toString()) / 100.00;
        System.err.println(area);
        Map near = NearbyUtil.findNear((double) map.get("longitude"), (double) map.get("latitude"), area);
        List<WxStationInfo> list = addressService.queryNearStation((double) near.get("minLng"), (double) near.get("maxLng"), (double) near.get("minLat"), (double) near.get("maxLat"), (String) map.get("cityInfo"));
        for (WxStationInfo stationInfo : list) {
            double distance = NearbyUtil.getDistance((double) map.get("longitude"), (double) map.get("latitude"), stationInfo.getXPoint(), stationInfo.getYPoint());
            stationInfo.setDistance(distance);
        }
        System.err.println(list);
        return AjaxResult.success(list);
    }

}
