package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.WxAliPay;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 16:32
 * @desc:
 */
@Mapper
public interface AliPayMapper {

    int insertPayInfo(WxAliPay aliPay);

}
