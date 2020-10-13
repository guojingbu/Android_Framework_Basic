package com.yesway.api_lib.networklib.http;


import androidx.annotation.NonNull;

import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.yesway.api_lib.R;
import com.yesway.api_lib.networklib.RetrofitManager;
import com.yesway.api_lib.networklib.bean.BaseResponseBean;
import com.yesway.api_lib.utils.ToastUtils;

import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RxAdapter {
    /**
     * 生命周期绑定
     *
     * @param lifecycle Activity
     */
    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull LifecycleProvider lifecycle) {
        if (lifecycle != null) {
            return lifecycle.bindUntilEvent(ActivityEvent.DESTROY);
        } else {
            throw new IllegalArgumentException("context not the LifecycleProvider type");
        }
    }
    /**
     * 线程调度器
     */
    public static SingleTransformer singleSchedulersTransformer() {
        return new SingleTransformer() {
            @Override
            public SingleSource apply(Single upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    public static SingleTransformer singleExceptionTransformer() {

        return new SingleTransformer() {
            @Override
            public SingleSource apply(Single observable) {
                return observable
                        .map(new HandleFuc())  //这里可以取出BaseResponse中的Result
                        .onErrorResumeNext(new HttpResponseFunc());
            }
        };
    }
    /**
     * 线程调度器
     */
    public static ObservableTransformer schedulersTransformer() {
        return new ObservableTransformer(){
            @Override
            public ObservableSource apply(io.reactivex.Observable upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static ObservableTransformer exceptionTransformer() {

        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(io.reactivex.Observable observable) {
                return observable
                        .map(new HandleFuc())  //这里可以取出BaseResponse中的Result
                        .onErrorResumeNext(new HttpResponseFunc());
            }
        };
    }

    private static class HttpResponseFunc<T> implements Function<Throwable, io.reactivex.Observable<T>> {
        @Override
        public io.reactivex.Observable<T> apply(Throwable t) {

            ResponseThrowable exception = ExceptionHandler.handleException(t);
            if(exception.code ==  ExceptionHandler.SYSTEM_ERROR.TIMEOUT_ERROR ){
                ToastUtils.showToast(RetrofitManager.mContext.getString(R.string.net_work_error));
            }
            return io.reactivex.Observable.error(exception);
        }
    }

    private static class HandleFuc implements Function<Object, Object> {

        @Override
        public Object apply(Object o) throws Exception {
            if(o instanceof BaseResponseBean){
                if(o instanceof BaseResponseBean){
                    BaseResponseBean baseHeader = (BaseResponseBean) o;
                    if(baseHeader.getCode() != ExceptionHandler.APP_ERROR.SUCC){
//                    ToastUtils.showToast(baseHeader.getMsg());
                        throw new ResponseThrowable(baseHeader.getCode(),baseHeader.getMsg());
                    }
                }
            }
            return o;
        }
    }

}
