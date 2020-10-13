/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarPlus Project
 *                http://www.yesway.cn/
 * 创建时间：2017年4月11日
 * 内容说明：
 *
 * 编号                日期                     担当者             内容
 * -------------------------------------------------
 *
 * -------------------------------------------------- */
package com.yesway.common_lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.yesway.common_lib.R;

/**
 * 倒计时Button
 *
 * @author guojingbu
 */
@SuppressLint("AppCompatCustomView")
public class CountDownButton extends Button {
    /**
     * 总时间 </br>
     */
    private final int TOTAL_TIME = 60 * 1000;
    /**
     * 间隔时间
     */
    private final int INTERVAL_TIME = 1000;

    private OnCountDownListener mOnCountDownListener;

    private CountDownTimer mCountDownTimer;
    private String send;
    private String resend;

    public CountDownButton(Context context) {
        this(context, null);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setEnabled(true);
        send = context.getResources().getString(R.string.send_verify_code);
        resend = context.getResources().getString(R.string.resend_verify_code);

        mCountDownTimer = new CountDownTimer(TOTAL_TIME, INTERVAL_TIME) {

            @Override
            public void onTick(long millisUntilFinished) {
                // 时间+15毫秒，防止倒计时产生的毫秒差
                CountDownButton.this.setText(((millisUntilFinished + 15) / 1000) + "");
            }

            @Override
            public void onFinish() {
                CountDownButton.this.setEnabled(true);
                CountDownButton.this.setText(resend);
            }
        };

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_UP && mOnCountDownListener != null
                && mOnCountDownListener.onCountDown()) {
            mCountDownTimer.start();
            setEnabled(false);
        }

        return true;
    }

    public void startCountTime() {
        if (mCountDownTimer != null) {
            mCountDownTimer.start();
        }
        setEnabled(false);
    }

    public void resetButton() {
        CountDownButton.this.setEnabled(true);
        CountDownButton.this.setText(send);
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    /**
     * 设置button显示文字
     *
     * @param text
     */
    public void setBtnText(String text) {
        this.send = text;
        setText(send);
    }

    public void setOnCountDownListener(OnCountDownListener mOnCountDownListener) {
        this.mOnCountDownListener = mOnCountDownListener;
    }

    /**
     * 倒计时button点击事件
     */
    public interface OnCountDownListener {

        /**
         * @return true：开始执行
         */
        boolean onCountDown();
    }

}
