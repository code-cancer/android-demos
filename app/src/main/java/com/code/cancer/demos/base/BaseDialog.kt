package com.code.cancer.demos.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.code.cancer.demos.R
import java.lang.reflect.ParameterizedType

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-06-09
 *  @What:
 */
abstract class BaseDialog<VB : ViewBinding> : DialogFragment() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BaseDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        val vbClazz = parameterizedType.actualTypeArguments[0] as Class<VB>
        binding = vbClazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            .invoke(null, inflater, container, false) as VB
        binding.root.setBackgroundColor(Color.WHITE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreated()
    }


    abstract fun onCreated()


}