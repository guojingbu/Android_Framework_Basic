package com.yesway.messagelibrary.api

import android.content.Context
import com.yesway.api_lib.networklib.RetrofitManager
import com.yesway.api_lib.networklib.bean.ResponseBean
import com.yesway.messagelibrary.api.bean.*
import com.yesway.messagelibrary.api.bean.request.Req_MsgList
import io.reactivex.Observable

class MsgModel(context: Context) {
    var context: Context? = null
    private val mApiService: ApiService

    init {
        this.context = context
        mApiService = RetrofitManager.getInstance().create(ApiService::class.java)
    }

    /**
     * 获取一级消息分类
     */
    fun getParentType(): Observable<ResponseBean<TypeParent>> {
        val observable: Observable<ResponseBean<TypeParent>> = mApiService.getParentType()
        return RetrofitManager.getInstance().toDispatchActivity(observable, context) as Observable<ResponseBean<TypeParent>>
    }

    /**
     * 获取全部消息分类
     */
    fun getallMsgType(): Observable<ResponseBean<TypeAll>> {
        val observable: Observable<ResponseBean<TypeAll>> = mApiService.getallMsgType()
        return RetrofitManager.getInstance().toDispatchActivity(observable, context) as Observable<ResponseBean<TypeAll>>
    }

    /**
     * 获取某分类下分类列表
     */
    fun getMsgType(id: String): Observable<ResponseBean<MsgList>> {
        val observable: Observable<ResponseBean<MsgList>> = mApiService.getMsgType(id)
        return RetrofitManager.getInstance().toDispatchActivity(observable, context) as Observable<ResponseBean<MsgList>>
    }

    /**
     * 消息已读
     */
    fun msgRead(id: String): Observable<ResponseBean<MsgResult>> {
        val observable: Observable<ResponseBean<MsgResult>> = mApiService.msgRead(id)
        return RetrofitManager.getInstance().toDispatchActivity(observable, context) as Observable<ResponseBean<MsgResult>>
    }

    /**
     * 获取消息列表
     */
    fun getMsgList(id: String, page: Req_MsgList): Observable<ResponseBean<MsgResultList>> {
        val observable: Observable<ResponseBean<MsgResultList>> = mApiService.getMsgList(id, page)
        return RetrofitManager.getInstance().toDispatchActivity(observable, context) as Observable<ResponseBean<MsgResultList>>
    }

    /**
     * 删除某条消息
     */
    fun delMsg(id: String): Observable<ResponseBean<MsgResult>> {
        val observable: Observable<ResponseBean<MsgResult>> = mApiService.delMsg(id)
        return RetrofitManager.getInstance().toDispatchActivity(observable, context) as Observable<ResponseBean<MsgResult>>
    }

    /**
     * 获取消息未读数
     */
    fun getUnOpened(id: String): Observable<ResponseBean<MsgUnread>> {
        val observable: Observable<ResponseBean<MsgUnread>> = mApiService.getUnOpened(id)
        return RetrofitManager.getInstance().toDispatchActivity(observable, context) as Observable<ResponseBean<MsgUnread>>
    }

}