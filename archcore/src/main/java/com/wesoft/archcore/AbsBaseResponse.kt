package com.wesoft.archcore

/**
 * Create by james.li on 2020/9/15
 * Description:
 */

interface AbsBaseResponse<T> {

    fun isSuccessful(): Boolean

    fun  data(): T

    fun  code(): Int
}