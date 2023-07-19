package com.code.cancer.demos

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import kotlinx.coroutines.MainScope

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-07-19
 *  @What:
 */
class App : Application() {


    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application

        val scope = MainScope()

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = base
    }

}