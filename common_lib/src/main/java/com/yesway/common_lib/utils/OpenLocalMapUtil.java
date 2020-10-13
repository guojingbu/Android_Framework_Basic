package com.yesway.common_lib.utils;


import java.io.File;

public class OpenLocalMapUtil {
    /**
     * 判断map是否安装
     */
    public static boolean isGoogleMapInstalled() {
        return isInstallPackage("com.google.android.apps.maps");
    }

    public static boolean isGdMapInstalled() {
        return isInstallPackage("com.autonavi.minimap");
    }

    public static boolean isBaiduMapInstalled() {
        return isInstallPackage("com.baidu.BaiduMap");
    }

    private static boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 获取打开google地图应用uri
     */
    public static String getGoogleMapUri(String appName, String slat, String slon, String sname, String dlat, String dlon, String dname) {

        String uri = "http://maps.google.com/maps?f=d&source=s_d" + "&daddr=%5$s,%6$s&hl=zh&t=m&dirflg=d";
        return String.format(uri, appName, slat, slon, sname, dlat, dlon, dname);

    }
    /**
     * 获取打开google地图应用uri
     */
    public static String getGoogleMapUri(String appName) {

        //String uri = "http://maps.google.com/maps?f=d&source=s_d" + "&saddr=&daddr=&directionsmode=driving&t=m&dirflg=d";
        String uri = "https://www.google.com/maps/dir/?api=1";
        return String.format(uri, appName, "", "", "", "", "", "");

    }

    /**
     * 获取打开百度地图应用uri [http://lbsyun.baidu.com/index.php?title=uri/api/android]
     */
    public static String getBaiduMapUri(String originLat, String originLon, String originName, String desLat, String desLon, String destination, String region, String src) {
        String uri = "intent://map/direction?origin=latlng:%1$s,%2$s|name:%3$s" +
                "&destination=latlng:%4$s,%5$s|name:%6$s&mode=driving&region=%7$s&src=%8$s#Intent;" +
                "scheme=bdapp;package=com.baidu.BaiduMap;end";

        return String.format(uri, originLat, originLon, originName, desLat, desLon, destination, region, src);
    }

    /**
     * 获取打开高德地图应用uri
     */
    public static String getGdMapUri(String appName, String slat, String slon, String sname, String dlat, String dlon, String dname) {
        String uri = "androidamap://route?sourceApplication=%1$s&slat=%2$s&slon=%3$s&sname=%4$s&dlat=%5$s&dlon=%6$s&dname=%7$s&dev=0&m=0&t=2";
        return String.format(uri, appName, slat, slon, sname, dlat, dlon, dname);
    }

    /**
     * 百度地图定位经纬度转高德经纬度
     */
    public static double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }

    /**
     * 高德地图定位经纬度转百度经纬度
     */
    public static double[] gaoDeToBaidu(double gd_lon, double gd_lat) {
        double[] bd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gd_lon, y = gd_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        bd_lat_lon[0] = z * Math.cos(theta) + 0.0065;
        bd_lat_lon[1] = z * Math.sin(theta) + 0.006;
        return bd_lat_lon;
    }
}
