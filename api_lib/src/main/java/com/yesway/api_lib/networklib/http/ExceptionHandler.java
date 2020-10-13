package com.yesway.api_lib.networklib.http;

import android.net.ParseException;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.yesway.api_lib.R;
import com.yesway.api_lib.networklib.RetrofitManager;
import com.yesway.api_lib.provider.ILoginProvider;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 * 异常处理类
 */
public class ExceptionHandler {
    private static final String TAG = ExceptionHandler.class.getSimpleName();
    public static ResponseThrowable handleException(Throwable e) {
        Log.i(TAG, "e" + e.toString());
        ResponseThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseThrowable(e, SYSTEM_ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case SYSTEM_ERROR.UNAUTHORIZED:
                    ex.message = RetrofitManager.mContext.getString(R.string.unauthorized);
                    ex.code = SYSTEM_ERROR.UNAUTHORIZED;
                    break;
                case SYSTEM_ERROR.FORBIDDEN:
                    ex.message = RetrofitManager.mContext.getString(R.string.forbidden_error);
                    break;
                case SYSTEM_ERROR.NOT_FOUND:
                    ex.message = RetrofitManager.mContext.getString(R.string.res_not_found);
                    break;
                case SYSTEM_ERROR.REQUEST_TIMEOUT:
                    ex.message = RetrofitManager.mContext.getString(R.string.request_timeout);
                    break;
                case SYSTEM_ERROR.INTERNAL_SERVER_ERROR:
                    ex.message = RetrofitManager.mContext.getString(R.string.internal_server_error);
                    break;
                case SYSTEM_ERROR.SERVICE_UNAVAILABLE:
                    ex.message = RetrofitManager.mContext.getString(R.string.service_unavailable);
                    break;
                default:
                    ex.message = RetrofitManager.mContext.getString(R.string.net_work_error);
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JsonSyntaxException || e instanceof IllegalStateException || e instanceof JSONException || e instanceof ParseException || e instanceof MalformedJsonException) {

            ex = new ResponseThrowable(e, SYSTEM_ERROR.PARSE_ERROR);
            ex.message = RetrofitManager.mContext.getString(R.string.parse_error);
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable(e, SYSTEM_ERROR.NETWORD_ERROR);
            ex.message = RetrofitManager.mContext.getString(R.string.net_work_error);
            return ex;
        } else if (e instanceof javax.net.ssl.SSLException) {
            ex = new ResponseThrowable(e, SYSTEM_ERROR.SSL_ERROR);
            ex.message = RetrofitManager.mContext.getString(R.string.ssl_error);
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponseThrowable(e, SYSTEM_ERROR.TIMEOUT_ERROR);
            ex.message = RetrofitManager.mContext.getString(R.string.connect_timeout_error);
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ResponseThrowable(e, SYSTEM_ERROR.TIMEOUT_ERROR);
            ex.message = RetrofitManager.mContext.getString(R.string.connect_timeout_error);
            return ex;
        } else if (e instanceof java.net.UnknownHostException) {
            ex = new ResponseThrowable(e, SYSTEM_ERROR.TIMEOUT_ERROR);
            ex.message = RetrofitManager.mContext.getString(R.string.unknown_host);
            return ex;
        } else if (e instanceof ResponseThrowable) {
            ex = new ResponseThrowable(e, (((ResponseThrowable) e).code));
            switch (ex.getCode()) {
                case APP_ERROR.UNAUTHORIZED:
                    ILoginProvider mLoginProvider = (ILoginProvider) ARouter.getInstance().build("/main/provider").navigation();
                    if(mLoginProvider!=null){
                        mLoginProvider.logout(RetrofitManager.mContext);
                    }
                    break;
                default:
            }
            ex.message = e.getMessage();
            return ex;
        } else {
            ex = new ResponseThrowable(e, SYSTEM_ERROR.NETWORD_ERROR);
            ex.message = RetrofitManager.mContext.getString(R.string.net_work_error);
            return ex;
        }
    }

    public interface SYSTEM_ERROR {
        int UNAUTHORIZED = 401;
        int FORBIDDEN = 403;
        int NOT_FOUND = 404;
        int REQUEST_TIMEOUT = 408;
        int INTERNAL_SERVER_ERROR = 500;
        int SERVICE_UNAVAILABLE = 503;

        /**
         * 未知错误
         */
        int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        int PARSE_ERROR = 1001;
        /**
         * SSL_ERROR         * 网络错误
         */
        int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        int TIMEOUT_ERROR = 1006;

    }

    public interface APP_ERROR {
        int SUCC = 20000;//	处理成功，无错误
        int UNAUTHORIZED = 60000;//token失效
    }
}
