package com.yesway.messagelibrary.api.bean

data class MsgResultList(
    val currentPage: Int,
    val pageCount: Int,
    val pageSize: Int,
    val record: List<BRecord>,
    val totalCount: Int
)

data class BRecord(
    val content: String,
    val createTime: String,
    val customerId: Int,
    val extParam: String,
    val id: String,
    val image: String,
    val openMode: Int,
    val openTarget: String,
    val title: String,
    val typeId: String,
    val typeName: String,
    val vehicleId: String
)