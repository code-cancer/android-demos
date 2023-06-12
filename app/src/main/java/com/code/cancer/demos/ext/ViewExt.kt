package com.code.cancer.demos.ext

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-06-09
 *  @What:
 */
fun View.modifyConstraintParams(modify: ConstraintLayout.LayoutParams.() -> Unit) {
    val layoutParams = this.layoutParams
    if(layoutParams is ConstraintLayout.LayoutParams) {
        modify.invoke(layoutParams)
        this.layoutParams = layoutParams
    }
}
fun View.modifyParams(modify: ViewGroup.LayoutParams.() -> Unit) {
    val layoutParams = this.layoutParams
    if(layoutParams is ViewGroup.LayoutParams) {
        modify.invoke(layoutParams)
        this.layoutParams = layoutParams
    }
}