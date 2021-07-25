package com.ruoyi.wx.wxuser.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;

import com.ruoyi.common.utils.SendMsgUtil;
import com.ruoyi.wx.wxuser.domain.MsgTemplate;
import com.ruoyi.wx.wxuser.service.MsgTemplateService;
import com.ruoyi.wx.wxuser.service.SendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SendMsgServiceImpl implements SendMsgService {

    @Autowired
    @Qualifier("msgTemplateImpl")
    private MsgTemplateService msgTemplateService;

    @Override
    public String sendPhoneMsg(String phone, String templateCode, String code) {
        MsgTemplate msgTemplate = msgTemplateService.queryAllTempByCode(templateCode);
        Client client = SendMsgUtil.createClient(msgTemplate.getKeyID(), msgTemplate.getKeySecret());
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(msgTemplate.getSignName())
                .setTemplateCode(templateCode)
                .setTemplateParam("{'code':'" + code + "'}");
        try {
            return client.sendSms(sendSmsRequest).getBody().getCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "noOK";
    }
}
