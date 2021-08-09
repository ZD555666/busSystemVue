package com.ruoyi.road.domain.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @author: William
 * @date: 2021/8/9 11:55
 * @desc:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRoadInfo {
    private String cityId;
    private String roadNo;
    private String cost;
    private String time;
//    private List<Station> stationPositive;
//    private List<Station> stationReverse;
}
