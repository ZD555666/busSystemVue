package com.ruoyi.road.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @author: William
 * @date: 2021/8/4 19:21
 * @desc:
 */
@RestController
@RequestMapping("/tool")
public class ToolController {
    @GetMapping("/getTime")
    public void getTime(){

    }
}
