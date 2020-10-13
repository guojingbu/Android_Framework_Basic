package com.yesway.common_lib.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

public class TextViewUtils {
    /**
     * 设置部分文字加下划线, 特殊颜色, 及点击效果
     * @param tv        传入的TextView
     * @param strId     传入 TextView 需要展示的文字在string.xml中的id
     * @param underLine 是否显示下划线
     * @param color     部分特殊文字的颜色 传入-1 默认为蓝色
     * @param start     特殊文字从哪个位置开始 传入 -1 则从文字最开始设置
     * @param end       特殊文字从哪个位置结束 传入 -1 则默认设置到文字最后
     * @param textSize  文字大小sp  传入 > 0 的 sp 值
     * @param listener  传入自定义点击监听器 不需要时传 null 即可
     */
    public static void setSpan(TextView tv,
                               @StringRes int strId,
                               final boolean underLine,
                               @ColorRes final int color,
                               int start,
                               int end,
                               int textSize,
                               final View.OnClickListener listener){

        final Context context = tv.getContext();
        tv.setHighlightColor(ContextCompat.getColor(context, android.R.color.transparent));
        String str = context.getString(strId);
        SpannableString info = new SpannableString(str);

        int startNum = (start <= -1) ? 0 : start;
        int endNum = (end <= -1) ? str.length() : end;

        if (textSize > 0){
            info.setSpan(new AbsoluteSizeSpan(sp2px(context, textSize)), startNum,
                    endNum, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        info.setSpan(new ClickableSpan() {

                         /**
                          * 重写父类点击事件
                          */
                         @Override
                         public void onClick(View widget) {
                             if (listener != null) {
                                 listener.onClick(widget);
                             }
                         }

                         /**
                          * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
                          */
                         @Override
                         public void updateDrawState(TextPaint ds) {
                             int colorRes = color;
                             if(color == -1) colorRes = Color.BLUE;
                             ds.setColor(ContextCompat.getColor(context, colorRes));
                             ds.setUnderlineText(underLine);
                         }
                     }, startNum,
                endNum, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(info);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
