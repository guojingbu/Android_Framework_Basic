package com.yesway.statistics_lib;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * author : guojingbu
 * date   : 2020/9/24
 * desc   :
 */
public class StatisticsManager {
    /**
     * 初始化Umeng统计sdk
     * 如果使用了Umeng分享已经初始化过了就不需要重复初始化只需要调用{@link StatisticsManager}setPageCollectionMode(PageMode pageMode)方法
     *
     * @param context
     * @param appkey  manifest文件中配置了这里可以传空字符串
     */
    public final static void init(Context context, String appkey, PageMode pageMode) {
        //渠道自行在manifest配置init中渠道可以不传
        //UMConfigure.init(this, "5ab2xxxxxxxxxxxxx002f8", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        UMConfigure.init(context.getApplicationContext(), UMConfigure.DEVICE_TYPE_PHONE, appkey);
        //在Android 4.0以上设备中，推荐使用自定采集的模式：在activity中 (AUTO模式下SDK会自动调用MobclickAgent.onResume/MobclickAgent.onPause接口，用户无须手动调用这两个接口)
        // （MANUAL模式：手动模式适合4.0以下的设备）在activity中（需要用户手动调用MobclickAgent.onResume/MobclickAgent.onPause接口）
        setPageCollectionMode(pageMode);
    }

    /**
     * 设置统计模式
     *
     * @param pageMode
     */
    public final static void setPageCollectionMode(PageMode pageMode) {
        switch (pageMode) {
            case AUTO:
                MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
                break;
            case MANUAL:
                MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL);
                break;
            case LEGACY_AUTO:
                MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_AUTO);
                break;
            case LEGACY_MANUAL:
                MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_MANUAL);
                break;
        }
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }


    public static enum PageMode {
        /**
         * Android 4.0及以上版本支持Activity生命周期的自动监控(通过注册自定义callback函数)。
         * 在Android 4.0以上设备中，推荐使用系统自动监控机制进行页面及基础指标自动埋点
         * (AUTO模式下SDK会自动调用MobclickAgent.onResume/MobclickAgent.onPause接口，用户无须手动调用这两个接口)。
         */
        AUTO,
        /**
         * 如果需要统计 Android 4.0 以下版本设备统计数据，则必须选择手动模式(MANUAL)，
         * 对宿主App中所有Activity都手动调用MobclickAgent.onResume/MobclickAgent.onPause手动埋点。
         * MANUAL模式下需要在Activity的onResume/onPause函数中手动调用MobclickAgent.onResume/MobclickAgent.onPause函数，
         * 保证正确统计应用使用时长、启动、活跃等基础指标。
         */
        MANUAL,
        /**
         * SDK默认情况下使用此模式，对于多数老版本【友盟+】统计SDK的开发者，
         * 如果在您的App中之前没有使用MobclickAgent.onPageStart/MobclickAgent.onPageEnd这两个接口对非Activity页面(如:Fragment)进行埋点统计。
         * 则请选择此模式，这样您的App埋点代码不需要做任何修改，SDK即可正常工作。
         * (需确保您应用中所有Activity中都已经手动调用MobclickAgent.onResume/MobclickAgent.onPause接口)
         */
        LEGACY_AUTO,
        /**
         * 对于已经在App中使用MobclickAgent.onPageStart/MobclickAgent.onPageEnd这两个接口对非Activity页面(如:Fragment)进行埋点统计的SDK老用户，
         * 则请选择LEGACY_MANUAL模式，这样您的App埋点代码不需要做任何修改，
         * SDK即可正常工作。(需确保您应用中所有Activity中都已经手动调用MobclickAgent.onResume/MobclickAgent.onPause接口)
         */
        LEGACY_MANUAL;

        private PageMode() {
        }
    }
}
