package com.ruoyi.wx.wxuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/8/4 15:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectVo {
    private String text;
    private List<Children> children;
}
