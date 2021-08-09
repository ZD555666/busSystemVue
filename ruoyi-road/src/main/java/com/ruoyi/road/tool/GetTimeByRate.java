package com.ruoyi.road.tool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @version 1.0
 * @author: William
 * @date: 2021/8/4 18:44
 * @desc:
 */
public class GetTimeByRate {
    public static List<String> countLocationPoints(String startTime, String endTime, int rate) throws Exception {//11:00 12:30
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(dateFormat.parse(startTime));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(dateFormat.parse(endTime));
        if (endCalendar.compareTo(startCalendar) < 0) {
            endCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        List<String> retList = new ArrayList<String>();
        retList.add(startTime);
        while (true) {
            startCalendar.add(Calendar.MINUTE, rate);
            if (startCalendar.compareTo(endCalendar) >= 0)
                break;
            retList.add(dateFormat.format(startCalendar.getTime()));
        }
//        retList.add(endTime);

        return retList;
    }
}
