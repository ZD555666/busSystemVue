package com.ruoyi.wx.wxuser.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/27 13:46
 * @desc:
 */
@Mapper
public interface WxMoneyMapper {

    int insertMoneyByOpId(String opId,int money);

    int queryMoneyByOpId(String opId);

    int updMoneyByOpId(@Param("openId") String opId, @Param("money") int money);


}
