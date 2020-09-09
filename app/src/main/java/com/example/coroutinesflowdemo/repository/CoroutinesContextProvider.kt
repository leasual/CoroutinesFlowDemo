package com.example.coroutinesflowdemo.repository

import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * Create by james.li on 2020/9/9
 * Description:
 */

@Singleton
open class CoroutinesContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
    open val default: CoroutineContext by lazy { Dispatchers.Default }
}

/**
 * 用于单元测试
 */
@Singleton
class TestCoroutinesContextProvider : CoroutinesContextProvider() {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
    override val default: CoroutineContext = Dispatchers.Unconfined
}
