package com.yesway.common_lib.base;

public interface IBaseView {

    void showInitLoading();

    void hideInitLoading();

    /**
     * 显示半透明loading框
     */
    void showTransLoading();

    /**
     * 隐藏半透明loading框
     */
    void hideTransLoading();

    /**
     * 显示无数据页面
     *
     * @param content
     * @param drawableId
     */
    void showNoDataPage(String content, int drawableId);

    /**
     * 主题灰色背景
     */
    void showNetWorkErrorPage();

    /**
     * 透明背景
     */
    void showTransNetWorkErrorPage();
    void hideNoData();

    /**
     * 隐藏错误页面
     */
    void hideErrorPage();

}
