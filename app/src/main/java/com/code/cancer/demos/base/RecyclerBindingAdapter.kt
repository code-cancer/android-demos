package com.code.cancer.demos.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class RecyclerBindingAdapter<VB : ViewBinding> : RecyclerView.Adapter<RecyclerBindingAdapter.ViewHolder<VB>>() {

	class ViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VB> {
		val parameterizedType = javaClass.genericSuperclass as ParameterizedType
		val bindingClazz = parameterizedType.actualTypeArguments[0] as Class<*>
		val binding = bindingClazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java).invoke(null, LayoutInflater.from(parent.context), parent, false) as VB
		return ViewHolder(binding)
	}

}