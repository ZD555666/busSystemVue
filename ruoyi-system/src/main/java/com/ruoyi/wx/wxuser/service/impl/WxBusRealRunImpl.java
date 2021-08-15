package com.ruoyi.wx.wxuser.service.impl;

import com.ruoyi.wx.wxuser.domain.WxBusRealRun;
import com.ruoyi.wx.wxuser.mapper.WxBusRealRunMapper;
import com.ruoyi.wx.wxuser.service.WxBusRealRunService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/11 23:10
 */
@Service
public class WxBusRealRunImpl implements WxBusRealRunService {

    @Autowired
    private WxBusRealRunMapper realRunMapper;

    @Override
    public List<WxBusRealRun> queryAllRunBus() {
        return realRunMapper.queryAllRunBus();
    }

    @Override
    public WxBusRealRun queryNowStationXy(String nowStation) {
        return realRunMapper.queryNowStationXy(nowStation);
    }

    @Override
    public WxBusRealRun queryNextStationXy(String nextStation) {
        return realRunMapper.queryNextStationXy(nextStation);
    }

    @Override
    public int queryTravelSort(String stationName, int direction, String busNo, String licensePlate) {
        return realRunMapper.queryTravelSort(stationName, direction, busNo, licensePlate);
    }

    @Override
    public List<WxBusRealRun> queryNextInfo(String busNo, int direction, int travelSort1, int travelSort2) {
        return realRunMapper.queryNextInfo(busNo, direction, travelSort1, travelSort2);
    }

    @Override
    public int queryHasNext(String busNo, int direction, int cityId, int travelSort) {
        return realRunMapper.queryHasNext(busNo, direction, cityId, travelSort);
    }

    @Override
    public int updateRealRun(WxBusRealRun realRun) {
        return realRunMapper.updateRealRun(realRun);
    }

    @Override
    public int delEndBus(String licensePlate) {
        return realRunMapper.delEndBus(licensePlate);
    }

    @Override
    public int updateBusInfoState(String licensePlate, int busParam) {
        return realRunMapper.updateBusInfoState(licensePlate, busParam);
    }

    @Override
    public WxBusRealRun queryNewRunsByPlate(String licensePlate) {
        return realRunMapper.queryNewRunsByPlate(licensePlate);
    }

    @Override
    public int delAllRealRun() {
        return realRunMapper.delAllRealRun();
    }
}
