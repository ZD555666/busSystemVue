package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.WxRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 10:50
 * @desc:
 */
@Mapper
public interface WxRecordMapper {

    int insertRecord(WxRecord record);


}
