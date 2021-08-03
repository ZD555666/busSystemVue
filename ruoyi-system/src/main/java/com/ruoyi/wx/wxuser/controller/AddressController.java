package com.ruoyi.wx.wxuser.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.NearbyUtil;
import com.ruoyi.wx.wxuser.domain.StationRoadVo;
import com.ruoyi.wx.wxuser.domain.WxBusRealRun;
import com.ruoyi.wx.wxuser.domain.WxCollect;
import com.ruoyi.wx.wxuser.domain.WxStationInfo;
import com.ruoyi.wx.wxuser.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @PostMapping("queryDetail")
    public AjaxResult queryDetail(@RequestBody HashMap<String, Object> map) {
        int stationId = (int) map.get("stationId");
        List<WxBusRealRun> busNos = addressService.queryBusNoById(stationId, (String) map.get("cityName"), 0);
        List<StationRoadVo> list = new ArrayList<>();
        for (WxBusRealRun vo : busNos) {
            List<StationRoadVo> listVo = addressService.queryBusTo(vo.getBusNo(), (String) map.get("cityName"), 0);
            for (StationRoadVo stationRoadVo : listVo) {
                StationRoadVo roadVo = new StationRoadVo();
                if (stationRoadVo.getTravelSort() == listVo.size()) {
                    list.add(roadVo.setTravelSort(stationRoadVo.getTravelSort())
                            .setStationName(stationRoadVo.getStationName())
                            .setBusNo(vo.getBusNo()).setEndStation(stationRoadVo
                                    .getStationName()).setLicensePlate(vo.getLicensePlate()).setXPoint(stationRoadVo.getXPoint()).setYPoint(stationRoadVo.getYPoint()));
                }
            }
        }
        System.err.println(list);
        return AjaxResult.success(list);
    }

    @PostMapping("queryBusDetailInfo")
    public AjaxResult queryBusDetailInfo(@RequestBody HashMap<String, Object> map) {
        String busString = JSONObject.toJSONString(map.get("busDetailInfo"));
        String clickStation = (String) map.get("clickStation");
        List<StationRoadVo> busDetailInfo = JSONObject.parseArray(busString, StationRoadVo.class);
        List<StationRoadVo> list = new ArrayList<>();
        for (StationRoadVo stationRoadVo : busDetailInfo) {
            StationRoadVo realRun = addressService.queryDistanceAndSpeed(stationRoadVo.getLicensePlate(), (String) map.get("cityName"), 0);
            double distance = NearbyUtil.getDistance(realRun.getRealXPoint(), realRun.getRealYPoint(), realRun.getXPoint(), realRun.getYPoint());
            realRun.setDistance(distance);
            realRun.setDistanceTime(((distance / 1000.00) / realRun.getSpeed() * 60));
            realRun.setStationName(stationRoadVo.getStationName()).setEndStation(stationRoadVo.getEndStation()).setBusNo(stationRoadVo.getBusNo());
            int stationOne = addressService.querySurplusStation(clickStation, stationRoadVo.getBusNo(), (String) map.get("cityName"), 0);
            int stationTwo = addressService.querySurplusStation(realRun.getNowStation(), stationRoadVo.getBusNo(), (String) map.get("cityName"), 0);
            realRun.setSurplusStation(stationOne - stationTwo);

            list.add(realRun);
        }
        System.err.println(list);
        return AjaxResult.success(list);
    }

    @PostMapping("doCollect")
    public AjaxResult doCollect(@RequestBody HashMap<String, Object> map) {
        List<WxCollect> wxCollects = addressService.queryCollectByOpId((String) map.get("stationName"), (String) map.get("cityName"), (String) map.get("opId"));
        return wxCollects.size() == 0 ?
                AjaxResult.success(addressService.insertCollect((String) map.get("stationName"), (String) map.get("cityName"), (String) map.get("opId"))) :
                AjaxResult.success("exist");
    }


}
