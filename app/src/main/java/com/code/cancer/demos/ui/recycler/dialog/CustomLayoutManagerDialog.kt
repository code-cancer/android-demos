package com.code.cancer.demos.ui.recycler.dialog

import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import com.code.cancer.demos.base.BaseDialog
import com.code.cancer.demos.base.RecyclerBindingAdapter
import com.code.cancer.demos.common.adapter.getRandomColor
import com.code.cancer.demos.databinding.CardRecyclerItemBinding
import com.code.cancer.demos.databinding.DialogCustomLayoutManagerBinding
import com.code.cancer.demos.ui.recycler.CustomLayoutManager

/**
 *  @Author: ZhiFu.Zhang
 *  @Date: 2023-08-09
 *  @What:
 */
class CustomLayoutManagerDialog: BaseDialog<DialogCustomLayoutManagerBinding>() {

    override fun onCreated(): Unit = binding.run {
        recyclerView.layoutManager = CustomLayoutManager()
        recyclerView.adapter = ItemAdapter()
        recyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        val linearSnapHelper = PagerSnapHelper()
        linearSnapHelper.attachToRecyclerView(recyclerView)
    }

    private inner class ItemAdapter: RecyclerBindingAdapter<CardRecyclerItemBinding>() {
        override fun onBindViewHolder(holder: ViewHolder<CardRecyclerItemBinding>, position: Int) {
            holder.binding.run {
                root.setBackgroundColor(getRandomColor())
                text.text = "item - $position"
            }
        }

        override fun getItemCount(): Int = 10

    }

}