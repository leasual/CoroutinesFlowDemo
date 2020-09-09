package com.example.coroutinesflowdemo.api

import com.example.coroutinesflowdemo.model.BaseResponse
import com.example.coroutinesflowdemo.model.GirlResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GankService {

    @GET("data/category/Girl/type/Girl/page/{page}/count/{count}")
    suspend fun getGirlPictures(@Path("page") page: Int, @Path("count") count: Int): Response<BaseResponse<List<GirlResp>>>
}