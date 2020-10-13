package com.yesway.share_lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.yesway.share_lib.config.ShareConfig;
import com.yesway.share_lib.utils.UMimageUtil;

import java.io.File;


/**
 * Created by 95190 on 2018/4/20.
 */

public class ShareUtils {

    private static Context mContext;

    private static ShareUtils shareUtils = new ShareUtils();

    private ShareUtils() {

    }

    public static ShareUtils getInstance() {
        return shareUtils;
    }

    /**
     * 初始化
     */
    public static void init(ShareConfig shareConfig) {
        mContext = shareConfig.context.getApplicationContext();
        UMConfigure.init(shareConfig.context, shareConfig.mConfig.appKey
                , shareConfig.mConfig.channel, UMConfigure.DEVICE_TYPE_PHONE, shareConfig.mConfig.pushSecret);
        //        设置各个平台的appkey
        if (shareConfig.mWeiXin != null) {
            PlatformConfig.setWeixin(shareConfig.mWeiXin.appId, shareConfig.mWeiXin.signMd5);
        }
        if (shareConfig.mSinaWeibo != null) {
            PlatformConfig.setSinaWeibo(shareConfig.mSinaWeibo.appKey, shareConfig.mSinaWeibo.appSecret, shareConfig.mSinaWeibo.callbackAddress);
        }
        if (shareConfig.mQQZone != null) {
            PlatformConfig.setQQZone(shareConfig.mQQZone.s, shareConfig.mQQZone.s1);
        }
        if (shareConfig.isdebug != null) {
            UMConfigure.setLogEnabled(shareConfig.isdebug);
        }
    }

    /**
     * 纯文本分享
     *
     * @param activity
     * @param share_media
     * @param umShareListener
     */
    public void shareText(Activity activity, SHARE_MEDIA share_media, String content, UMShareListener umShareListener) {
        new ShareAction(activity).withText(content).setPlatform(share_media).setCallback(umShareListener).share();

    }

    /**
     * 带界面的纯文本分享
     *
     * @param activity        activity
     * @param share_medias    分享平台配置
     * @param content         文本内容
     * @param umShareListener
     */
    public void shareBoardText(final Activity activity, final String content, final UMShareListener umShareListener, SHARE_MEDIA... share_medias) {
        ShareAction shareAction = initShareAction(activity, share_medias);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        shareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                shareText(activity, share_media, content, umShareListener);
            }
        });
        shareAction.open(config);

    }


    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param share_media
     * @param imageurl
     * @param thumbImgId
     * @param umShareListener
     */
    public void shareImage(final Activity activity, final String content, final SHARE_MEDIA share_media, final String imageurl, final int thumbImgId, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, imageurl);
        UMImage thumbImage = UMimageUtil.getImage(activity, thumbImgId);
        image.setThumb(thumbImage);
        new ShareAction(activity)
                .withText(content)
                .withMedia(image)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param imageurl
     * @param share_media
     * @param type
     * @param umShareListener
     */
    public void shareImage(final Activity activity, final String content, final String imageurl, final SHARE_MEDIA share_media, final int type, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, imageurl);
        new ShareAction(activity)
                .withText(content)
                .withMedia(UMimageUtil.setCompression(image, type))
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param file
     * @param share_media
     * @param umShareListener
     */
    public void shareImage(final Activity activity, final String content, final File file, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, file);
        new ShareAction(activity)
                .withText(content)
                .withMedia(image)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param file
     * @param type
     * @param share_media
     */
    public void shareImage(final Activity activity, final String content, final File file, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, file);
        new ShareAction(activity)
                .withText(content)
                .withMedia(UMimageUtil.setCompression(image, type))
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param id
     * @param share_media
     * @param umShareListener
     */
    public void shareImage(final Activity activity, final String content, final int id, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, id);
        new ShareAction(activity)
                .withText(content)
                .withMedia(image)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param id
     * @param type
     * @param share_media
     * @param umShareListener
     */
    public void shareImage(final Activity activity, final String content, final int id, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, id);
        new ShareAction(activity)
                .withText(content)
                .withMedia(UMimageUtil.setCompression(image, type))
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param bitmap
     * @param share_media
     * @param umShareListener
     */
    public void shareImage(final Activity activity, final String content, final Bitmap bitmap, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, bitmap);
        new ShareAction(activity)
                .withText(content)
                .withMedia(image)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param bitmap
     * @param type
     * @param share_media
     * @param umShareListener
     */
    public void shareImage(final Activity activity, final String content, final Bitmap bitmap, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, bitmap);
        new ShareAction(activity)
                .withText(content)
                .withMedia(UMimageUtil.setCompression(image, type))
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param k
     * @param share_media
     * @param umShareListener
     */
    public void shareImage(final Activity activity, final String content, final byte[] k, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, k);
        new ShareAction(activity).withText(content)
                .withMedia(image)
                .setPlatform(share_media)
                .setCallback(umShareListener).share();
    }

    /**
     * 文本加图片分享
     *
     * @param activity
     * @param content
     * @param k
     * @param type
     * @param share_media
     * @param umShareListener
     */
    public void shareImage(final Activity activity, final String content, final byte[] k, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMImage image = UMimageUtil.getImage(activity, k);
        new ShareAction(activity).withText(content)
                .withMedia(UMimageUtil.setCompression(image, type))
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 带界面的文本+图片分享
     *
     * @param activity
     * @param content
     * @param imageurl
     * @param thumbImgId
     * @param umShareListener
     * @param share_medias
     */
    public void shareBoardImage(final Activity activity, final String content, final String imageurl, final int thumbImgId, final UMShareListener umShareListener, final SHARE_MEDIA... share_medias) {
        ShareAction shareAction = initShareAction(activity, share_medias);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        shareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                shareImage(activity, content, share_media, imageurl, thumbImgId, umShareListener);
            }
        });
        shareAction.open(config);
    }

    /**
     * 带界面的文本+本地图片分享
     *
     * @param activity
     * @param content
     * @param imgId
     * @param umShareListener
     * @param share_medias
     */
    public void shareBoardImage(final Activity activity, final String content, final int imgId, final UMShareListener umShareListener, final SHARE_MEDIA... share_medias) {
        ShareAction shareAction = initShareAction(activity, share_medias);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        shareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                shareImage(activity, content, imgId, share_media, umShareListener);
            }
        });
        shareAction.open(config);
    }


    /**
     * 多图分享
     *
     * @param activity    activity
     * @param content     输入内容
     * @param share_media 分享的平台
     * @param var1        图片可变参数
     */
    public void shareManyImages(final Activity activity, final String content, final SHARE_MEDIA share_media, final UMShareListener umShareListener, final UMImage... var1) {
        new ShareAction(activity).withMedias(var1)
                .withText(content)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 连接分享
     *
     * @param activity        activity
     * @param url             链接地址
     * @param title           标题
     * @param des             描述
     * @param umShareListener
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final String imageurl, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, imageurl);
        web.setThumb(thumb);  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media).setCallback(umShareListener).share();
    }

    /**
     * 连接分享
     *
     * @param activity        activity
     * @param url             链接地址
     * @param title           标题
     * @param des             描述
     * @param umShareListener
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final String imageurl, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, imageurl);
        web.setThumb(UMimageUtil.setCompression(thumb, type));  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 连接分享
     *
     * @param activity
     * @param url
     * @param title
     * @param des
     * @param file            本地文件图片
     * @param share_media
     * @param umShareListener
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final File file, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, file);
        web.setThumb(thumb);  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 连接分享
     *
     * @param activity        activity
     * @param url             链接地址
     * @param title           标题
     * @param des             描述
     * @param file
     * @param type
     * @param share_media
     * @param umShareListener
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final File file, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, file);
        web.setThumb(UMimageUtil.setCompression(thumb, type));  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 连接分享
     *
     * @param activity        activity
     * @param url             链接地址
     * @param title           标题
     * @param des             描述
     * @param id              本地图片id
     * @param share_media
     * @param umShareListener
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final int id, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, id);
        web.setThumb(thumb);  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * @param activity        activity
     * @param url             链接地址
     * @param title           标题
     * @param des             描述
     * @param id
     * @param type
     * @param share_media
     * @param umShareListener
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final int id, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, id);
        web.setThumb(UMimageUtil.setCompression(thumb, type));  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 连接分享
     *
     * @param activity        activity
     * @param url             链接地址
     * @param title           标题
     * @param des             描述
     * @param bitmap
     * @param share_media
     * @param umShareListener
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final Bitmap bitmap, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, bitmap);
        web.setThumb(thumb);  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 连接分享
     *
     * @param activity        activity
     * @param url             链接地址
     * @param title           标题
     * @param des             描述
     * @param bitmap
     * @param type
     * @param share_media
     * @param umShareListener
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final Bitmap bitmap, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, bitmap);
        web.setThumb(UMimageUtil.setCompression(thumb, type));  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 连接分享
     *
     * @param activity activity
     * @param url      链接地址
     * @param title    标题
     * @param des      描述
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final byte[] k, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, k);
        web.setThumb(thumb);  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }


    /**
     * 连接分享
     *
     * @param activity        activity
     * @param url             链接地址
     * @param title           标题
     * @param des             描述
     * @param k
     * @param type
     * @param share_media
     * @param umShareListener
     */
    public void shareWeb(final Activity activity, final String url, final String title, final String des, final byte[] k, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = UMimageUtil.getImage(activity, k);
        web.setThumb(UMimageUtil.setCompression(thumb, type));  //缩略图
        web.setDescription(des);//描述
        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 分享web链接+网络图片的
     *
     * @param activity        activity
     * @param url             链接地址
     * @param title           标题
     * @param des             描述
     * @param imageurl        图片地址
     * @param umShareListener
     * @param share_media     支持的分享平台 （默认显示微信与新浪）
     */
    public void showShareBoardWeb(final Activity activity, final String url, final String title, final String des, final String imageurl, final UMShareListener umShareListener, SHARE_MEDIA... share_media) {
        ShareAction shareAction = initShareAction(activity, share_media);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        shareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                shareWeb(activity, url, title, des, imageurl, share_media, umShareListener);
            }
        });
        shareAction.open(config);
    }

    /**
     * 分享web链接+本地图片的id
     *
     * @param activity    activity
     * @param url         链接地址
     * @param title       标题
     * @param des         描述
     * @param imgSourceId 本地图片id
     * @param share_media 支持的分享平台 （默认显示微信与新浪）
     */
    public void showShareBoardWeb(final Activity activity, final String url, final String title, final String des, final int imgSourceId, final UMShareListener umShareListener, SHARE_MEDIA... share_media) {
        ShareAction shareAction = initShareAction(activity, share_media);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        shareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                shareWeb(activity, url, title, des, imgSourceId, share_media, umShareListener);
            }
        });
        shareAction.open(config);
    }

    /**
     * 网络视屏分享
     *
     * @param activity        activity
     * @param videoUrl        视屏地址
     * @param title           视频的标题
     * @param des             视频的描述
     * @param content         输入内容
     * @param imageurl        网络图片url
     * @param share_media
     * @param umShareListener
     */

    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final String imageurl, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, imageurl);
        video.setThumb(thumb);//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络视屏分享
     *
     * @param activity        activity
     * @param videoUrl        视屏地址
     * @param title           视频的标题
     * @param des             视频的描述
     * @param content         输入内容
     * @param imageurl        图片url
     * @param type            图片压缩方式
     * @param share_media
     * @param umShareListener
     */
    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final String imageurl, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, imageurl);
        video.setThumb(UMimageUtil.setCompression(thumb, type));//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }


    /**
     * 网络视屏分享
     *
     * @param activity        activity
     * @param videoUrl        视屏地址
     * @param title           视频的标题
     * @param des             视频的描述
     * @param content         输入内容
     * @param file            本地视屏文件
     * @param share_media
     * @param umShareListener
     */
    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final File file, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, file);
        video.setThumb(thumb);//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络视屏分享
     *
     * @param activity        activity
     * @param videoUrl        视屏地址
     * @param title           视频的标题
     * @param des             视频的描述
     * @param content         输入内容
     * @param file            本地文件
     * @param type
     * @param share_media
     * @param umShareListener
     */
    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final File file, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, file);
        video.setThumb(UMimageUtil.setCompression(thumb, type));//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络视屏分享
     *
     * @param activity        activity
     * @param videoUrl        视屏地址
     * @param title           视频的标题
     * @param des             视频的描述
     * @param content         输入内容
     * @param id
     * @param share_media
     * @param umShareListener
     */
    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final int id, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, id);
        video.setThumb(thumb);//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络视屏分享
     *
     * @param activity        activity
     * @param videoUrl        视屏地址
     * @param title           视频的标题
     * @param des             视频的描述
     * @param content         输入内容
     * @param id              视屏缩略图id
     * @param type            压缩模式
     * @param share_media
     * @param umShareListener
     */
    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final int id, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, id);
        video.setThumb(UMimageUtil.setCompression(thumb, type));//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络视屏分享
     *
     * @param activity        activity
     * @param videoUrl        视屏地址
     * @param title           视频的标题
     * @param des             视频的描述
     * @param content         输入内容
     * @param bitmap
     * @param share_media
     * @param umShareListener
     */
    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final Bitmap bitmap, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, bitmap);
        video.setThumb(thumb);//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络视屏分享
     *
     * @param activity        activity
     * @param videoUrl        视屏地址
     * @param title           视频的标题
     * @param des             视频的描述
     * @param content         输入内容
     * @param bitmap
     * @param type
     * @param share_media
     * @param umShareListener
     */
    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final Bitmap bitmap, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, bitmap);
        video.setThumb(UMimageUtil.setCompression(thumb, type));//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络视屏分享
     *
     * @param activity        activity
     * @param videoUrl        视屏地址
     * @param title           视频的标题
     * @param des             视频的描述
     * @param content         输入内容
     * @param k
     * @param share_media
     * @param umShareListener
     */
    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final byte[] k, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, k);
        video.setThumb(thumb);//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络视屏分享
     *
     * @param activity activity
     * @param videoUrl 视屏地址
     * @param title    视频的标题
     * @param des      视频的描述
     * @param content  输入内容
     */
    public void shareVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final byte[] k, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videoUrl);
        video.setTitle(title);//视频的标题
        UMImage thumb = UMimageUtil.getImage(activity, k);
        video.setThumb(UMimageUtil.setCompression(thumb, type));//视频的缩略图
        video.setDescription(des);//视频的描述
        new ShareAction(activity)
                .withText(content)
                .withMedia(video)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 带分享界面的视频分享
     *
     * @param activity        activity
     * @param videoUrl        视频地址
     * @param title           视频头信息
     * @param des             视频描述信息
     * @param content         分享文字内容
     * @param imageurl        分享缩略图片
     * @param umShareListener
     * @param share_medias    分享平台设置
     */
    public void shareBoardVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final String imageurl, final UMShareListener umShareListener, SHARE_MEDIA... share_medias) {
        ShareAction shareAction = initShareAction(activity, share_medias);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        shareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                shareVideo(activity, videoUrl, title, des, content, imageurl, share_media, umShareListener);
            }
        });
        shareAction.open(config);
    }

    /**
     * 带分享界面的视频分享
     *
     * @param activity        activity
     * @param videoUrl        视频地址
     * @param title           视频头信息
     * @param des             视频描述信息
     * @param content         分享文字内容
     * @param thumbId         分享缩略图片id
     * @param umShareListener
     * @param share_medias    分享平台设置
     */
    public void shareBoardVideo(final Activity activity, final String videoUrl, final String title, final String des, final String content, final int thumbId, final UMShareListener umShareListener, SHARE_MEDIA... share_medias) {
        ShareAction shareAction = initShareAction(activity, share_medias);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        shareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                shareVideo(activity, videoUrl, title, des, content, thumbId, share_media, umShareListener);
            }
        });
        shareAction.open(config);
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final String imageurl, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, imageurl);
        music.setThumb(thumb);//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接

        new ShareAction(activity)
                .withMedia(music)
                .withText(content)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final String imageurl, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, imageurl);
        music.setThumb(UMimageUtil.setCompression(thumb, type));//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接

        new ShareAction(activity)
                .withMedia(music)
                .withText(content)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final File file, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, file);
        music.setThumb(thumb);//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接

        new ShareAction(activity)
                .withMedia(music)
                .withText(content)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final File file, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, file);
        music.setThumb(UMimageUtil.setCompression(thumb, type));//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接

        new ShareAction(activity)
                .withMedia(music)
                .withText(content)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final int id, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, id);
        music.setThumb(thumb);//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接

        new ShareAction(activity)
                .withMedia(music)
                .withText(content)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final int id, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, id);
        music.setThumb(UMimageUtil.setCompression(thumb, type));//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接

        new ShareAction(activity)
                .withMedia(music)
                .setPlatform(share_media)
                .withText(content)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final Bitmap bitmap, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, bitmap);
        music.setThumb(thumb);//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接

        new ShareAction(activity)
                .withMedia(music)
                .withText(content)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final Bitmap bitmap, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, bitmap);
        music.setThumb(UMimageUtil.setCompression(thumb, type));//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接

        new ShareAction(activity)
                .withMedia(music)
                .withText(content)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final byte[] k, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, k);
        music.setThumb(thumb);//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接
        new ShareAction(activity)
                .withMedia(music)
                .withText(content)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     */
    public void shareMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final byte[] k, final int type, final SHARE_MEDIA share_media, final UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle(title);//音乐的标题
        UMImage thumb = UMimageUtil.getImage(activity, k);
        music.setThumb(UMimageUtil.setCompression(thumb, type));//音乐的缩略图
        music.setDescription(des);//音乐的描述
        music.setmTargetUrl(tarurl);//音乐的跳转链接

        new ShareAction(activity)
                .withMedia(music)
                .setPlatform(share_media)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 带分享界面网络音乐分享
     *
     * @param activity        activity
     * @param musicurl        音乐播放地址地址
     * @param tarurl          音乐跳转播放地址
     * @param title           音乐的标题
     * @param des             音乐的描述
     * @param content         输入内容
     *                        特别说明：播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     * @param umShareListener
     * @param share_medias
     */
    public void shareBoardMusic(final Activity activity, final String musicurl, final String tarurl, final String title, final String des, final String content, final String imageurl, final UMShareListener umShareListener, SHARE_MEDIA... share_medias) {
        ShareAction shareAction = initShareAction(activity, share_medias);
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        shareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                shareMusic(activity, musicurl, tarurl, title, des, content, imageurl, share_media, umShareListener);
            }
        });
        shareAction.open(config);
    }

    /**
     * 带ui分享的基础ui配置
     *
     * @param activity
     * @param share_media
     * @return
     */
    private ShareAction initShareAction(Activity activity, SHARE_MEDIA... share_media) {
        ShareAction shareAction = new ShareAction(activity);
        if (share_media != null && share_media.length > 0) {
            shareAction.setDisplayList(share_media);
        } else {
            shareAction.setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.SINA);
        }
        return shareAction;
    }

    /**
     * 判断指定平台是否授权
     *
     * @param activity
     * @param share_media
     * @return
     */
    public boolean isAuth(Activity activity, SHARE_MEDIA share_media) {
        final boolean isauth = UMShareAPI.get(mContext).isAuthorize(activity, share_media);
        return isauth;
    }

    /**
     * 请求授权
     *
     * @param activity
     * @param share_media
     */
    public void doOauthVerify(Activity activity, SHARE_MEDIA share_media, UMAuthListener authListener) {
        if (isInstall(activity, share_media)) {
            UMShareAPI.get(mContext).getPlatformInfo(activity, share_media, authListener);
        }
    }

    /**
     * 判断设备是否安装指定应用
     *
     * @param activity
     * @param share_media
     * @return
     */
    public boolean isInstall(Activity activity, SHARE_MEDIA share_media) {
        return UMShareAPI.get(mContext).isInstall(activity, share_media);
    }

    /**
     * 删除auth授权
     *
     * @param activity
     * @param share_media
     */
    public void deleteOAuth(Activity activity, SHARE_MEDIA share_media, UMAuthListener authListener) {
        if (isAuth(activity, share_media)) {
            UMShareAPI.get(mContext).deleteOauth(activity, share_media, authListener);
        }
    }

    /**
     * 释放资源 防止内存泄露
     *
     * @param context
     */
    public void shareOnDestroy(Context context) {
        UMShareAPI.get(context).release();
    }

    /**
     * 在activity的onActivityResult 方法中必须调用这个方法
     *
     * @param context
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onShareActivityResult(Context context, int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }


}
