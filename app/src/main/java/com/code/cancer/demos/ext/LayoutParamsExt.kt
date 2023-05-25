package com.code.cancer.demos.ext

import android.view.ViewGroup

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-05-24
 *  @What:
 */

const val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT
const val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT

fun viewGroupLayoutParams(
    width: Int = WRAP_CONTENT,
    height: Int = WRAP_CONTENT,
    builder: (ViewGroup.LayoutParams.() -> Unit)? = null
): ViewGroup.LayoutParams {
    val layoutParams = ViewGroup.LayoutParams(width, height)
    builder?.invoke(layoutParams)
    return layoutParams
}

fun ViewGroup.LayoutParams.modify(modifier: (ViewGroup.LayoutParams) -> Unit): ViewGroup.LayoutParams {
    modifier(this)
    return this
}