package com.ruoyi.wx.wxBus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: ZD
 * @date: 2021/7/29 15:46
 * @desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Complaint implements Serializable {
    private String phoneNumber;
    private String problem;
    private String imagePath;
}
