package com.yesway.api_lib.networklib.upload;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @Description
 * @Author guojingbu
 * @Date 2019/9/23
 */
public class MultipartBuilder {
    /**
     * 单文件上传构造.
     *
     * @param file 文件
     * @param requestBody 请求体
     * @return MultipartBody
     */
    public static MultipartBody fileToMultipartBody(File file, RequestBody requestBody) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("key", file.getName(), requestBody);
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    /**
     * 多文件上传构造.
     *
     * @param files 文件列表
     * @param fileUploadObserver 文件上传回调
     * @return MultipartBody
     */
    public static MultipartBody filesToMultipartBody(List<File> files,
                                                     FileUploadObserver<ResponseBody> fileUploadObserver) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
//        JsonArray jsonArray = new JsonArray();

//        Gson gson = new Gson();
        for (File file : files) {
            UploadFileRequestBody uploadFileRequestBody =
                    new UploadFileRequestBody(file, fileUploadObserver);
//            JsonObject jsonObject = new JsonObject();
//
//            jsonObject.addProperty("fileName", file.getName());
//            jsonArray.add(jsonObject);
            builder.addFormDataPart("file", file.getName(), uploadFileRequestBody);
        }
//        builder.addFormDataPart("params", gson.toJson(jsonArray));
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }
}
