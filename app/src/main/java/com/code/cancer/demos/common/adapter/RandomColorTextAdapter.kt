package com.code.cancer.demos.common.adapter

import android.graphics.Color
import com.code.cancer.demos.base.RecyclerBindingAdapter
import com.code.cancer.demos.databinding.NestedItemViewBinding
import java.util.*

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-07-11
 *  @What:
 */
class RandomColorTextAdapter(private val size: Int = 50) : RecyclerBindingAdapter<NestedItemViewBinding>() {

    override fun onBindViewHolder(holder: ViewHolder<NestedItemViewBinding>, position: Int) {
        holder.binding.run {
            root.setBackgroundColor(getRandomColor())
            text.text = buildString {
                append("测试Item-")
                append(position)
            }
        }
    }

    override fun getItemCount(): Int = size

}

fun getRandomColor(): Int {
    val random = Random()
    return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
}

