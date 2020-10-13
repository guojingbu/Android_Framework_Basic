/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarStore Project
 *                http://www.yesway.cn/
 * 创建时间：2016/7/02
 * 内容说明：公共的fragment基类
 *
 * 编号		日期			担当者		内容
 * -------------------------------------------------
 *
 *
 *--------------------------------------------------*/
package com.yesway.common_lib.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle3.components.support.RxFragment;
import com.umeng.analytics.MobclickAgent;
import com.yesway.common_lib.R;
import com.yesway.common_lib.eventbus.BindEventBus;
import com.yesway.common_lib.eventbus.EventBusUtils;
import com.yesway.common_lib.widget.ErrorHintView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 公共的Fragment基类
 */
public abstract class BaseFragment extends RxFragment implements IBaseView {
    public static final String TAG = "YESWAY";
    /**
     * 可视化View
     */
    public View mView;
    /**
     * presenter泛型
     */
    private ErrorHintView mErrorHintView;
    protected RxAppCompatActivity mActivity;
    private boolean isViewCreated = false;
    private boolean isViewVisable = false;
    private ViewStub mViewSubContent;
    private ViewStub mViewSubToolBar;
    private Toolbar mToolbar;
    protected TextView mTxtTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (RxAppCompatActivity) getActivity();
        setHasOptionsMenu(true);//加上这句话，menu才会显示出来
        ARouter.getInstance().inject(this);

        // 若使用BindEventBus注解，则绑定EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.register(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mTxtTitle != null) {
            MobclickAgent.onPageStart(mTxtTitle.getText().toString());
        } else {
            MobclickAgent.onPageStart(getClass().getSimpleName());
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTxtTitle != null) {
            MobclickAgent.onPageEnd(mTxtTitle.getText().toString());
        } else {
            MobclickAgent.onPageEnd(getClass().getSimpleName());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isViewCreated = true;
        mView = inflater.inflate(R.layout.fragment_base_view, null);
        initCommonView(mView);
        initView(mView);
        initListener();
        return mView;
    }

    private void initCommonView(View view) {
        mErrorHintView = (ErrorHintView) view.findViewById(R.id.hintView);
        mViewSubToolBar = (ViewStub) view.findViewById(R.id.view_stub_toolbar);
        mViewSubContent = (ViewStub) view.findViewById(R.id.view_contnet);
        if (enableToolBar()) {
            mViewSubToolBar.setLayoutResource(onBindToolBarLayout());
            View toolBarView = mViewSubToolBar.inflate();
            initTooBar(toolBarView);
        }
        mViewSubContent.setLayoutResource(bindView());
        mViewSubContent.inflate();
    }

    /**
     * 初始化titlebar
     *
     * @param view
     */
    protected void initTooBar(View view) {
        mToolbar = view.findViewById(R.id.toolbar_root);
        mTxtTitle = view.findViewById(R.id.toolbar_title);
        if (mToolbar != null) {
            mActivity.setSupportActionBar(mToolbar);
            mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            if (isVisibleCallBackBtn()) {
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.onBackPressed();
                    }
                });
            } else {
                mToolbar.setNavigationIcon(null);
            }

        }
        if (mTxtTitle != null) {
            mTxtTitle.setText(getToolbarTitle());
        }
    }

    public Toolbar getToolBar() {
        return mToolbar;
    }

    public int onBindToolBarLayout() {
        return R.layout.common_toolbar;
    }

    /**
     * toolbar是否可用
     *
     * @return
     */
    public boolean enableToolBar() {
        return false;
    }

    /**
     * 是否显示返回按钮
     *
     * @return
     */
    public boolean isVisibleCallBackBtn() {
        return false;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //如果启用了懒加载就进行懒加载，否则就进行预加载
        if (enableLazyData()) {
            lazyLoad();
        } else {
            initData();
        }
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        //这里进行双重标记判断,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isViewVisable) {
            initData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isViewVisable = false;
        }
    }

    /**
     * 是否开启懒加载
     *
     * @return
     */
    public boolean enableLazyData() {
        return false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isViewVisable = isVisibleToUser;
        if (enableLazyData() && isViewVisable) {
            lazyLoad();
        }
    }

    /**
     * 显示loading
     */
    @Override
    public void showInitLoading() {
        mErrorHintView.loadingData();
    }

    /**
     * 隐藏loading
     */
    @Override
    public void hideInitLoading() {
        mErrorHintView.hideLoading();
    }

    @Override
    public void showTransLoading() {
        mErrorHintView.loadingTransData();
    }

    @Override
    public void hideTransLoading() {
        mErrorHintView.hideTransLoading();
    }

    @Override
    public void showNoDataPage(String content, int drawableId) {
        if (mErrorHintView != null) {
            mErrorHintView.noData(content, drawableId);
        }
    }

    @Override
    public void hideNoData() {
        mErrorHintView.close();
    }

    @Override
    public void showNetWorkErrorPage() {
        if (mErrorHintView == null) {
            return;
        }
        mErrorHintView.netError(0, new ErrorHintView.OperateListener() {

            @Override
            public void operate() {
                re_Request();
            }
        });
    }

    @Override
    public void showTransNetWorkErrorPage() {
        if (mErrorHintView == null) {
            return;
        }
        mErrorHintView.netError(R.color.transparent, new ErrorHintView.OperateListener() {

            @Override
            public void operate() {
                re_Request();
            }
        });
    }

    @Override
    public void hideErrorPage() {
        if (mErrorHintView != null) {
            mErrorHintView.close();
        }
    }

    /**
     * presenter释放view引用
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 若使用BindEventBus注解，则解绑定EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.unregister(this);
        }
    }

    /**
     * 初始化view对象
     */
    public abstract void initView(View view);

    /**
     * 绑定view
     */
    public abstract int bindView();

    /**
     * title
     *
     * @return
     */
    public abstract int getToolbarTitle();

    /**
     * 填充数据
     */
    public abstract void initData();

    /**
     * 初始化事件监听
     */
    public abstract void initListener();

    /**
     * 重新加载
     */
    protected void re_Request() {

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {

        super.setMenuVisibility(menuVisible);

        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)

    public void onMessageEvent(Object myEvent) {

    }

    /**
     * 设置toolbar文字
     */
    public void setToobarText(String title) {

        if (mTxtTitle != null && !TextUtils.isEmpty(title)) {
            mTxtTitle.setText(title);
        }
    }
}
