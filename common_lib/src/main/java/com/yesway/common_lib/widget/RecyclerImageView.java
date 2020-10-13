/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarPlus Project
 *                http://www.yesway.cn/
 * 创建时间：2016-7-15
 * 内容说明：
 *
 * 编号                日期                     担当者             内容
 * -------------------------------------------------
 *
 * -------------------------------------------------- */
package com.yesway.common_lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Px;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.yesway.common_lib.R;

import java.util.List;

/**
 * 自定义轮播图
 *
 * @author zhangke
 */
public class RecyclerImageView extends FrameLayout implements ViewPager.OnPageChangeListener, OnTouchListener {

    /**
     * 自动切换时间
     */
    private final int AUTO_SWITCH_TIME = 5 * 1000;

    /**
     * 上下文
     */
    private Context mContext;
    /**
     * viewpager
     */
    private ViewPager mViewPager;
    /**
     * 标题指示器
     */
    private LinearLayout mRlBottom;
    /**
     * 指示器
     */
    private RadioGroup mRgDots;
    /**
     * 标题
     */
    private TextView mTvTitle;

    /**
     * image url
     */
    private List<String> mImageUrlList;
    /**
     * image ids;
     */
    private int[] mImageIdsList;
    /**
     * 标题
     */
    private List<String> mTitleList;
    /**
     * 适配器
     */
    private RecyclerAdpater mAdapter;
    /**
     * 事件监听
     */
    private OnImageClickListener mOnImageClickListener;
    /**
     * 加载网络图片标示
     */
    private boolean mLoadNetworkFlag = false;
    /**
     * 页面数量
     */
    private int mPageCount;
    private boolean mLooper;
    private OnPageChangeListener mPageChangeListener;

    public RecyclerImageView(Context context) {
        this(context, null);
    }

    public RecyclerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.recycler_imageview_layout, this);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mRlBottom = (LinearLayout) findViewById(R.id.rl_bottom);
        mRgDots = (RadioGroup) findViewById(R.id.rg_dots);
        mTvTitle = (TextView) findViewById(R.id.tv_title);

        mViewPager.addOnPageChangeListener(this);
//        mViewPager.setOnTouchListener(this);
    }

    public void loadNetworkResources(List<String> urlList, List<String> titleList, OnImageClickListener listener) {
        loadNetworkResources(urlList, titleList, listener, false, false);
    }

    /**
     * 加载网络资源
     */
    public void loadNetworkResources(List<String> urlList, List<String> titleList, OnImageClickListener listener, boolean showDots, boolean autoScroll) {
        mLoadNetworkFlag = true;

        this.mImageUrlList = urlList;
        this.mTitleList = titleList;
        this.mOnImageClickListener = listener;

        if (showDots) {
            initDots(mRgDots, urlList.size());
        }

        showViewPager(autoScroll);
    }

    /**
     * 加载本地资源
     */
    public void loadLocalResources(int[] idsList, List<String> titleList, boolean autoScroll, boolean isLooper) {
        mLoadNetworkFlag = false;

        this.mImageIdsList = idsList;
        this.mTitleList = titleList;
        this.mLooper = isLooper;

        showTitle(0);
        initDots(mRgDots, idsList.length);
        showViewPager(autoScroll);
    }

    /**
     * 显示viewpager
     *
     * @param autoScroll
     */
    private void showViewPager(boolean autoScroll) {
        mAdapter = new RecyclerAdpater();
        mViewPager.setAdapter(mAdapter);
        if (mLoadNetworkFlag) {
            if (mLooper) {
                mViewPager.setCurrentItem(mImageUrlList.size() * 10000);
            } else {
                mViewPager.setCurrentItem(0);
            }

        } else {
            if (mLooper) {
                mViewPager.setCurrentItem(mImageIdsList.length * 10000);
            } else {
                mViewPager.setCurrentItem(0);
            }
        }


        if (autoScroll) {
            startScroller();
        }

    }

    public void setCurrentItem(int currentItem) {
        mViewPager.setCurrentItem(currentItem);
    }

    /**
     * 显示dots
     */
    private void initDots(RadioGroup radioGroup, int pageCount) {
        if (pageCount <= 0) {
            return;
        }
        // 清除所有view
        radioGroup.removeAllViews();
        mPageCount = pageCount;

        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 0, 10, 0);
        params.gravity = Gravity.CENTER;

        RadioButton radioButton = null;
        for (int i = 0; i < pageCount; i++) {
            radioButton = new RadioButton(mContext);
            radioButton.setButtonDrawable(R.drawable.selector_dots);
            radioButton.setLayoutParams(params);
            radioButton.setClickable(false);
            radioGroup.addView(radioButton, params);
        }

        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                // 开始图片滚动
                startScroller();
                break;
            default:
                stopScroller();
                break;
        }
        return false;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, @Px int positionOffsetPixels) {
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int index) {
        index = index % mPageCount;
        ((RadioButton) mRgDots.getChildAt(index)).setChecked(true);
        showTitle(index);
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageSelected(index);
        }
    }

    private void showTitle(int index) {
        if (mTitleList == null || mTitleList.isEmpty()) {
            return;
        }
        String title = mTitleList.get(index);
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(mTitleList.get(index));
        } else {
            mTvTitle.setText("");
        }
    }

    /**
     * 自动轮播
     */
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mPageCount > 0) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                mHandler.postDelayed(mRunnable, AUTO_SWITCH_TIME);
            }

        }
    };

    /**
     * 停止滚动
     */
    private void stopScroller() {
        mHandler.removeCallbacks(mRunnable);
    }

    /**
     * 开始滚动
     */
    private void startScroller() {
        mHandler.postDelayed(mRunnable, AUTO_SWITCH_TIME);
    }

    /**
     * 轮播图适配器
     *
     * @author zhangke
     */
    private class RecyclerAdpater extends PagerAdapter {

        @Override
        public int getCount() {
            if (mLooper) {
                return Integer.MAX_VALUE >> 1;
            } else {
                if (mLoadNetworkFlag) {
                    return mImageUrlList.size();
                } else {
                    return mImageIdsList.length;
                }
            }
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @SuppressLint("NewApi")
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            container.addView(imageView);

            if (mLoadNetworkFlag) {
                Glide.with(mContext).load(mImageUrlList.get(position % mImageUrlList.size())).into(imageView);
            } else {
                Glide.with(mContext).load(mImageIdsList[position % mImageIdsList.length]).into(imageView);
            }

            if (mOnImageClickListener != null) {
                imageView.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mOnImageClickListener.onImageClick(position % mImageUrlList.size());
                    }
                });
            }


            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }

    }

    public void addOnPageChangeListener(OnPageChangeListener pageChangeListener) {
        this.mPageChangeListener = pageChangeListener;
    }

    /**
     * viewpager item点击事件
     */
    public interface OnImageClickListener {
        /**
         * pager点击事件
         */
        void onImageClick(int position);
    }

    public interface OnPageChangeListener {

        void onPageScrolled(int position, float positionOffset, @Px int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }
}
