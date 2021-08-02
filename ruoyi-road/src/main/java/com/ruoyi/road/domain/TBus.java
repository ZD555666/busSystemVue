package com.ruoyi.road.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公交车信息对象 t_businfo
 *
 * @author ruoyi
 * @date 2021-08-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TBus {
    /**
     * 公交车id主键
     */
    private Integer busId;

    /**
     * 公交车线路
     */
    private String busNo;

    /**
     * 车牌号
     */
    private String licensePlate;

    /**
     * 公交车状态参数
     */
    private Integer busParam;

    /**
     * 公交线路id
     */
    private Integer roadId;

}
