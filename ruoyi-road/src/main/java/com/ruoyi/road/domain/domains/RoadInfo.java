package com.ruoyi.road.domain.domains;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.road.domain.TBus;
import com.ruoyi.road.domain.TSchedule;
import com.ruoyi.road.domain.TStationinfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @author: William
 * @date: 2021/8/2 18:47
 * @desc:线路配置的信息   时间表、路线、路线对应的车牌
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadInfo extends BaseEntity {
    private String cityName;
    private String busNo;
    private String travelTime;
    private int cost;
    private int cityId;
    private List<RoadStationInfo> rsList;
    private List<RoadStationInfo> rsReList;
    private List<TSchedule> scheduleList;
    private List<TBus> busList;
    private int rsListSize;
    private int rsReListSize;
    private int scheduleListSize;
    private int busListSize;
}
