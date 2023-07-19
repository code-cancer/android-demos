package com.code.cancer.demos.ui.down

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.code.cancer.demos.App
import com.code.cancer.demos.databinding.CountDownFloatViewBinding
import com.code.cancer.demos.ext.modify
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-07-19
 *  @What:
 */
@SuppressLint("ClickableViewAccessibility")
class FloatCountDownView(
    context: Context
) : ConstraintLayout(context) {

    private var mx = 0f
    private var my = 0f
    private val date = Date()
    private val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    private var countDownJob: Job? = null
    private val binding = CountDownFloatViewBinding.inflate(LayoutInflater.from(context), this, true)
    private var windowManager: WindowManager? = ContextCompat.getSystemService(App.context, WindowManager::class.java)
    private val params = WindowManager.LayoutParams().apply {
        width = WindowManager.LayoutParams.WRAP_CONTENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }
        format = PixelFormat.RGBA_8888
    }
    var isInWindow = false
        private set

    init {
        binding.tvTime.setOnTouchListener(object : OnTouchListener {
            private var x = 0
            private var y = 0

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                val rawX = event.rawX.toInt()
                val rawY = event.rawY.toInt()
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        x = rawX
                        y = rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val movedX = rawX - x
                        val movedY = rawY - y
                        x = rawX
                        y = rawY
                        params.x += movedX
                        params.y += movedY
                        windowManager?.updateViewLayout(this@FloatCountDownView, params)
                    }
                }
                return false
            }
        })
    }

    fun show() {
        if (!isInWindow && parent == null) {
            isInWindow = true
            windowManager?.addView(this, params)
            startCountDown()
        }
    }

    fun remove() {
        windowManager?.removeView(this)
        isInWindow = false
        stopCountDownJob()
    }

    private fun startCountDown() {
        if (countDownJob?.isActive == true) return
        countDownJob?.cancel()
        countDownJob = App.scope.launch {
            while (isInWindow) {
                updateTime()
                delay(50)
            }
        }
    }

    private fun stopCountDownJob() {
        countDownJob?.cancel()
    }

    private fun updateTime() {
        date.time = System.currentTimeMillis()
        val timeText = sdf.format(date)
        binding.tvTime.text = timeText
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopCountDownJob()
    }


}