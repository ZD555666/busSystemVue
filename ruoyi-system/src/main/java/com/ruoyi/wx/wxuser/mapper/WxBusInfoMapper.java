package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.WxBusInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/10 20:38
 */
@Mapper
public interface WxBusInfoMapper {

    List<WxBusInfo> queryBus(@Param("busNo") String busNo, @Param("busParam1") int busParam1, @Param("busParam2") int busParam2);

    int insertRealRunBus(WxBusInfo busInfo);

    int updBusState(@Param("licensePlate") String licensePlate, @Param("busParam") int busParam);

}
