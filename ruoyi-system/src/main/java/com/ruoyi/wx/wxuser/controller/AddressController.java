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
        int direction = (int) map.get("direction");
        List<WxBusRealRun> busNos = addressService.queryBusNoById(stationId, (String) map.get("cityName"), direction);
        List<StationRoadVo> list = new ArrayList<>();
        for (WxBusRealRun vo : busNos) {
            List<StationRoadVo> listVo = addressService.queryBusTo(vo.getBusNo(), (String) map.get("cityName"), direction);
            for (StationRoadVo stationRoadVo : listVo) {
                StationRoadVo roadVo = new StationRoadVo();
                if (direction == 0) {
                    if (stationRoadVo.getTravelSort() == listVo.size()) {
                        list.add(roadVo.setTravelSort(stationRoadVo.getTravelSort())
                                .setStationName(stationRoadVo.getStationName()).setEndStation(stationRoadVo.getStationName())
                                .setBusNo(vo.getBusNo()).setLicensePlate(vo.getLicensePlate()).setXPoint(stationRoadVo.getXPoint()).setYPoint(stationRoadVo.getYPoint()));
                    }
                } else if (direction == 1) {
                    if (stationRoadVo.getTravelSort() == listVo.size()) {
                        list.add(roadVo.setTravelSort(stationRoadVo.getTravelSort())
                                .setStationName(stationRoadVo.getStationName()).setEndStation(stationRoadVo.getStationName())
                                .setBusNo(vo.getBusNo()).setLicensePlate(vo.getLicensePlate()).setXPoint(stationRoadVo.getXPoint()).setYPoint(stationRoadVo.getYPoint()));
                    }
                }

            }
        }
        System.err.println(list);
        return AjaxResult.success(list);
    }

    @PostMapping("/queryBusDetailInfo")
    public AjaxResult queryBusDetailInfo(@RequestBody HashMap<String, Object> map) {
        int direction = (int) map.get("direction");
        String busString = JSONObject.toJSONString(map.get("busDetailInfo"));
        String clickStation = (String) map.get("clickStation");
        Map<String, Double> clickStationXy = addressService.queryClickStationXy(clickStation, (String) map.get("cityName"));
        System.out.println(clickStationXy + "================>>>>>>>>>>>>>");
        List<StationRoadVo> busDetailInfo = JSONObject.parseArray(busString, StationRoadVo.class);
        List<StationRoadVo> list = new ArrayList<>();
        for (StationRoadVo stationRoadVo : busDetailInfo) {
            StationRoadVo realRun = addressService.queryDistanceAndSpeed(stationRoadVo.getLicensePlate(), (String) map.get("cityName"), direction);
            double distance = NearbyUtil.getDistance(realRun.getRealXPoint(), realRun.getRealYPoint(), clickStationXy.get("xPoint"), clickStationXy.get("yPoint"));
            realRun.setDistance(distance);
            realRun.setDistanceTime(((distance / 1000.00) / realRun.getSpeed() > 35 ? 30 : realRun.getSpeed() * 60));
            realRun.setStationName(stationRoadVo.getStationName()).setEndStation(stationRoadVo.getEndStation()).setBusNo(stationRoadVo.getBusNo());
            int stationOne = addressService.querySurplusStation(clickStation, stationRoadVo.getBusNo(), (String) map.get("cityName"), direction);
            int stationTwo = addressService.querySurplusStation(realRun.getNowStation(), stationRoadVo.getBusNo(), (String) map.get("cityName"), direction);
            int i = stationOne - stationTwo;
            realRun.setSurplusStation(i);
            if (i >= 0) list.add(realRun);
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
        List<WxHistory> wxHistories = addressService.queryHistoryByOpId((String) map.get("opId"), (String) map.get("cityName"));
        System.err.println(wxHistories);

        return AjaxResult.success(wxHistories);
    }

    @PostMapping("/putHistory")
    public AjaxResult putHistory(@RequestBody HashMap<String, Object> map) {
        int i = addressService.insertHistory(new WxHistory().setOpenId((String) map.get("opId")).setEndStation((String) map.get("endStation")).
                setBusNo((String) map.get("busNo")).setCityName((String) map.get("cityName")));
        return AjaxResult.success(i);
    }

    @PostMapping("/putHistory1")
    public AjaxResult putHistory1(@RequestBody HashMap<String, Object> map) {
        int i = addressService.insertHistory1(new WxHistory().setOpenId((String) map.get("opId")).setStationId((int) map.get("stationId"))
                .setStationName((String) map.get("stationName")).setXPoint(Double.valueOf((String) map.get("xPoint"))).setYPoint(Double.valueOf((String) map.get("yPoint")))
                .setCityName((String) map.get("cityName")));
        return AjaxResult.success(i);
    }

    @PostMapping("/delHistory")
    public AjaxResult delHistory(@RequestBody HashMap<String, Object> map) {
        return AjaxResult.success(addressService.delAllHistory((String) map.get("opId"), (String) map.get("cityName")));
    }

    @PostMapping("/queryRoadMap")
    public AjaxResult queryRoadMap(@RequestBody HashMap<String, Object> map) {
        List<BaiDuMarkers> baiDuMarkers = addressService.queryRoadMap((String) map.get("busNo"), (int) map.get("direction"));
        for (BaiDuMarkers baiDuMarker : baiDuMarkers) {
            if (baiDuMarker.getLongitude() == (double) map.get("xPoint") && baiDuMarker.getLatitude() == (double) map.get("yPoint")) {
                baiDuMarker.setIconPath("../../images/marker_origen.png");
            }
        }
        return AjaxResult.success(baiDuMarkers);
    }


}
