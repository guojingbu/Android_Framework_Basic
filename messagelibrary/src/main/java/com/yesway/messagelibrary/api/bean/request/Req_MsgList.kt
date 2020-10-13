package com.yesway.messagelibrary.api.bean.request

class Req_MsgList(pageNum:Int,pageSize:Int) {
    var  pageNum:Int ?=0
    var  pageSize:Int?=0
    init {
        this.pageNum = pageNum
        this.pageSize= pageSize
    }
}
