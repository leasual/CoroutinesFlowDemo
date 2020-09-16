package com.wesoft.archcore.extension

import androidx.annotation.CheckResult
import androidx.viewpager.widget.ViewPager
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
 * Create a [InitialValueFlow] of page selected events on the [ViewPager] instance
 * where the value emitted is the position index of the new selected page.
 *
 * Note: Created flow keeps a strong reference to the [ViewPager] instance
 * until the coroutine that launched the flow collector is cancelled.
 *
 * Example of usage:
 *
 * ```
 * viewPager.pageSelections()
 *     .onEach { position ->
 *          // handle position
 *     }
 *     .launchIn(uiScope)
 * ```
 */
@CheckResult
@OptIn(ExperimentalCoroutinesApi::class)
public fun ViewPager.pageSelections(): InitialValueFlow<Int> = callbackFlow<Int> {
    checkMainThread()
    val listener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
            safeOffer(position)
        }

        override fun onPageScrollStateChanged(state: Int) = Unit
    }

    addOnPageChangeListener(listener)
    awaitClose { removeOnPageChangeListener(listener) }
}
    .conflate()
    .asInitialValueFlow { currentItem }

/**
 * Create a [Flow] of page scroll events on the [ViewPager] instance.
 *
 * Note: Created flow keeps a strong reference to the [ViewPager] instance
 * until the coroutine that launched the flow collector is cancelled.
 *
 * Example of usage:
 *
 * ```
 * viewPager.pageScrollEvents()
 *     .onEach { event ->
 *          // handle page scroll event
 *     }
 *     .launchIn(uiScope)
 * ```
 */
@CheckResult
@OptIn(ExperimentalCoroutinesApi::class)
public fun ViewPager.pageScrollEvents(): Flow<ViewPagerPageScrollEvent> = callbackFlow<ViewPagerPageScrollEvent> {
    checkMainThread()
    val listener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            safeOffer(
                ViewPagerPageScrollEvent(
                    view = this@pageScrollEvents,
                    position = position,
                    positionOffset = positionOffset,
                    positionOffsetPixel = positionOffsetPixels
                )
            )
        }

        override fun onPageSelected(position: Int) = Unit

        override fun onPageScrollStateChanged(state: Int) = Unit
    }
    addOnPageChangeListener(listener)
    awaitClose { removeOnPageChangeListener(listener) }
}.conflate()

public class ViewPagerPageScrollEvent(
    public val view: ViewPager,
    public val position: Int,
    public val positionOffset: Float,
    public val positionOffsetPixel: Int
)

/**
 * Create a [Flow] of page scroll state change events on the [ViewPager] instance
 * where the value emitted can be one of [SCROLL_STATE_IDLE], [SCROLL_STATE_DRAGGING]} or [SCROLL_STATE_SETTLING].
 *
 * Note: Created flow keeps a strong reference to the [ViewPager] instance
 * until the coroutine that launched the flow collector is cancelled.
 *
 * Example of usage:
 *
 * ```
 * viewPager.pageScrollStateChanges()
 *     .onEach { state ->
 *          // handle state
 *     }
 *     .launchIn(uiScope)
 * ```
 */
@CheckResult
@OptIn(ExperimentalCoroutinesApi::class)
public fun ViewPager.pageScrollStateChanges(): Flow<Int> = callbackFlow<Int> {
    checkMainThread()
    val listener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) = Unit

        override fun onPageScrollStateChanged(state: Int) {
            safeOffer(state)
        }
    }

    addOnPageChangeListener(listener)
    awaitClose { removeOnPageChangeListener(listener) }
}.conflate()