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