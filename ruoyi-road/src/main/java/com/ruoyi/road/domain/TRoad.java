package com.ruoyi.road.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 线路配置对象 t_road
 * 
 * @author zlw
 * @date 2021-08-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TRoad
{
    /** 线路id */
    private Integer roadId;

    /** 站点id */
    private Integer stationId;

    /** 公交车x路 */
    private String busNo;

    /** 花费 */
    private Integer cost;

    /** 单程时间 */
    private String travelTime;

    /** 行驶顺序 */
    private Integer travelSort;

    /** 城市id */
    private Integer cityId;

    /** 行驶方向0正向，1反 */
    private Integer direction;
}
