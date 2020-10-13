package com.yesway.share_lib.utils;

import android.app.Activity;
import android.graphics.Bitmap;

import com.umeng.socialize.media.UMImage;

import java.io.File;

/**
 * Created by 95190 on 2018/4/20.
 */

public class UMimageUtil {

    public final static int SCALE = 0;
    public final static int QUALITY = 1;
    public final static int PNG = 2;

    /**
     * 获取网络图片
     */
    public static UMImage getImage(Activity activity, String imageurl) {
        UMImage image = new UMImage(activity, imageurl);//网络图片
        return image;
    }

    /**
     * 本地文件
     */
    public static UMImage getImage(Activity activity, File file) {
        UMImage image = new UMImage(activity, file);
        return image;
    }

    /**
     * 资源文件
     */
    public static UMImage getImage(Activity activity, int id) {
        UMImage image = new UMImage(activity, id);
        return image;
    }

    /**
     * bitmap文件
     */
    public static UMImage getImage(Activity activity, Bitmap bitmap) {
        UMImage image = new UMImage(activity, bitmap);
        return image;
    }

    /**
     * 字节流
     */
    public static UMImage getImage(Activity activity, byte[] k) {
        UMImage image = new UMImage(activity, k);//网络图片
        return image;
    }

    /**
     * 图片压缩设置
     */
    public static UMImage setCompression(UMImage image, int type) {
        switch (type) {
            case SCALE:
                image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
                break;
            case QUALITY:
                image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
                break;
            case PNG:
                image.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
                break;
        }

        return image;
    }
}
