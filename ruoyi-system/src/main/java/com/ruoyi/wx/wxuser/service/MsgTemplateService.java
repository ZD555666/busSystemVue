package com.ruoyi.wx.wxuser.service;


import com.ruoyi.wx.wxuser.domain.MsgTemplate;

public interface MsgTemplateService {

    MsgTemplate queryAllTempByCode(String templateCode);

}
