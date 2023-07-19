package com.code.cancer.demos.common.utils

import android.content.Context
import android.view.WindowManager
import com.code.cancer.demos.App

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-07-19
 *  @What:
 */
object Devices {

    fun getPhoneHeight(): Int {
        val wm = App.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.height
    }

    fun getPhoneWidth(): Int {
        val wm = App.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.width
    }


    fun getStatusBarHeight(): Int {
        return try {
            val clazz = Class.forName("com.android.internal.R${'$'}dimen")
            val instance = clazz.newInstance()
            val field = clazz.getField("status_bar_height")
            val h = field.get(instance)?.toString()?.toInt() ?: 0
            App.context.resources.getDimensionPixelSize(h)
        } catch (e: Exception) {
            0
        }
    }

}