package com.example.coroutinesflowdemo.di

import android.content.Context
import com.example.coroutinesflowdemo.BuildConfig
import com.example.coroutinesflowdemo.api.AppService
import com.example.coroutinesflowdemo.data.AppDatabase
import com.wesoft.archcore.di.AbsBaseAppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule: AbsBaseAppModule {

    @Singleton
    @Provides
    override fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(httpLoggingInterceptor.apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BODY })
    }.build()

    @Singleton
    @Provides
    override fun provideBaseURL(): String = BuildConfig.BASE_URL

    @Singleton
    @Provides
    override fun provideAppService(retrofit: Retrofit) = retrofit.create(AppService::class.java)


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideNewDao(db: AppDatabase) = db.getNewsDao()

}