package com.ruoyi.wx.wxuser.service;

import com.ruoyi.wx.wxuser.domain.WxRecord;

import java.util.List;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 10:51
 * @desc:
 */
public interface WxRecordService {

    int insertRecord(WxRecord record);

    List<WxRecord> queryIncomeRecordByOpId(String opId, int isAdd);

}
