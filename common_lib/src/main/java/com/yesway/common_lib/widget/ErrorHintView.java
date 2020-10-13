package com.yesway.common_lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yesway.common_lib.R;

/**
 * 错误页面
 */
public class ErrorHintView extends RelativeLayout {

    private RelativeLayout mContainer;
    private ErrorHandler mErrorHandler = new ErrorHandler();
    private OperateListener mOperateListener;
    private LayoutParams layoutParams;
    private RotateAnimation mRotateAnimation;

    // private AnimationDrawable animationDrawable;
    public ErrorHintView(Context context) {
        super(context);
        init();
    }

    public ErrorHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ErrorHintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        View.inflate(getContext(), R.layout.custom_error_hint_view, this);
        mContainer = (RelativeLayout) findViewById(R.id.container);
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mRotateAnimation = new RotateAnimation(0f, 359f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setDuration(1000L);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setRepeatCount(-1);
        mRotateAnimation.setRepeatMode(Animation.RESTART);
    }


    class ErrorHandler {
        public void operate(IStrategy iStrategy) {
            show();
            iStrategy.operate();
        }
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void close() {
        setVisibility(View.GONE);
    }

    /**
     * 显示加载失败UI
     */
    public void loadFailure(OperateListener Listener) {
        this.mOperateListener = Listener;
        mErrorHandler.operate(new LoadFailure());
    }

    View loadFailure = null;

    /**
     * 加载失败策略
     */
    class LoadFailure implements IStrategy {

        @Override
        public void operate() {
            if (loadFailure == null) {
                loadFailure = View.inflate(getContext(), R.layout.layout_load_failure, null);
            }
            View view = loadFailure.findViewById(R.id.load_retry);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOperateListener != null) {
                        mOperateListener.operate();
                    }
                }
            });

            mContainer.removeAllViews();
            mContainer.addView(loadFailure, layoutParams);
        }
    }

    /**
     * 显示无网络
     *
     * @param operateListener
     */
    public void netError(int rootBgColorId,OperateListener operateListener) {
        this.mOperateListener = operateListener;
        mErrorHandler.operate(new NetWorkError(rootBgColorId));
    }

    View netError;

    /**
     * 无网络加载策略
     */
    class NetWorkError implements IStrategy {
        private int rootBgColorId = 0;

        public NetWorkError(int rootBgColorId) {
            this.rootBgColorId = rootBgColorId;
        }

        @Override
        public void operate() {
            if (netError == null) {
                netError = View.inflate(getContext(), R.layout.layout_load_wifi_failure, null);
                View view = netError.findViewById(R.id.wifi_retry);
                RelativeLayout mRlNetErrorRoot = netError.findViewById(R.id.rl_net_error_root);
                if(rootBgColorId!=0){
                    mRlNetErrorRoot.setBackgroundColor(netError.getContext().getResources().getColor(rootBgColorId));
                }
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOperateListener != null) {
                            mOperateListener.operate();
                        }

                    }
                });
            }
            mContainer.removeAllViews();
            mContainer.addView(netError, layoutParams);
        }
    }

    View noData;

    /**
     * 显示无数据
     */
    public void noData(String content, int imgResId) {
        mErrorHandler.operate(new NoDataError(content, imgResId));
    }

    /**
     * 无数据显示策略
     */
    class NoDataError implements IStrategy {
        private String content;
        private int imgResId = 0;

        public NoDataError(String content, int imgResId) {
            this.content = content;
            this.imgResId = imgResId;
        }

        @Override
        public void operate() {
            if (noData == null) {
                noData = View.inflate(getContext(),
                        R.layout.layout_load_nodata, null);
                TextView tvContent = noData.findViewById(R.id.tv_nodata_text);
                ImageView ivNodata = noData.findViewById(R.id.iv_nodata_Icon);
                if (imgResId != 0) {
                    ivNodata.setImageResource(imgResId);
                }
                tvContent.setText(content);
            }
            mContainer.removeAllViews();
            mContainer.addView(noData, layoutParams);
        }
    }

    View loadingdata;

    class LoadingData implements IStrategy {


        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void operate() {
            if (loadingdata == null) {
                loadingdata = View.inflate(getContext(),
                        R.layout.layout_load_loading, null);
            }
            LinearLayout loadingRoot = loadingdata.findViewById(R.id.rl_loading_root);
            loadingRoot.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            ImageView iv = (ImageView) loadingdata
                    .findViewById(R.id.iv_loading);
            mContainer.removeAllViews();
            mContainer.addView(loadingdata, layoutParams);
            showLoading(iv);
        }
    }

    View loadingTrans;

    class LoadingTransData implements IStrategy {

        @Override
        public void operate() {
            if (loadingTrans == null) {
                loadingTrans = View.inflate(getContext(),
                        R.layout.layout_load_trans_loading, null);
            }
            LinearLayout loadingRoot = (LinearLayout) loadingTrans.findViewById(R.id.rl_loading_root);
            loadingRoot.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            ImageView iv = (ImageView) loadingTrans
                    .findViewById(R.id.img_loading);
            mContainer.removeAllViews();
            mContainer.addView(loadingTrans, layoutParams);
            showLoading(iv);
        }
    }


    /**
     * 转菊花
     */
    public void loadingData() {
        mErrorHandler.operate(new LoadingData());
    }
    /**
     * 隐藏动画loading
     */
    public void hideLoading() {
        if (mRotateAnimation != null) {
            mRotateAnimation.cancel();
        }
        close();
    }
    /**
     * 转菊花
     */
    public void loadingTransData() {
        mErrorHandler.operate(new LoadingTransData());
    }
    /**
     * 隐藏动画loading
     */
    public void hideTransLoading() {
        if (mRotateAnimation != null) {
            mRotateAnimation.cancel();
        }
        close();
    }


    /**
     * 显示动画loading
     */
    @SuppressLint("WrongConstant")
    public void showLoading(final ImageView iv) {
        iv.clearAnimation();
        //开启旋转动画
        iv.startAnimation(mRotateAnimation);
    }


    public interface OperateListener {
        void operate();
    }
}
