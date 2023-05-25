package com.code.cancer.demos.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        val vbClazz = parameterizedType.actualTypeArguments[0] as Class<VB>
        binding = vbClazz.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB
        setContentView(binding.root)
        onCreated()
    }

    abstract fun onCreated()

}
