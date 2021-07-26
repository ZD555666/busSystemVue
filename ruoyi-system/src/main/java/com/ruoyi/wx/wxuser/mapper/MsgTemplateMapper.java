package com.ruoyi.wx.wxuser.mapper;

import com.ruoyi.wx.wxuser.domain.MsgTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MsgTemplateMapper {

    MsgTemplate queryAllTempByCode(String templateCode);

}
