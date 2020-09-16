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

@Module
@InstallIn(ApplicationComponent::class)
object AppCommonModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson, baseURL: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideCoroutinesContextProvider() = CoroutinesContextProvider()
}