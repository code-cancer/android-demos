package com.code.cancer.demos.ui.nested.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.NestedScrollingParent
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ViewCompat

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-05-24
 *  @What:
 */
class NestedScrollingLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs), NestedScrollingParent {

    private val helper = NestedScrollingParentHelper(this)

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        Log.d("NestedScroll", "parent onStartNestedScroll")
        return (nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
        Log.d("NestedScroll", "parent onNestedScrollAccepted")
        helper.onNestedScrollAccepted(child, target, axes)
    }

    override fun onStopNestedScroll(child: View) {
        Log.d("NestedScroll", "parent onStopNestedScroll")
        helper.onStopNestedScroll(child)
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        Log.d("NestedScroll", "parent onNestedScroll")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        scrollBy(0, -dy)
        consumed[0] = 0
        consumed[1] = 10
        Log.d("NestedScroll", "parent onNestedPreScroll")
    }

    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        Log.d("NestedScroll", "parent onNestedFling")
        return true
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        Log.d("NestedScroll", "parent onNestedPreFling")
        return true
    }

    override fun getNestedScrollAxes(): Int {
        return helper.nestedScrollAxes
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when(ev.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("NestedScroll", "ACTION_DOWN: ")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("NestedScroll", "ACTION_MOVE: ")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("NestedScroll", "ACTION_UP: ")
            }
        }
        val dispatchTouchEvent = super.dispatchTouchEvent(ev)
        Log.d("NestedScroll", "return: $dispatchTouchEvent")
        return dispatchTouchEvent
    }

}