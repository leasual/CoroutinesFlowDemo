package com.example.coroutinesflowdemo.api

import com.example.coroutinesflowdemo.model.BaseResponse
import com.example.coroutinesflowdemo.model.GirlResp
import com.example.coroutinesflowdemo.model.LoginResp
import com.example.coroutinesflowdemo.model.TokenResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GankService {

    @GET("data/category/Girl/type/Girl/page/{page}/count/{count}")
    suspend fun getGirlPictures(@Path("page") page: Int, @Path("count") count: Int): Response<BaseResponse<List<GirlResp>>>

    @GET("data/category/Girl/type/Girl")
    suspend fun getToken(): Response<BaseResponse<TokenResp>>

    @GET("data/category/Girl/type/Girl/page")
    suspend fun login(): Response<BaseResponse<LoginResp>>
}