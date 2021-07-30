package com.ruoyi.wx.wxBus.service.impl;

import com.ruoyi.wx.wxBus.domain.Complaint;
import com.ruoyi.wx.wxBus.mapper.ComplaintMapper;
import com.ruoyi.wx.wxBus.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/29 15:51
 * @desc:
 */

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintMapper complaintMapper;

    @Override
    public Integer addComplaint(Complaint complaint) {
        complaintMapper.insertComplaint(complaint);
        return null;
    }
}
