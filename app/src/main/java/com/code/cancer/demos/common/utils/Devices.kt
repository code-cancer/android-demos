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


}