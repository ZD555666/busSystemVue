package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.WxBusRealRun;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/11 23:10
 */
public interface WxBusRealRunService {

    List<WxBusRealRun> queryAllRunBus();

    WxBusRealRun queryNowStationXy(String nowStation);

    WxBusRealRun queryNextStationXy(String nextStation);

    int queryTravelSort(String stationName, int direction,String busNo, String licensePlate);

    List<WxBusRealRun> queryNextInfo(String busNo, int direction, int travelSort1, int travelSort2);

    int queryHasNext(String busNo, int direction, int cityId, int travelSort);

    int updateRealRun(WxBusRealRun realRun);

    int delEndBus(String licensePlate);

    int updateBusInfoState(String licensePlate, int busParam);

    WxBusRealRun queryNewRunsByPlate(String licensePlate);

    int delAllRealRun();


}
