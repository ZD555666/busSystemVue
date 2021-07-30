package com.ruoyi.wx.wxBus.mapper;

import com.ruoyi.wx.wxBus.domain.Complaint;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/29 15:49
 * @desc:
 */
@Mapper
public interface ComplaintMapper {

    //添加投诉信息
    Integer insertComplaint(Complaint complaint);
}
