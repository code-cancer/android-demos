package com.code.cancer.demos.base

import android.view.ViewGroup
import android.widget.Button
import com.code.cancer.demos.ext.MATCH_PARENT
import com.code.cancer.demos.ext.WRAP_CONTENT
import com.code.cancer.demos.ext.viewGroupLayoutParams

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-06-09
 *  @What:
 */
class CaseItemAdapter(private val caseNames: List<String>, val onClick: (Int) -> Unit) : RecyclerViewAdapter<Button>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Button> {
        val button = Button(parent.context)
        button.layoutParams = viewGroupLayoutParams(MATCH_PARENT, WRAP_CONTENT)
        return ViewHolder(button)
    }

    override fun getItemCount(): Int = caseNames.size

    override fun onBindViewHolder(holder: ViewHolder<Button>, position: Int) {
        holder.view.text = caseNames[position]
        holder.view.setOnClickListener { onClick(position) }
    }
}