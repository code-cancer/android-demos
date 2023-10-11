package com.code.cancer.demos.ui.system

import android.app.ActivityManager
import android.content.Context
import android.os.Debug
import com.code.cancer.demos.base.BaseActivity
import com.code.cancer.demos.databinding.ActivitySystemParamsBinding
import com.code.cancer.demos.ext.context

class SystemParamsActivity : BaseActivity<ActivitySystemParamsBinding>() {
    override fun onCreated() = binding.run {
        getBtn.setOnClickListener { getSystemParams() }
    }

    private fun getSystemParams() = binding.run {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val maxHeapSize = activityManager.memoryClass
        val largeHeapSize = activityManager.largeMemoryClass
        val freeHeapSize = Runtime.getRuntime().freeMemory() / 1024 / 1024
        val usedHeapSize = maxHeapSize - freeHeapSize
        normalHeapSize.text = "正常堆大小：${maxHeapSize}M"
        initHeapSize.text = "可用堆大小：${Runtime.getRuntime().freeMemory()  / 1024 / 1024}M"
        binding.maxHeapSize.text = "最大堆大小：${largeHeapSize}M"
        binding.currentHeapSize.text = "已用堆大小：${usedHeapSize}M"
        val nativeHeapSizeValue = Debug.getNativeHeapSize()  / 1024 / 1024
        nativeHeapSize.text = "Native堆大小：${nativeHeapSizeValue}M"
        val nativeHeapFreeSize = Debug.getNativeHeapFreeSize()  / 1024 / 1024
        currentNativeHeapSize.text = "Native堆已用大小：${nativeHeapSizeValue - nativeHeapFreeSize}M"
    }


}