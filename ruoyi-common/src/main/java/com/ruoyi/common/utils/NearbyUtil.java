package com.ruoyi.common.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gjw
 * @version 1.0
 * @date 2021/7/31 16:31
 */
public class NearbyUtil {

    public static Map findNear(double longitude, double latitude, double distance) {

        double minLat = 0;
        double maxLat = 0;
        double minLng = 0;
        double maxLng = 0;

        // 先计算查询点的经纬度范围
        double r = 6371;// 地球半径千米
        double dis = distance;// 距离(单位:千米)
        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r))
                / Math.cos(longitude * Math.PI / 180));
        dlng = dlng * 180 / Math.PI;// 角度转为弧度
        double dlat = dis / r;
        dlat = dlat * 180 / Math.PI;
        if (dlng < 0) {
            minLng = longitude + dlng;
            maxLng = longitude - dlng;
        } else {
            minLng = longitude - dlng;
            maxLng = longitude + dlng;
        }
        if (dlat < 0) {
            minLat = latitude + dlat;
            maxLat = latitude - dlat;
        } else {
            minLat = latitude - dlat;
            maxLat = latitude + dlat;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("minLat", minLat);//纬度底
        params.put("maxLat", maxLat);//纬度顶
        params.put("minLng", minLng);//经度底
        params.put("maxLng", maxLng);//经度顶
        return params;
    }

    public static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);
        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }



}
