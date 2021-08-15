package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.WxBusRealRun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/11 23:09
 */
@Mapper
public interface WxBusRealRunMapper {

    List<WxBusRealRun> queryAllRunBus();

    WxBusRealRun queryNowStationXy(String nowStation);

    WxBusRealRun queryNextStationXy(String nextStation);

    int queryTravelSort(@Param("stationName") String stationName, @Param("direction") int direction, @Param("busNo") String busNo, @Param("licensePlate") String licensePlate);

    List<WxBusRealRun> queryNextInfo(@Param("busNo") String busNo, @Param("direction") int direction, @Param("travelSort1") int travelSort1, @Param("travelSort2") int travelSort2);

    int queryHasNext(@Param("busNo") String busNo, @Param("direction") int direction, @Param("cityId") int cityId, @Param("travelSort") int travelSort);

    int updateRealRun(WxBusRealRun realRun);

    int delEndBus(String licensePlate);

    int updateBusInfoState(@Param("licensePlate") String licensePlate, @Param("busParam") int busParam);

    WxBusRealRun queryNewRunsByPlate(String licensePlate);

    int delAllRealRun();

}
