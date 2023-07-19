package com.code.cancer.demos.common.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.code.cancer.demos.App

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-07-19
 *  @What:
 */
object Permissions {

    fun canOverlay(): Boolean {
        return Settings.canDrawOverlays(App.context)
    }

    fun requestOverlay(activity: Activity) {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
        intent.data = Uri.parse("package:${App.context.packageName}")
        activity.startActivity(intent)
    }

}