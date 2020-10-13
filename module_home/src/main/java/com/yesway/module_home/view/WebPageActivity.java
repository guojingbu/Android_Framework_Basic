package com.yesway.module_home.view;

import android.content.Context;
import android.content.Intent;

import com.yesway.common_lib.base.BaseWebPageActivity;

/**
 * author : guojingbu
 * date   : 2020/9/25
 * desc   :
 */
public class WebPageActivity extends BaseWebPageActivity {

    public static void startWebPageActivity(Context context) {
        Intent intent = new Intent(context, WebPageActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initData() {
        mWebView.loadUrl("https://www.youku.com/");
    }
}
