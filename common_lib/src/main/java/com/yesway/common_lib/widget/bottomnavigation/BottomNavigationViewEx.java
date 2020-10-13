package com.yesway.common_lib.widget.bottomnavigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;

public class BottomNavigationViewEx extends BottomNavigationViewInner {

    public BottomNavigationViewEx(Context context) {
        super(context);
    }

    public BottomNavigationViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomNavigationViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置底部导航icon显示
     * @param visibility
     * @return
     */
    @Override
    public BottomNavigationViewInner setIconVisibility(boolean visibility) {
        try {
            return super.setIconVisibility(visibility);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置是否显示文字
     * @param visibility
     * @return
     */
    @Override
    public BottomNavigationViewInner setTextVisibility(boolean visibility) {
        try {
            return super.setTextVisibility(visibility);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 是否禁用动画
     * @param enable It means the text won't scale and icon won't move when active it in no item shifting mode if false.
     * @return
     */
    @Override
    public BottomNavigationViewInner enableAnimation(boolean enable) {
        try {
            return super.enableAnimation(enable);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 是否禁用移位模式
     * @param enable It will has a shift animation if true. Otherwise all items are the same width.
     * @return
     */
    @Override
    public BottomNavigationViewInner enableShiftingMode(boolean enable) {
        try {
            return super.enableShiftingMode(enable);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     *如果为真，它将为item提供一个移位动画。否则，总是显示item文本
     * @param enable It will has a shift animation for item if true. Otherwise the item text always be shown.
     * @return
     */
    @Override
    public BottomNavigationViewInner enableItemShiftingMode(boolean enable) {
        try {
            return super.enableItemShiftingMode(enable);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 获取当前条目的position
     * @return
     */
    @Override
    public int getCurrentItem() {
        try {
            return super.getCurrentItem();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 根据菜单item获取到它所对应的position
     * @param item
     * @return
     */
    @Override
    public int getMenuItemPosition(MenuItem item) {
        try {
            return super.getMenuItemPosition(item);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 设置指定position为选中状态
     * @param index start from 0.
     * @return
     */
    @Override
    public BottomNavigationViewInner setCurrentItem(int index) {
        try {
            return super.setCurrentItem(index);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 获取导航item选中监听
     * @return
     */
    @Override
    public OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        try {
            return super.getOnNavigationItemSelectedListener();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 设置导航条目选中监听
     * @param listener
     */
    @Override
    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        try {
            super.setOnNavigationItemSelectedListener(listener);
        } catch (Exception e) {
        }
    }

    @Override
    public BottomNavigationMenuView getBottomNavigationMenuView() {
        return super.getBottomNavigationMenuView();
    }

    /**
     * 清除导航条目色调
     * @return
     */
    @Override
    public BottomNavigationViewInner clearIconTintColor() {
        try {
            return super.clearIconTintColor();
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 获取导航条目数组
     * @return
     */
    @Override
    public BottomNavigationItemView[] getBottomNavigationItemViews() {
        try {
            return super.getBottomNavigationItemViews();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据position获取item
     * @param position
     * @return
     */
    @Override
    public BottomNavigationItemView getBottomNavigationItemView(int position) {
        try {
            return super.getBottomNavigationItemView(position);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据指定position获取指定item对应的imageview
     * @param position
     * @return
     */
    @Override
    public ImageView getIconAt(int position) {
        try {
            return super.getIconAt(position);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据position获取小的label对应的textview
     * @param position
     * @return
     */
    @Override
    public TextView getSmallLabelAt(int position) {
        try {
            return super.getSmallLabelAt(position);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据position获取大的label 对应的textview
     * @param position
     * @return
     */
    @Override
    public TextView getLargeLabelAt(int position) {
        try {
            return super.getLargeLabelAt(position);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取导航item个数
     * @return
     */
    @Override
    public int getItemCount() {
        try {
            return super.getItemCount();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 设置小label字号
     * @param sp
     * @return
     */
    @Override
    public BottomNavigationViewInner setSmallTextSize(float sp) {
        try {
            return super.setSmallTextSize(sp);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置大label字号
     * @param sp
     * @return
     */
    @Override
    public BottomNavigationViewInner setLargeTextSize(float sp) {
        try {
            return super.setLargeTextSize(sp);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置大label与小label的字号
     * @param sp
     * @return
     */
    @Override
    public BottomNavigationViewInner setTextSize(float sp) {
        try {
            return super.setTextSize(sp);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置指定position图标的宽高
     * @param position position start from 0
     * @param width    in dp
     * @param height   in dp
     * @return
     */
    @Override
    public BottomNavigationViewInner setIconSizeAt(int position, float width, float height) {
        try {
            return super.setIconSizeAt(position, width, height);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置所有icon的宽高
     * @param width  in dp
     * @param height in dp
     * @return
     */
    @Override
    public BottomNavigationViewInner setIconSize(float width, float height) {
        try {
            return super.setIconSize(width, height);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置所有icon的宽高 width==height
     * @param dpSize  in dp
     * @return
     */
    @Override
    public BottomNavigationViewInner setIconSize(float dpSize) {
        try {
            return super.setIconSize(dpSize);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置条目的高度
     * @param height in px
     * @return
     */
    @Override
    public BottomNavigationViewInner setItemHeight(int height) {
        try {
            return super.setItemHeight(height);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 获取条目的高度
     * @return
     */
    @Override
    public int getItemHeight() {
        try {
            return super.getItemHeight();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 设置导航字体
     * @param typeface
     * @param style
     * @return
     */
    @Override
    public BottomNavigationViewInner setTypeface(Typeface typeface, int style) {
        try {
            return super.setTypeface(typeface, style);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置导航字体
     * @param typeface
     * @return
     */
    @Override
    public BottomNavigationViewInner setTypeface(Typeface typeface) {
        try {
            return super.setTypeface(typeface);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 绑定viewpager
     * @param viewPager
     * @return
     */
    @Override
    public BottomNavigationViewInner setupWithViewPager(ViewPager viewPager) {
        try {
            return super.setupWithViewPager(viewPager);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 绑定viewpager 是否平滑切换
     * @param viewPager
     * @param smoothScroll whether ViewPager changed with smooth scroll animation
     * @return
     */
    @Override
    public BottomNavigationViewInner setupWithViewPager(ViewPager viewPager, boolean smoothScroll) {
        try {
            return super.setupWithViewPager(viewPager, smoothScroll);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 指定条目设置是否启用移位模式
     * @param position
     * @param enable
     * @return
     */
    @Override
    public BottomNavigationViewInner enableShiftingMode(int position, boolean enable) {
        try {
            return super.enableShiftingMode(position, enable);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置指定条目的背景
     * @param position
     * @param background
     * @return
     */
    @Override
    public BottomNavigationViewInner setItemBackground(int position, int background) {
        try {
            return super.setItemBackground(position, background);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置指定item的icon的颜色选择器
     * @param position
     * @param tint
     * @return
     */
    @Override
    public BottomNavigationViewInner setIconTintList(int position, ColorStateList tint) {
        try {
            return super.setIconTintList(position, tint);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置指定item的icon的文字选择器
     * @param position
     * @param tint
     * @return
     */
    @Override
    public BottomNavigationViewInner setTextTintList(int position, ColorStateList tint) {
        try {
            return super.setTextTintList(position, tint);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置所有icon距离顶部的距离
     * @param marginTop in px
     * @return
     */
    @Override
    public BottomNavigationViewInner setIconsMarginTop(int marginTop) {
        try {
            return super.setIconsMarginTop(marginTop);
        } catch (Exception e) {
            return this;
        }
    }

    /**
     * 设置指定item距离顶部距离
     * @param position
     * @param marginTop in px
     * @return
     */
    @Override
    public BottomNavigationViewInner setIconMarginTop(int position, int marginTop) {
        try {
            return super.setIconMarginTop(position, marginTop);
        } catch (Exception e) {
            return this;
        }
    }
}