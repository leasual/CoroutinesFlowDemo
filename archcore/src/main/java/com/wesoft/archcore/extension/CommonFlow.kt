package com.wesoft.archcore.extension

import android.os.Looper
import androidx.annotation.RestrictTo
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onStart

/**
 * Create by james.li on 2020/9/10
 * Description:
 */

fun <E> SendChannel<E>.safeOffer(value: E) = !isClosedForSend && try {
    offer(value)
} catch (e: CancellationException) {
    false
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun checkMainThread() = check(Looper.myLooper() == Looper.getMainLooper()) {
    "Expected to be called on the main thread but was " + Thread.currentThread().name
}

/**
 * Converts a [Flow] to an [InitialValueFlow], taking an [initialValue] lambda for computing the initial value.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@OptIn(ExperimentalCoroutinesApi::class)
fun <T : Any> Flow<T>.asInitialValueFlow(initialValue: () -> T): InitialValueFlow<T> = InitialValueFlow(
    onStart {
        emit(initialValue())
    }
)

/**
 * A [Flow] implementation that emits the current value of a widget immediately upon collection.
 */
class InitialValueFlow<T : Any>(private val flow: Flow<T>) : Flow<T> by flow {

    /**
     * Returns a [Flow] that skips the initial emission of the current value.
     */
    fun skipInitialValue(): Flow<T> = flow.drop(1)
}