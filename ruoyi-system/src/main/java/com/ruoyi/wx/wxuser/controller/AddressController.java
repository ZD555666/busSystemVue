package com.ruoyi.wx.wxuser.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.NearbyUtil;
import com.ruoyi.wx.wxuser.domain.*;
import com.ruoyi.wx.wxuser.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @PostMapping("/queryNearStation")
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
        Collections.sort(list, new Comparator<WxStationInfo>() {
            @Override
            public int compare(WxStationInfo o1, WxStationInfo o2) {
                return new Double(o1.getDistance()).compareTo(new Double(o2.getDistance()));
            }
        });
        return AjaxResult.success(list);
    }

    @PostMapping("/queryDetail")
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

    @PostMapping("/queryBusDetailInfo")
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

    @PostMapping("/doCollect")
    public AjaxResult doCollect(@RequestBody HashMap<String, Object> map) {
        List<WxCollect> wxCollects = addressService.queryCollectByOpId((int) map.get("stationId"), (String) map.get("stationName"), (String) map.get("cityName"), (String) map.get("opId"));
        return wxCollects.size() == 0 ?
                AjaxResult.success(addressService.
                        insertCollect((int) map.get("stationId"),
                                (String) map.get("stationName"), (String) map.get("cityName"), (String) map.get("opId"), (double) map.get("xPoint"), (double) map.get("yPoint"))) :
                AjaxResult.success("exist");
    }

    @PostMapping("/queryMyCollect")
    public AjaxResult queryMyCollect(@RequestBody HashMap<String, Object> map) {
        List<String> cityNames = addressService.queryCollectCityName((String) map.get("opId"));
        List<CollectVo> list = new ArrayList<>();
        for (String cityName : cityNames) {
            List<WxCollect> collects = addressService.queryCollectByOpIdAndCity((String) map.get("opId"), cityName);
            CollectVo collectVo = new CollectVo();
            collectVo.setText(cityName);
            List<Children> list1 = new ArrayList();
            for (WxCollect collect : collects) {
                Children children = new Children();
                children.setText(collect.getStationName()).setId(collect.getStationId()).setXPoint(collect.getXPoint()).setYPoint(collect.getYPoint()).setCityName(collect.getCityName());
                list1.add(children);
                collectVo.setChildren(list1);
            }
            list.add(collectVo);
        }
        return AjaxResult.success(list);
    }

    @PostMapping("/queryHistory")
    public AjaxResult queryHistory(@RequestBody HashMap<String, Object> map) {
        System.err.println(map.get("opId"));
        List<String> list = new ArrayList<>();
        list.add("aaa");


        return AjaxResult.success(list);
    }

    @RequestMapping("/test")
    public AjaxResult test(){
        return AjaxResult.success("132a");
    }

}
