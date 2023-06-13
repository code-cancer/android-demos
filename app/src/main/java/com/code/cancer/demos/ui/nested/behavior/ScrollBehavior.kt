package com.code.cancer.demos.ui.nested.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-06-12
 *  @What:
 */
class ScrollBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        val b = child.y in (-child.height.toFloat()..0f)
        return b
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val down = !target.canScrollVertically(-1) && dy < 0
        val up = dy > 0 && -child.y < child.height
        if (down || up) {
            child.y -= dy
            if (child.y >= 0f) child.y = 0f
            if (child.y < -child.height) child.y = -child.height.toFloat()
            consumed[1] = dy
        }
    }


}