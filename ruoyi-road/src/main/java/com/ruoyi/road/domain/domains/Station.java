package com.ruoyi.road.domain.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: William
 * @date: 2021/8/9 11:52
 * @desc:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    private String xPoint;
    private String yPoint;
    private String stationName;
    private String stationId;
}
