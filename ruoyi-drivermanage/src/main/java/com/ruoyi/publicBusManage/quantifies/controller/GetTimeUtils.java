package com.ruoyi.publicBusManage.quantifies.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @author: xixi
 * @date: 2021/8/12 16:43
 * @desc:before SET
 */
public class GetTimeUtils {
    public String getTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String dataTime = formatter.format(date);
        return dataTime;
    }
}
