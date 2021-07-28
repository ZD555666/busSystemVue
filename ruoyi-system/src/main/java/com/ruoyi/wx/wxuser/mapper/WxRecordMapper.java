package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.WxRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 10:50
 * @desc:
 */
@Mapper
public interface WxRecordMapper {

    int insertRecord(WxRecord record);

    List<WxRecord> queryIncomeRecordByOpId(@Param("openId") String opId, @Param("isAdd") int isAdd);

}
