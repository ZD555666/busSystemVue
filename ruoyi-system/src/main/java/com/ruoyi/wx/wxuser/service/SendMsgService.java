package com.ruoyi.wx.wxuser.service;

public interface SendMsgService {

    String sendPhoneMsg(String phone,String templateCode, String code);

}
