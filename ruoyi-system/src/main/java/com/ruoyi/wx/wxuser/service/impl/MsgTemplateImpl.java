package com.ruoyi.wx.wxuser.service.impl;


import com.ruoyi.wx.wxuser.domain.MsgTemplate;
import com.ruoyi.wx.wxuser.mapper.MsgTemplateMapper;
import com.ruoyi.wx.wxuser.service.MsgTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgTemplateImpl implements MsgTemplateService {

    @Autowired
    private MsgTemplateMapper msgTemplateMapper;

    @Override
    public MsgTemplate queryAllTempByCode(String templateCode) {
        return msgTemplateMapper.queryAllTempByCode(templateCode);
    }

}
