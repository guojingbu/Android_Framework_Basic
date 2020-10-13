package com.yesway.common_lib.utils.language;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;

import com.yesway.api_lib.utils.SPUtils;
import com.yesway.common_lib.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @Description 语言切换工具类
 * @Author guojingbu
 * @Date 2019/9/24
 */
public class MultiLanguageUtil {
    private static final String TAG = "MultiLanguageUtil";
    private static MultiLanguageUtil instance;
    private static final String SAVE_LANGUAGE = "save_language";

    private static Locale mSystemCurrentLocal = Locale.ENGLISH;

    //    private static int DEFAULT_LANGUAGE_TYPE = LanguageType.LANGUAGE_EN;
    private static int DEFAULT_LANGUAGE_TYPE = LanguageType.LANGUAGE_FOLLOW_SYSTEM;

    public static MultiLanguageUtil getInstance() {
        if (instance == null) {
            synchronized (MultiLanguageUtil.class) {
                if (instance == null) {
                    instance = new MultiLanguageUtil();
                }
            }
        }
        return instance;
    }

    private MultiLanguageUtil() {
    }

    /**
     * 如果不是英文、泰文，默认返回英文
     *
     * @return
     */
    public Locale getLanguageLocale(Context context) {
        int languageType = (int) SPUtils.get(context, MultiLanguageUtil.SAVE_LANGUAGE, DEFAULT_LANGUAGE_TYPE);
        if (languageType == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
            //Locale sysLocale = mSystemCurrentLocal;
            return mSystemCurrentLocal;
        } else if (languageType == LanguageType.LANGUAGE_EN) {
            return Locale.ENGLISH;
        } else if (languageType == LanguageType.LANGUAGE_TH) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return Locale.forLanguageTag("th");
            } else {
                return new Locale("th");
            }
        }
        getSystemLanguage(getSysLocale());
        Log.e(TAG, "getLanguageLocale" + languageType + languageType);
        return Locale.ENGLISH;
    }

    private String getSystemLanguage(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    @Deprecated
    public Locale getSysLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * @param context
     */
    public static void saveSystemCurrentLanguage(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        Log.d(TAG, locale.getLanguage());
        mSystemCurrentLocal = locale;
    }

    /**
     * 更新语言
     *
     * @param
     * @param languageType
     */
    public void updateLanguage(Context context, int languageType) {
        SPUtils.put(context, MultiLanguageUtil.SAVE_LANGUAGE, languageType);
        setConfiguration(context);
        setApplicationLanguage(context);
        EventBus.getDefault().post(new LanguageChangeEvent(languageType));
    }

    public String getLanguageName(Context context) {
        int languageType = (int) SPUtils.get(context, MultiLanguageUtil.SAVE_LANGUAGE, DEFAULT_LANGUAGE_TYPE);
        if (languageType == LanguageType.LANGUAGE_EN) {
            return context.getString(R.string.setting_language_english);
        } else if (languageType == LanguageType.LANGUAGE_TH) {
            return context.getString(R.string.setting_language_th);
        }
        return context.getString(R.string.setting_language_english);
    }

    /**
     * 获取语言代码
     *
     * @param context
     * @return
     */
    public String getLanguageCode(Context context) {
        Locale languageLocale = MultiLanguageUtil.getInstance().getLanguageLocale(context);
        String language = languageLocale.getLanguage();
        //如果不是泰文或者英文就返回en
        if (!"th".equals(language) && !"en".equals(language)) {
            return "en";
        }
        return languageLocale.getLanguage();
    }

    public String getLanguageNameReal(Context context) {
        int languageType = (int) SPUtils.get(context, MultiLanguageUtil.SAVE_LANGUAGE, DEFAULT_LANGUAGE_TYPE);
        if (languageType == LanguageType.LANGUAGE_EN) {
            return context.getString(R.string.setting_language_english);
        } else if (languageType == LanguageType.LANGUAGE_TH) {
            return context.getString(R.string.setting_language_th);
        } else if (languageType == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
            return context.getString(R.string.follow_system);
        }
        return context.getString(R.string.setting_language_english);
    }

    /**
     * 获取到用户保存的语言类型
     *
     * @return
     */
    public int getLanguageType(Context context) {
        int languageType = (int) SPUtils.get(context, MultiLanguageUtil.SAVE_LANGUAGE, DEFAULT_LANGUAGE_TYPE);
        if (languageType == LanguageType.LANGUAGE_EN) {
            return LanguageType.LANGUAGE_EN;
        } else if (languageType == LanguageType.LANGUAGE_TH) {
            return LanguageType.LANGUAGE_TH;
        } else if (languageType == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
            return LanguageType.LANGUAGE_FOLLOW_SYSTEM;
        }
        Log.e(TAG, "getLanguageType" + languageType);
        return languageType;
    }

    public static Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context);
        } else {
            return context;
        }
    }

    /**
     * 设置语言
     */
    public void setConfiguration(Context context) {
        Locale targetLocale = getLanguageLocale(context);
        Locale.setDefault(targetLocale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.locale = targetLocale;
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getInstance().getLanguageLocale(context);
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    /**
     * 设置语言类型
     */
    public void setApplicationLanguage(Context context) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = getLanguageLocale(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.getApplicationContext().createConfigurationContext(config);
            Locale.setDefault(locale);
        } else {
            config.setLocale(locale);
        }
        resources.updateConfiguration(config,dm);
    }

    /**
     * 获取当前系统支持的语言集
     *
     * @return
     */
    public List<String> getLanguages() {
        List<String> list = new ArrayList<>();
        Locale[] lg = Locale.getAvailableLocales();
        for (Locale locale : lg) {
            String language = locale.getLanguage();
            Log.i(TAG, language);
            //去掉重复的语言
            if (!list.contains(language)) {
                list.add(language);
            }
        }
        return list;
    }
}
