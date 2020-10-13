package com.yesway.messagelibrary.api

import com.yesway.api_lib.networklib.bean.ResponseBean
import com.yesway.messagelibrary.api.bean.*
import com.yesway.messagelibrary.api.bean.request.Req_MsgList
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    /**获取一级菜单*/
    @GET("app/v1/message-types/v1/parent")
    fun getParentType(): Observable<ResponseBean<TypeParent>>
    /**获取全部消息类型*/
    @GET("app/v1/message-types/v1")
    fun getallMsgType():Observable<ResponseBean<TypeAll>>
    /**获取某分类下消息类型*/
    @GET("app/v1/message-types/v1/{id}")
    fun getMsgType(@Path("id")  id: String ):Observable<ResponseBean<MsgList>>
    /**消息已读*/
    @PUT("app/v1/message-types/v1/{id}/opened")
    fun msgRead(@Path("id") id :String):Observable<ResponseBean<MsgResult>>
    /**消息列表*/
    @POST("app/v1/message-infos/v1/types/{id}")
    fun getMsgList(@Path("id") id:String,@Body page: Req_MsgList):Observable<ResponseBean<MsgResultList>>
    /**删除消息*/
    @DELETE("app/v1/message-infos/v1/{id}")
    fun delMsg(@Path("id")id :String):Observable<ResponseBean<MsgResult>>
    /**获取未读消息数*/
    @GET("/app/v1/message-types/v1/customers/{customer_id}/unopened")
    fun getUnOpened(@Path("customer_id") customer_id:String):Observable<ResponseBean<MsgUnread>>
}