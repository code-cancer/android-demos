package com.code.cancer.demos.ui.nested.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.ViewCompat

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-05-24
 *  @What:
 */
class NestedScrollingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), NestedScrollingChild {

    private val helper = NestedScrollingChildHelper(this)

    private val mConsumed = IntArray(2)

    private val mOffset = IntArray(2)

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        helper.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return helper.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        Log.d("NestedScroll", "child startNestedScroll: $axes")
        return helper.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        helper.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return helper.hasNestedScrollingParent()
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        Log.d("NestedScroll", "child dispatchNestedScroll")
        return helper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        Log.d("NestedScroll", "child dispatchNestedPreScroll")
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        Log.d("NestedScroll", "child dispatchNestedPreFling")
        return super.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        Log.d("NestedScroll", "child dispatchNestedPreFling")
        return super.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("NestedScroll", "onTouchEvent: ")
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
            }
            MotionEvent.ACTION_MOVE -> {
                dispatchNestedPreScroll(0, 20, mConsumed, mOffset)
                dispatchNestedScroll(50,50,50,50,mOffset)
            }
            MotionEvent.ACTION_UP -> {
                stopNestedScroll()
            }
        }
        return super.onTouchEvent(event)
    }
}