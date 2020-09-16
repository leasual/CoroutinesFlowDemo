package com.wesoft.archcore.extension

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.annotation.CheckResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

/**
 * Create by james.li on 2020/9/10
 * Description:
 */

/**
 * Create a [Flow] of refresh events on the [SwipeRefreshLayout] instance.
 *
 * Note: Created flow keeps a strong reference to the [SwipeRefreshLayout] instance
 * until the coroutine that launched the flow collector is cancelled.
 *
 * Example of usage:
 *
 * ```
 * swipeRefreshLayout.refreshes()
 *     .onEach {
 *          // handle refreshed
 *     }
 *     .launchIn(uiScope)
 * ```
 */
@CheckResult
@OptIn(ExperimentalCoroutinesApi::class)
public fun SwipeRefreshLayout.refreshes(): Flow<Unit> = callbackFlow {
    checkMainThread()
    val listener = SwipeRefreshLayout.OnRefreshListener {
        safeOffer(Unit)
    }
    setOnRefreshListener(listener)
    awaitClose { setOnRefreshListener(null) }
}.conflate()