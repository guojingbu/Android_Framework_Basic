package com.yesway.module_message;

import com.yesway.api_lib.networklib.bean.ResponseBean;
import com.yesway.common_lib.base.BaseActivity;
import com.yesway.messagelibrary.api.MsgModel;
import com.yesway.messagelibrary.api.bean.TypeAll;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    @Override
    public void initData() {
        MsgModel msgModel = new MsgModel(this);
        msgModel.getallMsgType().subscribe(new Observer<ResponseBean<TypeAll>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBean<TypeAll> typeAllResponseBean) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    public int bindView() {
        return R.layout.activity_main;
    }
}
