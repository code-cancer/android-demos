package com.code.cancer.demos.ui.nested.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-06-12
 *  @What:
 */
class BottomImageViewBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is ImageView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        child.y = dependency.y + dependency.height
        child.x = dependency.x
        return true
    }

}