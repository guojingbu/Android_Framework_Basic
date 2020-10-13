package com.yesway.api_lib.networklib;

import android.content.Context;

import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle3.components.support.RxFragment;
import com.yesway.api_lib.BuildConfig;
import com.yesway.api_lib.CommonService;
import com.yesway.api_lib.networklib.http.RxAdapter;
import com.yesway.api_lib.networklib.upload.FileUploadObserver;
import com.yesway.api_lib.networklib.upload.MultipartBuilder;
import com.yesway.api_lib.networklib.upload.UploadFileRequestBody;
import com.yesway.api_lib.utils.SSLContextUtil;
import com.yesway.api_lib.utils.VersionUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private OkHttpClient.Builder okHttpBuilder;
    private static final int DEFAULT_CONNECT_TIMEOUT = 30;
    private static final int DEFAULT_WRITE_TIMEOUT = 30;
    private static final int DEFAULT_READ_TIMEOUT = 30;
    public static Context mContext;
    private Retrofit retrofit;

    public static void init(Context context) {
        mContext = context;
    }

    private static class RetrofitHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static final RetrofitManager getInstance() {
        return RetrofitHolder.INSTANCE;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public RetrofitManager() {
        //手动创建一个OkHttpClient并设置超时时间
        okHttpBuilder = new OkHttpClient.Builder();
        /**
         * 设置头信息
         */
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader(HttpConfig.HEADER_CONTENT_TYPE, HttpConfig.PROTOCOL_CONTENT_TYPE)
                        .addHeader(HttpConfig.HEADER_BUILDNUMBER, VersionUtils.getAppVersionCode(mContext) + "")
                        .addHeader(HttpConfig.HEADER_VERSION_NAME, VersionUtils.getAppVersionName(mContext))
                        .addHeader(HttpConfig.DEVICE_SYSTEM_INFO, HttpConfig.getHeaderAppkey())// //设备信息：设备标识+设备型号+操作系统
                        .addHeader(HttpConfig.DEVICE_TYPE, HttpConfig.getDeviceType())
                        .addHeader(HttpConfig.HEADER_TIMESTAMP, String.valueOf(System.currentTimeMillis()))
                        .addHeader(HttpConfig.HEADER_ACCESSTOKEN, "token")
                        .addHeader(HttpConfig.HEADER_USERID, "1")
                        .addHeader(HttpConfig.HEADER_LANGUAGE_CODE, "zh")
                        .addHeader(HttpConfig.HEADER_APPKEY, HttpConfig.getAppKey())
                        .addHeader(HttpConfig.HEADER_X_OS_TYPE, HttpConfig.getOSType())
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        //处理多BaseUrl,添加应用拦截器
//        okHttpBuilder.addInterceptor(new MoreBaseUrlInterceptor());
        okHttpBuilder.addInterceptor(headerInterceptor);
//        if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置 Debug Log 模式
        okHttpBuilder.addInterceptor(loggingInterceptor);
        /**
         * 设置超时和重新连接
         */
        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //错误重连
        okHttpBuilder.retryOnConnectionFailure(false);
        //支持https
        SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
        if (sslContext != null) {
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            okHttpBuilder.sslSocketFactory(socketFactory);
        }
        okHttpBuilder.hostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.URL_HOST)
                .build();
    }


    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    /**
     * 设置订阅 和 所在的线程环境activty
     */
    public Observable toDispatchActivity(Observable o, Context context) {
        Observable observable = o.compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer())
                .compose(((RxAppCompatActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        return observable;
    }

    /**
     * 设置订阅 和 所在的线程环境fragment
     */
    public <T> Observable toDispatchFragment(Observable<T> o, final RxFragment fragment) {
        Observable observable = o.compose(RxAdapter.schedulersTransformer())
                .compose(RxAdapter.exceptionTransformer())
                .compose(fragment.<T>bindUntilEvent(FragmentEvent.DESTROY_VIEW));
        return observable;
    }

    /**
     * 上传单个文件
     *
     * @param file
     * @param fileUploadObserver
     */
    public void uploadFile(File file, FileUploadObserver<ResponseBody> fileUploadObserver) {
        UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(file, fileUploadObserver);
        create(CommonService.class).uploadFile(MultipartBuilder.fileToMultipartBody(file, uploadFileRequestBody))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileUploadObserver);
    }

    /**
     * 上传多个文件
     *
     * @param files
     * @param fileUploadObserver
     */
    public void uploadFiles(List<File> files, FileUploadObserver<ResponseBody> fileUploadObserver) {
        create(CommonService.class).uploadFile(MultipartBuilder.filesToMultipartBody(files, fileUploadObserver))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileUploadObserver);
    }

    /**
     * 多baseURL拦截器
     */
    public class MoreBaseUrlInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request originalRequest = chain.request();
            HttpUrl oldUrl = originalRequest.url();
            Request.Builder builder = originalRequest.newBuilder();
            List<String> type_app = originalRequest.headers("TYPE_BASEURL");
            if (type_app != null && type_app.size() > 0) {
                builder.removeHeader("urlname");
                String urlname = type_app.get(0);
                HttpUrl baseURL = null;
                baseURL = HttpUrl.parse(BuildConfig.URL_HOST);
                HttpUrl newHttpUrl = oldUrl.newBuilder()
                        .scheme(baseURL.scheme())//http协议如：http或者https
                        .host(baseURL.host())//主机地址
                        .port(baseURL.port())//端口
                        .build();
                Request newRequest = builder.url(newHttpUrl).build();
                return chain.proceed(newRequest);
            } else {
                return chain.proceed(originalRequest);
            }

        }
    }
}
