package com.yesway.messagelibrary.api.bean

 class MsgList {
     var messageType: MessageType?=null
 }

 class MessageType {
    var children: List<Children>?=null
    var count: Int?=null
    var icon: String?=null
    var id: String?=null
    var name: String?=null
}

/*
 class Children {
     var count: Int?=null
     var icon: String?=null
     var id: String?=null
     var lastMessageTime: String?=null
     var lastMessageTitle: String?=null
     var name: String?=null
     var parentId: String?=null
     var parentName: String?=null
 }*/
