package com.yesway.api_lib;

import com.yesway.api_lib.base.ConmonRequest;
import com.yesway.api_lib.moudel.common.response.CheckVersionResponse;
import com.yesway.api_lib.networklib.bean.ResponseBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Description
 * @Author guojingbu
 * @Date 2019/9/23
 */
public interface CommonService {
    @POST("")
    Observable<ResponseBody> uploadFile(@Body MultipartBody body);

    /**
     * 检测版本更新
     *
     * @param conmonRequest
     * @return
     */
    @POST("")
    Observable<ResponseBean<CheckVersionResponse>> checkVersion(@Body ConmonRequest conmonRequest);
}
