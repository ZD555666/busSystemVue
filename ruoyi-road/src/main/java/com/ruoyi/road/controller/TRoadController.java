package com.ruoyi.road.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.road.domain.TCity;
import com.ruoyi.road.domain.TRoad;
import com.ruoyi.road.domain.domains.AddRoadInfo;
import com.ruoyi.road.domain.domains.RoadInfo;
import com.ruoyi.road.domain.domains.Station;
import com.ruoyi.road.service.ITCityService;
import com.ruoyi.road.service.impl.TCityServiceImpl;
import com.ruoyi.road.tool.GetTimeByRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.road.service.ITRoadService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 线路配置Controller
 * 
 * @author ruoyi
 * @date 2021-08-01
 */
@RestController
@RequestMapping("/road/roads")
public class TRoadController extends BaseController
{
    @Autowired
    private ITRoadService tRoadService;
    @Autowired
    private ITCityService tCityService;
//  线路表格
    @GetMapping("/list")
    public TableDataInfo list(RoadInfo roadInfo){
        startPage();
        List<RoadInfo> roadInfoList = tRoadService.selectAllRoad(roadInfo);
        return getDataTable(roadInfoList);
    }
//  城市下拉框
    @GetMapping("/cityList")
    public List<TCity> cityList() {
        TCity city=new TCity();
        city.setCityname("");
        List<TCity> cityList=tCityService.selectTCityList(city);
        return cityList;
    }
//添加线路
    @GetMapping("/addRoads")
    public String addRoads(AddRoadInfo addRoadInfo, @RequestParam("stationPositive") String stationPositive, @RequestParam("stationReverse") String stationReverse, @RequestParam("actionType") int actionType){
        String msg="";
        if (actionType == 0) {
            //新增
            msg="新增成功";
        } else if (actionType == 1) {
            //更新
            TRoad road = TRoad.builder().busNo(addRoadInfo.getRoadNo()).cityId(Integer.parseInt(addRoadInfo.getCityId())).build();
            int flag = tRoadService.deleteRoad(road);
            msg = "修改成功";
        }
        Gson gson=new Gson();
//        System.out.println(addRoadInfo);
        List<Station> positiveList= gson.fromJson(stationPositive, new TypeToken<List<Station>>() {}.getType());
//        System.out.println(positiveList);
        List<Station> reverseList= gson.fromJson(stationReverse, new TypeToken<List<Station>>() {}.getType());
//        System.out.println(reverseList);
        List<TRoad> roadList=new ArrayList<TRoad>();
        for (Station s : positiveList) {
            TRoad road=TRoad.builder().stationId(Integer.parseInt(s.getStationId())).busNo(addRoadInfo.getRoadNo()).cost(Integer.parseInt(addRoadInfo.getCost())).
                    travelTime(addRoadInfo.getTime()).cityId(Integer.parseInt(addRoadInfo.getCityId())).direction(0).travelSort(positiveList.indexOf(s)+1).build();
            roadList.add(road);
        }
//        System.out.println(roadList);
        for (Station s : reverseList) {
            TRoad road = TRoad.builder().stationId(Integer.parseInt(s.getStationId())).busNo(addRoadInfo.getRoadNo()).cost(Integer.parseInt(addRoadInfo.getCost())).
                    travelTime(addRoadInfo.getTime()).cityId(Integer.parseInt(addRoadInfo.getCityId())).direction(1).travelSort(reverseList.indexOf(s) + 1).build();
            roadList.add(road);
        }
//        System.out.println(roadList);
        int flag=tRoadService.insertRoad(roadList);
        return msg;
    }
    //删除线路
    @GetMapping("/deleteRoad")
    public String deleteRoads(TRoad road){
        int flag=tRoadService.deleteRoad(road);
        return "删除成功";
    }
//    //更新路线
//    @GetMapping("/updateRoad")
//    public String updateRoad(){
//
//    }
}
