package com.ruoyi.wx.wxuser.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.wx.wxuser.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:44
 * @desc:
 */
@RequestMapping("/wx")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("queryStationOrRoad")
    public AjaxResult queryStationOrRoad(@RequestBody HashMap<String,Object> map) {
//        addressService.queryStationOrRoad(map.get("nowCity"),map.get("info"));

        return AjaxResult.success();
    }

}
