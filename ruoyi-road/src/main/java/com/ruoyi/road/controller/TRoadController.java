package com.ruoyi.road.controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.HttpClientUtil;
import com.ruoyi.road.domain.TCity;
import com.ruoyi.road.domain.TRoad;
import com.ruoyi.road.domain.domains.AddRoadInfo;
import com.ruoyi.road.domain.domains.RoadInfo;
import com.ruoyi.road.domain.domains.Station;
import com.ruoyi.road.service.ITCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.road.service.ITRoadService;

import java.util.ArrayList;
import java.util.HashMap;
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

    @GetMapping("/getResource")
//  百度api获取行车距离和时间
    public AjaxResult getResource(@RequestParam("stationSort") String stationSort, @RequestParam("returnSort") String returnSort){
        System.out.println("获取百度api距离时间");
        Gson gson=new Gson();
        List<Station> stationSort2 = gson.fromJson(stationSort, new TypeToken<List<Station>>() {}.getType());
        System.out.println("启程集合"+stationSort2);
        List<Station> returnSort2 = gson.fromJson(returnSort, new TypeToken<List<Station>>() {}.getType());
        System.out.println("返程集合" +returnSort2);
        if (stationSort2.size()>=2){
            for (int i = 1; i < stationSort2.size(); i++) {
                String resource = HttpClientUtil.doGet("https://api.map.baidu.com/routematrix/v2/driving?output=json&ak=1wwimYfoUw2bdW4APuLfFalfPQpiPm9t"
                        +"&tactics=11&origins="
                        + stationSort2.get(i).getYPoint() + ","+ stationSort2.get(i).getXPoint()
                        + "&destinations="
                        + stationSort2.get(i-1).getYPoint() + "," + stationSort2.get(i-1).getXPoint());
                JsonObject jsonObject = new JsonParser().parse(resource).getAsJsonObject();;
                System.out.println("启程集合转换为json"+jsonObject);
                System.out.println("距离"+
                                jsonObject.get("result").getAsJsonArray().
                                get(0).getAsJsonObject().
                                get("distance").getAsJsonObject().
                                get("text").getAsString());
                System.out.println("时间" +
                                jsonObject.get("result").getAsJsonArray().
                                get(0).getAsJsonObject().
                                get("duration").getAsJsonObject().
                                get("text").getAsString());
                stationSort2.get(i).setDistance(
                        jsonObject.get("result").getAsJsonArray().
                        get(0).getAsJsonObject().
                        get("distance").getAsJsonObject().
                        get("text").getAsString());
                stationSort2.get(i).setTime(
                        jsonObject.get("result").getAsJsonArray().
                        get(0).getAsJsonObject().
                        get("duration").getAsJsonObject().
                        get("text").getAsString());
            }
        }
        if(returnSort2.size()>=2){
            for (int i = 1; i < returnSort2.size(); i++) {
                String resource = HttpClientUtil.doGet("https://api.map.baidu.com/routematrix/v2/driving?output=json&ak=1wwimYfoUw2bdW4APuLfFalfPQpiPm9t"
                        + "&tactics=11&origins="
                        + returnSort2.get(i).getYPoint() + "," + returnSort2.get(i).getXPoint()
                        + "&destinations="
                        + returnSort2.get(i - 1).getYPoint() + "," + returnSort2.get(i - 1).getXPoint());
                JsonObject jsonObject = new JsonParser().parse(resource).getAsJsonObject();
                ;
                System.out.println("返程集合转换为json" + jsonObject);
                System.out.println("返程距离" +
                        jsonObject.get("result").getAsJsonArray().
                                get(0).getAsJsonObject().
                                get("distance").getAsJsonObject().
                                get("text").getAsString());
                System.out.println("返程时间" +
                        jsonObject.get("result").getAsJsonArray().
                                get(0).getAsJsonObject().
                                get("duration").getAsJsonObject().
                                get("text").getAsString());
                returnSort2.get(i).setDistance(jsonObject.get("result").getAsJsonArray().
                        get(0).getAsJsonObject().
                        get("distance").getAsJsonObject().
                        get("text").getAsString());
                returnSort2.get(i).setTime(jsonObject.get("result").getAsJsonArray().
                        get(0).getAsJsonObject().
                        get("duration").getAsJsonObject().
                        get("text").getAsString());
            }
        }
        Map<String, List<Station>> stationSort3=new HashMap<>();
        stationSort3.put("positiveList", stationSort2);
        stationSort3.put("reverseList", returnSort2);
        return AjaxResult.success(stationSort3);
    }
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
