package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.WxBusInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/10 20:38
 */
public interface WxBusInfoService {

    List<WxBusInfo> queryBus(String busNo,int busParam1,int busParam2);

    int insertRealRunBus(WxBusInfo busInfo);

    int updBusState(String licensePlate, int busParam);

    int updBusWhenOver(int busParam);


}
