package com.ruoyi.wx.wxBus.service;

import com.ruoyi.wx.wxBus.domain.Complaint;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/29 15:51
 * @desc:
 */
public interface ComplaintService {

    //添加投诉信息
    Integer addComplaint(Complaint complaint);
}
