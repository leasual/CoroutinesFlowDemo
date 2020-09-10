package com.example.coroutinesflowdemo.model

/**
 * Create by james.li on 2020/9/9
 * Description:
 */

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