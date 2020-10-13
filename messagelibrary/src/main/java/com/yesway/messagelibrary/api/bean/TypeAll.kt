package com.yesway.messagelibrary.api.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class TypeAll {
     var record: List<ARecord>?=null
 }

class ARecord {
    var children: List<Children>? =null
    var count: Int?=null
    var icon: String?=null
    var id: String?=null
    var name: String?=null
}
@Parcelize
 class Children : Parcelable {
     var count: Int?=null
     var icon: String?=null
     var id: String?=null
     var lastMessageTime: String?=null
     var lastMessageTitle: String?=null
     var name: String?=null
     var parentId: String?=null
     var parentName: String?=null
 }