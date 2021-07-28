package com.ruoyi.wx.wxuser.service.impl;

import com.ruoyi.wx.wxuser.domain.WxRecord;
import com.ruoyi.wx.wxuser.mapper.WxRecordMapper;
import com.ruoyi.wx.wxuser.service.WxRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 10:51
 * @desc:
 */
@Service
public class WxRecordImpl implements WxRecordService {

    @Autowired
    private WxRecordMapper wxRecordMapper;

    @Override
    public int insertRecord(WxRecord record) {
        return wxRecordMapper.insertRecord(record);
    }
}
