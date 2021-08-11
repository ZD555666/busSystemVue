package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.WxAliPay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 16:32
 * @desc:
 */
@Mapper
public interface AliPayMapper {

    int insertPayInfo(WxAliPay aliPay);

    int updPayStateByOpId(@Param("openId") String opId, @Param("out_trade_no") String orderNo, @Param("payState") String payState);

    String queryPayState(@Param("out_trade_no") String out_trade_no,@Param("openId") String openId,@Param("total_amount") int total_amount);

    String queryOpenIdByOrderNo(String orderNo);

}
