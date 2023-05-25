package com.code.cancer.demos.ext

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-05-24
 *  @What:
 */

val Activity.context
    get() = this

fun runActivity(context: Context, clazz: Class<out Activity>) {
    context.startActivity(Intent(context, clazz))
}