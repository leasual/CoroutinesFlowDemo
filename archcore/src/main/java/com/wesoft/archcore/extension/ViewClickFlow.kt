package com.wesoft.archcore.extension

import android.view.View
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
 * Create a [Flow] of click events on the [View] instance.
 *
 * Note: Created flow keeps a strong reference to the [View] instance
 * until the coroutine that launched the flow collector is cancelled.
 *
 * Example of usage:
 *
 * ```
 * view.clicks()
 *     .onEach {
 *          // handle view clicked
 *     }
 *     .launchIn(uiScope)
 * ```
 */
@CheckResult
@OptIn(ExperimentalCoroutinesApi::class)
fun View.clicks(): Flow<Unit> = callbackFlow {
    checkMainThread()
    val listener = View.OnClickListener {
        safeOffer(Unit)
    }
    setOnClickListener(listener)
    awaitClose { setOnClickListener(null) }
}.conflate()