package com.example.coroutinesflowdemo.di

import com.example.coroutinesflowdemo.core.util.Connectivity
import com.example.coroutinesflowdemo.core.util.ConnectivityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Create by james.li on 2020/9/9
 * 使用 @Binds 注入接口实例 https://developer.android.com/training/dependency-injection/hilt-android
 * 简化注入，避免像Repository依赖Connectivity对象，如果不使用，则每个Repository都需要声明注入
 * Description:
 */

@Module
@InstallIn(ApplicationComponent::class)
abstract class ConnectivityModule {
    @Binds
    abstract fun bindConnectivity(
        connectivityImpl: ConnectivityImpl
    ): Connectivity
}