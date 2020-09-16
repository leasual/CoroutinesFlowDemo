package com.wesoft.archcore.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wesoft.archcore.util.CoroutinesContextProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Create by james.li on 2020/9/15
 * Description:
 */

interface AbsBaseAppModule {

    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient

    fun provideBaseURL(): String

    fun provideAppService(retrofit: Retrofit): Any
}