package com.ruoyi.wx.wxuser.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/7/24 15:25
 */
@RestController
@RequestMapping("/wx")
public class UserInfoController {

    @RequestMapping("/getOpenId")
    public AjaxResult getOpenId(@RequestParam String code){

        System.out.println(code);
        return AjaxResult.success();
    }


}
