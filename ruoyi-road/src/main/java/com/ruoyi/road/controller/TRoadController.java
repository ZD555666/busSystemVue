package com.ruoyi.road.controller;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.road.domain.TBus;
import com.ruoyi.road.domain.domains.RoadInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.road.service.ITRoadService;

import java.util.List;

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

    @GetMapping("/list")
    public TableDataInfo list(RoadInfo roadInfo){
        startPage();
        List<RoadInfo> roadInfoList = tRoadService.selectAllRoad(roadInfo);
        return getDataTable(roadInfoList);
    }
}
