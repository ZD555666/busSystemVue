package com.ruoyi.wx.wxuser.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @author: gjw
 * @date: 2021/7/28 10:27
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WxRecord implements Serializable {

    private int rid;
    private String openId;
    private Integer isAdd;
    private int record;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date recordTime;
    private int restMoney;


}
