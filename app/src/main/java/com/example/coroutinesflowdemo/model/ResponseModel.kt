package com.example.coroutinesflowdemo.model

import com.google.gson.annotations.SerializedName

/**
 * Create by james.li on 2020/9/9
 * Description:
 */

data class BaseResponse<T>(
    @SerializedName("data") var data: T,
    @SerializedName("page") var page: Int,
    @SerializedName("page_count") var pageCount: Int,
    @SerializedName("status") var code: Int,
    @SerializedName("total_counts") var totalCount: Int,
)

data class GirlResp(
    val _id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>?,
    val likeCounts: Int,
    val publishedAt: String,
    val stars: Int,
    val title: String,
    val type: String,
    val url: String,
    val views: Int
)

data class TokenResp(
    val token: String
)

data class LoginResp(
    var userName: String,
    var nickName: String,
    var avatarUrl: String
)