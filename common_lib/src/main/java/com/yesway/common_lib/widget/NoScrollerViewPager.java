/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarPlus Project
 *                http://www.yesway.cn/
 * 创建时间：2017年3月23日
 * 内容说明：
 * 
 * 编号                日期                     担当者             内容                  
 * -------------------------------------------------
 *
 * -------------------------------------------------- */
package com.yesway.common_lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 不可滑动的viewpager
 */
public class NoScrollerViewPager extends ViewPager {

    /**
     * 是否可以滑动
     */
    private boolean scroll = true;

    public NoScrollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollerViewPager(Context context) {
        this(context, null);
    }


    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (scroll) {
            return super.onTouchEvent(arg0);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (scroll) {
            return super.onInterceptTouchEvent(arg0);
        }
        return false;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    /**
     * 设置是否能滑动
     *
     * @param scroll false：不能滑动
     */
    public void setScroll(boolean scroll) {
        this.scroll = scroll;
    }

}
