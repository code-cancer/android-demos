package com.code.cancer.demos.common.adapter

import com.code.cancer.demos.base.RecyclerBindingAdapter
import com.code.cancer.demos.databinding.NestedItemViewBinding

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-07-11
 *  @What:
 */
class NormalAdapter<Data> : RecyclerBindingAdapter<NestedItemViewBinding>() {

    private val data = ArrayList<Data>()

    override fun onBindViewHolder(holder: ViewHolder<NestedItemViewBinding>, position: Int) {
        holder.binding.run {
            root.setBackgroundColor(getRandomColor())
            text.text = buildString {
                append("测试Item-")
                append(position)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun add(index: Int, data: Data) {
        this.data.add(index, data)
        notifyItemInserted(index)
    }

    fun remove(index: Int) {
        this.data.removeAt(index)
        notifyItemRemoved(index)
    }
}