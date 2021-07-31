package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.WxStationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/30 16:50
 * @desc:
 */
@Mapper
public interface AddressMapper {

    List<WxStationInfo> queryNearStation(@Param("minLng") double minLng, @Param("maxLng") double maxLng, @Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("cityName") String cityName);


}
